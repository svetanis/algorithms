package com.svetanis.algorithms.intervals.select;


import java.util.ArrayList;
import java.util.List;

import com.svetanis.algorithms.intervals.Interval;

// Pick the most activities one person can do, one at a time.
//
// Takes a list of activities, each with a start and a finish time, in any order. Returns the
// chosen activities, ordered by finish time. An activity can follow the last chosen one only if
// it starts STRICTLY AFTER that one finishes: one starting at the exact moment the previous
// finishes cannot be taken. Empty input gives an empty list.
//
// Look at the activity that finishes first. Some best answer starts with it: take any best
// answer and swap its first activity for this one. The swap is safe, because this one finishes
// no later, so everything that came after in that answer still starts after it. Once it is
// taken, every activity that starts at or before its finish is ruled out, and what is left is the
// same problem on fewer activities. So: sort by finish time, take the first, then walk the list
// and take each activity that starts after the finish of the last one taken.
//
// Same greedy as NonOverlappingIntervals (LC 435), where touching intervals do not clash, so its
// test is >= and it returns a count; and MinNumOfArrowsToBurstBalloons (LC 452), whose test is this
// file's strict > -- the fewest arrows equals the most activities chosen here.

public final class ActivitySelection {
  // Time Complexity: O(n log n), the sort; the walk after it is one pass
  // Space Complexity: O(n), the sorted copy and the chosen list

  public static List<Interval> activities(List<Interval> intervals) {
    if (intervals.isEmpty()) {
      return new ArrayList<>();
    }
    // sorted here, not assumed: the greedy is only correct in finish-time order
    List<Interval> sorted = new ArrayList<>(intervals); // a copy: the caller's list keeps its order
    sorted.sort((a, b) -> Integer.compare(a.end, b.end));

    List<Interval> list = new ArrayList<>();
    list.add(sorted.get(0));
    for (int i = 1; i < sorted.size(); i++) {
      // strictly after: starting at the previous finish is a clash
      if (sorted.get(i).start > list.get(list.size() - 1).end) {
        list.add(sorted.get(i));
      }
    }
    return list;
  }

  public static void main(String[] args) {
    int[] start = { 1, 3, 0, 5, 8, 5 };
    int[] end = { 2, 4, 6, 7, 9, 9 };
    System.out.println(activities(build(start, end))); // [[1, 2], [3, 4], [5, 7], [8, 9]]

    // an activity starting exactly when another finishes cannot follow it,
    // so {10, 20} and {20, 30} cannot both be taken -- the answer is 1
    int[] touchStart = { 10, 12, 20 };
    int[] touchEnd = { 20, 25, 30 };
    System.out.println(activities(build(touchStart, touchEnd))); // [[10, 20]]
  }

  private static List<Interval> build(int[] start, int[] end) {
    List<Interval> list = new ArrayList<>();
    for (int i = 0; i < start.length; i++) {
      list.add(new Interval(start[i], end[i]));
    }
    return list;
  }
}
