// @author G. Hemingway, copyright 2018 - All rights reserved
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
        throw "single object new invoked";
    }

    void* operator new[](size_t size)
    {
        return ::operator new[](size);
    }

    void operator delete(void*)
    {
        // You should really never be here!!!
        exit(-1);
    }

    void operator delete[](void* ptr)
    {
        ::operator delete[](ptr);
    }

    static uint32_t getCount()
    {
        return mCount;
    }

private:
    // Static object counter
    static uint32_t mCount;

    // Instance junk counter
    uint32_t mJunk;
};

std::ostream& operator<<(std::ostream& stream, const AllocationTracker& obj);

#endif // ALLOCATION_TRACKER_H
