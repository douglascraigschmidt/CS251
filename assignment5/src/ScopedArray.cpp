// @author G. Hemingway, copyright 2020 - All rights reserved

#ifndef SCOPEDARRAY_CPP
#define SCOPEDARRAY_CPP

template <class T>
ScopedArray<T>::ScopedArray(T* rhs)
{
}

template <typename T> ScopedArray<T>::~ScopedArray()
{
}

template <typename T> T* ScopedArray<T>::get() const
{
}

template <typename T> const T& ScopedArray<T>::operator[](std::uint32_t pos) const
{
}

template <typename T> T& ScopedArray<T>::operator[](std::uint32_t pos)
{
}

template <typename T> ScopedArray<T>::operator bool() const
{
}

template <typename T> void ScopedArray<T>::swap(ScopedArray& rhs)
{
}

template <typename T> T* ScopedArray<T>::release()
{
}

template <typename T> void ScopedArray<T>::reset(T* rhs)
{
}

#endif // SCOPEDARRAY_CPP
