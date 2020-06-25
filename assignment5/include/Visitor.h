/* @author G. Hemingway, copyright 2020 - All rights reserved */
#ifndef VISITOR_H
#define VISITOR_H

#include <ostream>

// Forward declaration.
class Object;

/**
 *  Abstract base class for the Visitor pattern.
 */
class Visitor {
public:
  /**
   *  Pure virtual destructor. A necessary no-op since this is a base class.
   */
  virtual ~Visitor() = default;

  /**
   * The worker method of the visitor. For this assignment, Object is
   * the only concrete class we can visit.
   */
  virtual void visit(Object& object) = 0;
};

/**
 * A visitor that accepts an ostream reference during
 * construction. Its visit method simply prints out the object's name.
 */
class PrintVisitor : public Visitor {
public:
  /**
   *  Construct a visitor that prints to the provided ostream.
   */
  explicit PrintVisitor(std::ostream& os);

  /**
   *  Prints the object's name.
   */
  void visit(Object& object) override;

private:
  /**
   *  Reference to the ostream.
   */
  std::ostream& os;
};

#endif // VISITOR_H
