// @author G. Hemingway, copyright 2020 - All rights reserved
//
#ifndef ALLOCATION_TRACKER_H
#define ALLOCATION_TRACKER_H

#include <cstdint>
#include <iostream>

class AllocationTracker {
public:
    AllocationTracker()
        : mJunk(0)
    {
        ++mCount;
    }

    AllocationTracker(const AllocationTracker&)
        : mJunk(0)
    {
        ++mCount;
    }

    ~AllocationTracker()
    {
        --mCount;
    }

    void nonConstMethod()
    {
        ++mJunk;
    }

    void* operator new(size_t)
    {
        throw std::logic_error("single object new invoked");
    }

    void* operator new[](size_t size)
    {
        ++mArrayCount;
        return ::operator new[](size);
    }

    void operator delete(void*)
    {
        // You should really never be here!!!
        exit(-1);
    }

    void operator delete[](void* ptr)
    {
        --mArrayCount;
        ::operator delete[](ptr);
    }

    static uint32_t getCount()
    {
        return mCount;
    }

    static uint32_t getArrayCount()
    {
        return mArrayCount;
    }

private:
    // Static object counters
    static uint32_t mCount;
    static uint32_t mArrayCount;
    // Instance junk counter
    uint32_t mJunk;
};

std::ostream& operator<<(std::ostream& stream, const AllocationTracker& obj);

#endif // ALLOCATION_TRACKER_H
