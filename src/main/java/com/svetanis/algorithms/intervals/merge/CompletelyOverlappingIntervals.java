package com.svetanis.algorithms.intervals.merge;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.svetanis.algorithms.intervals.Interval;

// Does any interval lie entirely inside another?
//
// intervals is a list of Interval in any order, and it is not changed. Returns true when some
// interval [a, b] sits inside another [c, d], that is c <= a and b <= d. Sharing an endpoint
// still counts: [1, 3] lies inside [1, 7], and two identical intervals lie inside each other.
//
// Sort by start, and among equal starts put the longer interval first. Walk the sorted list and
// compare each end with the end just before it. If an end fails to move forward, that interval
// starts no earlier than its neighbour and ends no later, so it lies inside it. If every end
// moves forward, no interval lies inside another. A later interval ends later, so it is not
// inside an earlier one. An earlier interval could only be inside a later one with the same
// start; but among equal starts the longer comes first, so that later one would end no later,
// and the ends would not all have moved forward.
//
// RemoveCoveredIntervals (LC 1288) sorts the same way and asks a different question: not whether
// any interval lies inside another, but how many do not.

public final class CompletelyOverlappingIntervals {
  // Time Complexity: O(n log n), the sort; the scan after it is one pass
  // Space Complexity: O(n), the sorted copy

  // equal starts: longer first, so a shorter one comes right after the one that contains it
  private static final Comparator<Interval> BY_START_THEN_LONGEST = (a, b) -> a.start != b.start
      ? Integer.compare(a.start, b.start) // by start
      : Integer.compare(b.end, a.end); // equal starts: longer first (b before a)

  public static boolean isOverlap(List<Interval> intervals) {
    List<Interval> sorted = new ArrayList<>(intervals); // a copy: the caller's list keeps its order
    sorted.sort(BY_START_THEN_LONGEST);
    for (int i = 1; i < sorted.size(); i++) {
      int right = sorted.get(i).end;
      int prev = sorted.get(i - 1).end;
      // starts are non-decreasing, so an end that fails to advance means
      // this interval sits entirely inside the one before it
      if (right <= prev) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    List<Interval> list1 = new ArrayList<>();
    list1.add(new Interval(6, 8));
    list1.add(new Interval(1, 9));
    list1.add(new Interval(2, 4));
    list1.add(new Interval(4, 7));
    System.out.println(isOverlap(list1)); // true: [2, 4] inside [1, 9]

    List<Interval> list2 = new ArrayList<>();
    list2.add(new Interval(1, 3));
    list2.add(new Interval(1, 7));
    list2.add(new Interval(4, 8));
    list2.add(new Interval(2, 5));
    System.out.println(isOverlap(list2)); // true: [1, 3] inside [1, 7]

    List<Interval> list3 = new ArrayList<>();
    list3.add(new Interval(1, 3));
    list3.add(new Interval(7, 9));
    list3.add(new Interval(4, 6));
    list3.add(new Interval(10, 13));
    System.out.println(isOverlap(list3)); // false

    // equal starts, shorter one listed first -- the case the tie-break exists for
    List<Interval> list4 = new ArrayList<>();
    list4.add(new Interval(1, 3));
    list4.add(new Interval(5, 6));
    list4.add(new Interval(5, 8));
    System.out.println(isOverlap(list4)); // true: [5, 6] inside [5, 8]
  }
}
