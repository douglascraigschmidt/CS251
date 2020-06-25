/* @author G. Hemingway, copyright 2020 - All rights reserved */
#ifndef VECTOR_H
#define VECTOR_H

#include <cstdint>
#include <string>

/**
 *  A class representing a 2-dimensional vector of doubles. Common
 *  vector operations are implemented in a loop-free manner to
 *  encourage use of functors, lambda expressions and STL algorithms.
 *
 *  Since no dynamic memory is used, destructor, copy constructor, and
 *  an assignment operator are not necessary.
 */
class vector2 {
public:
  /**
   *  Creates the zero vector.
   */
  vector2() noexcept;

  /**
   * Copy constructor - use default
   */
  vector2(const vector2& rhs) noexcept = default;

  /**
   *  Creates a vector using the first 2 values starting at ptr.
   */
  explicit vector2(const double* ptr) noexcept;

  /**
   * Assignment operator - use default
   */
  vector2& operator=(const vector2& rhs) noexcept = default;

  /***************************************************************************
   *                                                                          *
   *                      S P A C E   O P E R A T I O N S                     *
   *                                                                          *
   ***************************************************************************/

  /**
   *  Returns the sum of this vector and rhs.
   */
  [[nodiscard]] vector2 add(const vector2& rhs) const;

  /**
   *  Scales a copy of this vector by rhs and returns the result.
   */
  [[nodiscard]] vector2 scale(const double& rhs) const;

  /**
   *  Returns the dot (inner) product of this vector and rhs.
   */
  [[nodiscard]] double dot(const vector2& rhs) const;

  /**
   *  Returns the square of the magnitude of this vector.
   */
  [[nodiscard]] double normSq() const;

  /**
   *  Returns the magnitude of this vector.
   */
  [[nodiscard]] double norm() const;

  /**
   *  Returns a scaled copy of this vector such that its magnitude is 1
   *  Throw overflow_error if norm is zero
   */
  [[nodiscard]] vector2 normalize() const;

  /**
   *  Returns a human readable representation of this vector.
   *  Ex. [1 2]
   */
  [[nodiscard]] std::string toString() const;

  /***************************************************************************
   *                                                                          *
   *                  O V E R L O A D E D   O P E R A T O R S                 *
   *                                                                          *
   ***************************************************************************/

  /**
   *  Returns the additive inverse of this vector.
   */
  vector2 operator-() const;

  /**
   *  Returns the difference between this vector and rhs.
   */
  vector2 operator-(const vector2 & rhs) const;

  /**
   *  Returns the sum of this vector and rhs.
   */
  vector2 operator+(const vector2& rhs) const;

  /**
   *  Returns true if this vector equals rhs.
   */
  bool operator==(const vector2& rhs) const;

  /**
   *  Returns true if this vector differs from rhs.
   */
  bool operator!=(const vector2& rhs) const;

  /**
   *  Returns a reference to the index-th component of this vector. Not range
   *  checked.
   */
  double& operator[](uint32_t index);

  /**
   *  Returns a reference to the index-th component of this vector. Not range
   *  checked.
   */
  const double& operator[](uint32_t index) const;

  /**
   *  Increments this vector by rhs and returns the result for chaining.
   */
  vector2& operator+=(const vector2& rhs);

  /**
   *  Scales this vector by 1.0 / rhs and returns the result for chaining.
   */
  vector2& operator/=(const double& rhs);

  /**
   *  Scales a copy of this vector by 1.0 / rhs and returns the result.
   */
  vector2 operator/(const double& rhs) const;

  /**
   *  Scales this vector by rhs and returns the result for chaining.
   */
  vector2& operator*=(const double& rhs);

  /**
   *  Returns the dot (inner) product of this vector and rhs.
   */
  double operator*(const vector2& rhs) const;

  /**
   *  Scales a copy of this vector by rhs and returns the result.
   */
  vector2 operator*(const double& rhs) const;

private:
  /**
   *  Private iterator methods.
   */
  double* begin();

  [[nodiscard]] const double* begin() const;

  double* end();

  [[nodiscard]] const double* end() const;

  /**
   * Number of dimensions in the vector.
   */
  static const int DIMS = 2;

  /**
   *  Statically allocated storage space.
   */
  double mData[DIMS]{};
};

/**
 *  Returns the result of scaling a copy of v by scale. This free function
 *  guarantees that vector scaling is commutative.
 */
vector2 operator*(const double& scale, const vector2& v);

#endif // VECTOR_H
