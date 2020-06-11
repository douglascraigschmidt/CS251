#!/bin/bash
case "$KEY" in
    linter)
        docker exec -it build find /repo -regex '.*\.\(h\|cpp\)' -exec clang-format-8 -i -style=file {} \; \
        && docker exec -t build git -C /repo diff --quiet \
        || {
          for i in {{1..80}}; do echo -n = ; done
          echo
          echo "The following files don't meet the style guidelines:"
          docker exec -t build git -C /repo diff --name-only | sed "s/^/ - /"
          echo
          for i in {{1..80}}; do echo -n = ; done
          echo
          echo "Detailed style violations are as below:"
          docker exec -t build git -C /repo diff --color=always -U0 | sed "/^\x1b\[32m+/d;s/^\x1b\[36m/\n\0/;s/^\x1b\[1mdiff/\n\0/"
          echo
          return 1
        }
        ;;

    clang)
        echo "Executing Clang compiled binaries:"
        docker exec -t build cmake -H/repo -B/build \
        && docker exec -t build cmake --build /build \
        && docker exec -t build /repo/bin/testing
        ;;

    gcc)
        echo "Executing GCC compiled binaries:"
        docker exec -t build cmake -H/repo -B/build \
        && docker exec -t build cmake --build /build \
        && docker exec -t build /repo/bin/testing
        ;;

    *)
        echo "KEY must be one of linter|clang|gcc; aborting." && exit 1
esac
