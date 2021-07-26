# CS 3251: Intermediate Software Design

## Programming Assignment 3

This assignment focuses on strong exception safety, as well as move
semantics (i.e., the "Rule of 5").  As always, you have been provided
with several source files to start with:

* `include/ArrayList.h`
 
And several building and testing files:

* `tests/main.cpp`
* `tests/arrayTest.cpp`
* `.github/`
* `.clang-format`
* `.gitignore`
* `.travis.sh`
* `.travis.yml`
* `CMakeLists.txt`
* `README.md`


Do not modify any of the build & test files.

The file _ArrayList.h_ contain comments that specify the class's
functionality. Your task is to implement all of the methods declared
in _ArrayList.h_ in a **strongly-exception** safe manner. You should
copy your _ScopedArray.h_ and _ScopedArray.cpp_ files from the
previous assignment into the _include_ and _src_ directors,
respectively, so make sure that it works properly

The assignment is to be submitted using the link to github.com sent
out via email to all class members. DO NOT email your assignment to
the professor or the TAs. Emailed assignments ARE NOT accepted.  We
will grade the last commit to your repository before the deadline date
and time.  Any commits after that moment will be ignored.  If nothing
has been committed at that time you will receive a zero grade.

### Undergraduate Students: 

Undergraduates are to implement the functionality described in the
_include/ArrayList.h_ file.  Read through this file carefully, and
study the test cases defined in _tests/arrayTest.cpp_.  These will be
your guide for what the code must do.  Then review your code to ensure
it is strongly exception safe.  These are all templated classes, so if
we don't use a method, the compiler will not include it.

## Reminders

* Ensure that your name, vunetid, email address, and the honor code appear in the header comments of all files that you have altered.

* Do not alter any of the project files!  Work only on those files specified above.  You must use the supplied `CMakeLists.txt` file as is.

* All students are required to abide by the CS 3251 coding standard, [available on the course web page](https://vuse-cs3251.github.io/style-guidelines/) and provided to you on the first day of class. Points will be deducted for not following the coding standard.

* For full credit, your program must compile with a recent version of `clang` or `gcc` and run successfully with the CI platform.
  * The CI build *will* fail if your code is not properly formatted. **This is by design.** If your code is compiling and running successfully, but the build is failing, then your build is most likely failing because your code is not passing the linter. This can be confirmed by checking if a command involving `linter` or `clang-format` in the CI build output log has an exit code of 1.

* Your program(s) should always have an exit code of 0.  A non-zero exit code (generally indicative of a segmentation fault or some other system error) is reason to worry and must be corrected for full points.
  
* When submitting the assignment, all files that are provided to you, plus your solution files have been submitted. All files necessary to compile and run your program must reside in the GitHub.com repository. 
