# CS 3251: Intermediate Software Design

## Programming Assignment 1

This assignment is intended to reacquaint you with C++, your
programming environment & tools, and software design. If you are
familiar with C++, then this assignment will be straightforward. If
this assignment isn’t very straightforward, you may not have enough
C++ background to take this class, in which case please contact me
immediately!

You have been provided with several starter files:

* `include/AllocationTracker.h`,
* `src/AllocationTracker.cpp`,
* `tests/main.cpp`,
* `tests/simpleArrayTest.cpp`, and
* `tests/moreTests.cpp`.

Several build/project files (which you should **not** modify) have also been provided:

* `.github/`
* `.clang-format`
* `.gitignore`
* `.travis.sh`
* `.travis.yml`
* `CMakeLists.txt`

Use these to maintain, compile and test your code.

The primary task for this assignment is to complete the `SimpleArray`
class, both declaration and implementation.  The beginnings of the
declaration has been provided in `include/SimpleArray.h`.  Review the
tests defined in `tests/simpleArrayTest.cpp`.  These tests will be run
against your code and define the correct behavior of the SimpleArray
class.  Build on the provided initial declaration and author the
remainder of the class so that it passes all tests.

The assignment is to be pushed to your GitLab account, which you'll
then notify me about by using the Google form sent out via email to
all class members. DO NOT email your assignment to the professor or
the TAs.  Emailed assignments ARE NOT accepted.  We will grade the
last commit to your repository before the deadline date and time.  Any
commits after that moment will be ignored.  If nothing has been
committed at that time you will receive a zero grade.

The full grading policy can be found at [the course
website](https://www.dre.vanderbilt.edu/~schmidt/cs251/assignments.html).

### Undergraduate Students:

Undergraduates are to implement the complete `SimpleArray` class.  Do
not implement the functions as "inline".  Carefully work though the
test cases defined in `tests/simpleArrayTest.cpp` and figure out the
class's expected behavior.  Complete the .h file and fully implement
the .cpp file.

Pay extra attention to the block of tests at the end of the testing
file.  Some lines are commented out and have a note saying "This line
is incorrect."  If left commented-out, your program should compile and
run successfully.  If any of these lines is uncommented it should
result in a compilation failure.  Tackle each commented out test
individually.
 
### Graduate Students:

Graduate students are to do all of the work outlined above for
undergraduates.  Plus, graduate students must write at least six
additional sets of functional tests for the SimpleArray class.
Complete `tests/moreTests.cpp` to implement these tests.  To do so you
will need to exercise the SimpleArray class and thoroughly test its
behavior.  Make sure you understand what SimpleArray's behavior should
be before you write any tests.

## Reminders

* Ensure that your name, vunetid, email address, and the honor code
  appear in the header comments of all files that you have altered.

* Do not alter any of the project files!  Work only on those files
  specified above.  You must use the supplied `CMakeLists.txt` file as
  is.

* All students are required to abide by the CS 3251 coding standard,
  [available
  here](https://vuse-cs3251.github.io/style-guidelines/). Points will
  be deducted for not following the coding standard.

* For full credit, your program must compile with a recent version of
  `clang` or `gcc` and run successfully with the CI platform.

  * The CI build *will* fail if your code is not properly
    formatted. **This is by design.** If your code is compiling and
    running successfully, but the build is failing, then your build is
    most likely failing because your code is not passing the
    linter. This can be confirmed by checking if a command involving
    `linter` or `clang-format` in the CI build output log has an exit
    code of 1.

* Your program(s) should always have an exit code of 0.  A non-zero
  exit code (generally indicative of a segmentation fault or some
  other system error) is reason to worry and must be corrected for
  full points.
  
* When submitting the assignment, all files that are provided to you,
  plus your solution files have been submitted. All files necessary to
  compile and run your program must reside in the GitHub.com
  repository.
