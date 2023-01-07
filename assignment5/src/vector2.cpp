/* @author G. Hemingway, copyright 2020 - All rights reserved */
#include <algorithm>
#include <cmath>
#include <functional>
#include <iterator>
#include <sstream>
#include <stdexcept>

#include "vector2.h"

/**
 *  Creates the zero vector.
 */
vector2::vector2() noexcept
{
  // TODO -- you fill in here by using std::fill() to initialize mData
  // to 0.0.
}

/**
 *  Creates a vector using the first two values starting at ptr.
 */
vector2::vector2(const double* ptr) noexcept
{
  // TODO -- you fill in here by using std::copy_n() to initialize
  // mData to the first two values of the ptr parameter.
}

/**
 *  Returns a human readable representation of this vector.
 *  Ex. [1 2]
 */
std::string vector2::toString() const
{
    std::stringstream str;
    str << "[";
    std::copy(begin(), end() - 1, std::ostream_iterator<double>(str, " "));
    str << (*this)[1] << "]";
    return str.str();
}

/**
 *  Returns the sum of this vector and rhs.
 */
vector2 vector2::add(const vector2& rhs) const
{
    vector2 sum;

    // TODO -- you fill in here to use the std::transform() method to
    // compute the sum of this vector and rhs.
}

/**
 *  Scales a copy of this vector by rhs and returns the result.
 */
vector2 vector2::scale(const double& rhs) const
{
  vector2 s;

  // TODO -- you fill in here to use the std::transform() method to
  // scale a copy of this vector by rhs into s and return s.

  // Either the functor approach -- currently commented out -- or the lambda approach is fine
}

/**
 *  Returns the dot (inner) product of this vector and rhs.
 */
double vector2::dot(const vector2& rhs) const
{
  // TODO -- you fill in here to use the std::transform() method to
  // return the dot (inner) product of this vector and rhs.

  // transform will return the output iterator. Therefore, I hacked together
  // an iterator that will actually accumulate the values that are "assigned
  // to its content."
  double product = 0.0;
  vector2 outVec;
}

/**
 *  Returns the square of the magnitude of this vector.
 */
double vector2::normSq() const
{
    // Dot product of a vector with itself yields the square of the norm.
    return dot(*this);
}

/**
 *  Returns the magnitude of this vector.
 */
double vector2::norm() const
{
    return std::sqrt(normSq());
}

/**
 *  Returns a scaled copy of this vector such that its magnitude is 1
 *  Throw overflow_error if norm is zero
 */
vector2 vector2::normalize() const
{
    vector2 tmp(*this);
    double norm = tmp.norm();
    if (norm == 0)
        throw std::overflow_error("vector norm is zero");
    return tmp /= norm;
}

/***************************************************************************
 *                                                                          *
 *                  O V E R L O A D E D   O P E R A T O R S                 *
 *                                                                          *
 ***************************************************************************/

/**
 *  Returns true if this vector equals rhs.
 */
bool vector2::operator==(const vector2& rhs) const
{
  // Can use either a functor or lambda
  // return std::equal(begin(), end(), rhs.begin(), epsilonTest());
  return std::equal(begin(), end(), rhs.begin(), [](auto l, auto r) { return std::abs(r - l) < 0.0000000001; });
}

/**
 *  Returns true if this vector differs from rhs.
 */
bool vector2::operator!=(const vector2& rhs) const
{
    return !(*this == rhs);
}

/**
 *  Returns a reference to the index-th component of this vector. Not range
 *  checked.
 */
double& vector2::operator[](uint32_t index)
{
    return mData[index];
}

/**
 *  Returns a reference to the index-th component of this vector. Not range
 *  checked.
 */
const double& vector2::operator[](uint32_t index) const
{
    return mData[index];
}

/**
 *  Returns the sum of this vector and rhs.
 */
vector2 vector2::operator+(const vector2& rhs) const
{
    return add(rhs);
}

/**
 *  Returns the additive inverse of this vector.
 */
vector2 vector2::operator-() const
{
    return scale(-1.0);
}

/**
 *  Returns the difference between this vector and rhs.
 */
vector2 vector2::operator-(const vector2 & rhs) const
{
    return *this + -rhs;
}


/**
 *  Increments this vector by rhs and returns the result for chaining.
 */
vector2& vector2::operator+=(const vector2& rhs)
{
    return *this = *this + rhs;
}

/**
 *  Scales this vector by rhs and returns the result for chaining.
 */
vector2& vector2::operator*=(const double& rhs)
{
    return *this = scale(rhs);
}

/**
 *  Scales this vector by 1.0 / rhs and returns the result for chaining.
 */
vector2& vector2::operator/=(const double& rhs)
{
    return *this *= (1.0 / rhs);
}

/**
 *  Scales a copy of this vector by 1.0 / rhs and returns the result.
 */
vector2 vector2::operator/(const double& rhs) const
{
  return scale(1.0 / rhs);
}

/**
 *  Returns the dot (inner) product of this vector and rhs.
 */
double vector2::operator*(const vector2& rhs) const
{
    return dot(rhs);
}

/**
 *  Scales a copy of this vector by rhs and returns the result.
 */
vector2 vector2::operator*(const double& rhs) const
{
  return scale(rhs);
}

double* vector2::begin()
{

}

const double* vector2::begin() const
{

}

double* vector2::end()
{

}

const double* vector2::end() const
{

}

/**
 *  Returns the result of scaling a copy of v by scale. This free function
 *  guarantees that vector scaling is commutative.
 */
vector2 operator*(const double& scale, const vector2& v)
{
    return v * scale;
}
