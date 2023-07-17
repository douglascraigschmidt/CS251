// @author G. Hemingway, copyright 2020 - All rights reserved
//
#include "AllocationTracker.h"

uint32_t AllocationTracker::mCount = 0;
uint32_t AllocationTracker::mArrayCount = 0;

std::ostream& operator<<(std::ostream& stream, const AllocationTracker&)
{
    stream << AllocationTracker::getCount() << std::endl;
    return stream;
}
