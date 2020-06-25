# CS 3251: Intermediate Software Design
## Programming Assignment 5

## Overview

Design patterns are essential in developing anything larger than a
trivial program. This assignment requires you to implement the
meaningful portions of a Gravitational Simulation project using some
design patterns, such as Iterator, Visitor, Factory Method, Singleton,
and Composite.  It also gives you experience with more STL algorithms,
such as transform(), fill(), and copy_n().

This assignment provides a number of header files and requires the
implementation of the corresponding *.cpp files using appropriate
patterns and STL components. An understanding of a few basic physics
principles is helpful to understand the simulation.  While this may
seem hard at first glance, thanks to the vector2 class the actual math
is easy to implement (and much of it is provided for you).

Do not change any of the header files. Please start early and ask
questions as they arise. One of the tests simulates every second of
the Earth’s journey around the sun for a year, so the final executable
program may take a few minutes to execute.

## Classes

* Universe – A class responsible for keeping track of all the objects
  in your simulation. Since no more than one such environment should
  exist, please make this class a Singleton. For this assignment you
  are to assume that the first object registered with the Universe is
  a “sun” and it should not be affected by any forces.

* Object – This base class of the class hierarchy is designed to
  outline the interface for objects used in the simulation. Certain
  objects may be composed of other bodies, so this class should
  implement the Composite pattern. Correct handling of different types
  of objects requires this class provide an access point for a visitor
  via the Visitor pattern. Finally, to make all force applications
  appear simultaneously, a “snapshot” method will be available via the
  Prototype pattern.

* ObjectFactory – An implementation of the Factory Method pattern that
  provides a convenient way of creating celestial Objects based on a
  string argument.

* Visitor – A Visitor abstract base class will be defined along with a
  concrete subclass called PrintVisitor. The latter class is
  responsible for testing the Visitor/Iterator framework via simple
  print tests.

* vector2 - A class representing a 2-dimensional vector of
  doubles. Common vector operations are implemented in a loop-free
  manner to encourage use of functors and/or lambda functions, and STL
  algorithms.

## Physics Concepts Required for this Assignment: 

* The Law of Universal Gravitation: the magnitude of the force due to
  gravity between two objects is equal to G times the mass of the
  first times the mass of the second divided by the square of the
  distance between the two.

* The Second Law of Motion: the net force acting on an object is equal
  to its mass times its acceleration.

* The net force acting on upon an object is also equal to the sum of
  all the forces acting upon it.

* The acceleration of an object equals the change in its velocity
  divided by the corresponding change in time.

* The velocity of an object equals the change in its position divided
  by the corresponding change in time.

Acceleration, force, position, and velocity are all quantities in the
vector2 class that you'll need to complete.

## Hints

* The overall simulation breaks time into small steps.  Look at the
  heart of the UMC test, which includes a loop that goes through an
  entire year with a step size of 1 second.  Use the state information
  for each object in the Universe to calculate state for the next step
  - make sure to keep the state in the current step completely
  unchanged until you have fully calculated the state in the next
  step.

* Start with the two easier tests first - Inertia and Visitor. Once
  these are working as expected then move on to the UMC test.

* Do not change the CMakeList.txt or any of the build-related files.
  Concentrate all of your effort and changes into the *.cpp files in
  the ./src directory.  I have provided a solution to most of the
  vector2 class - you just need to implement the methods marked by the
  "// TODO -- you fill in here ..." tags.

* Make sure you have a working implementation of your ArrayList and
  ScopedArray classes from previous assignments since you'll need to
  use them in this assignment.  You'll also need to implement the
  const_iterator for ArrayList, which will give you additional
  experience with the Iterator pattern.

* Please use the test drivers and test data files provided in the
  repository.

## Reminders

* Ensure that your name, vunetid, email address, and the honor code
  appear in the header comments of all files that you have altered.

* Do not alter any of the project files!  Work only on those files
  specified above.  You must use the supplied `CMakeLists.txt` file as
  is.

* All students are required to abide by the CS 3251 coding standard,
  [available on the course web
  page](https://vuse-cs3251.github.io/style-guidelines/) and provided
  to you on the first day of class. Points will be deducted for not
  following the coding standard.

* For full credit, your program must compile with a recent version of
  * `clang` or `gcc` and run successfully with the CI platform.  The
  * CI build *will* fail if your code is not properly
  * formatted. **This is by design.** If your code is compiling and
  * running successfully, but the build is failing, then your build is
  * most likely failing because your code is not passing the
  * linter. This can be confirmed by checking if a command involving
  * `linter` or `clang-format` in the CI build output log has an exit
  * code of 1.

* Your program(s) should always have an exit code of 0.  A non-zero
  exit code (generally indicative of a segmentation fault or some
  other system error) is reason to worry and must be corrected for
  full points.
  
* When submitting the assignment, all files that are provided to you,
  plus your solution files have been submitted. All files necessary to
  compile and run your program must reside in your GitLab.com
  repository.
