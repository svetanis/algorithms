package com.svetanis.algorithms.intervals.select;


import java.util.Arrays;

// 435. Non-overlapping Intervals
//
// Takes intervals as [start, end] with start < end, as LC guarantees
// (-5 * 10^4 <= start < end <= 5 * 10^4, 1 <= n <= 10^5). Returns the fewest intervals to remove
// so that no two of the rest overlap. Two intervals that only touch, like [1, 2] and [2, 3], do
// not overlap. Needs at least one interval: both methods read intervals[0] and throw on an empty
// array, since LC 435 guarantees one. "What about empty input?" is the usual follow-up: say so
// when asked. Both sort the input array in place.
//
// Removing the fewest is the same as keeping the most. Look at the interval that ends first. Some
// best set of kept intervals includes it: take any best set and swap its first interval for this
// one, which ends no later, so it clashes with nothing the swapped one did not. Keeping it rules
// out every interval that starts before its end. So sort by end, keep the first, then walk the list
// and keep each interval that starts at or after the end of the last one kept.
//
// Sorting by start, or by length, instead of by end gives too many removals -- [1, 10], [2, 3],
// [4, 5] and [1, 5], [4, 7], [6, 10] each need 1, and both wrong orders still pass LC's examples.
//
// Two methods, the same walk: nonOverlappingIntervals counts the kept intervals and subtracts from
// n; count counts the removed ones directly. Every interval after the first is either kept or
// removed, so the two always agree.
//
// Same greedy as ActivitySelection, where an interval must start strictly after the last one
// chosen and the chosen intervals are returned, and MinNumOfArrowsToBurstBalloons (LC 452), which
// uses that same strict test because an arrow at a shared end bursts both balloons.

public final class NonOverlappingIntervals {
  // Time Complexity: O(n log n), the sort; the walk after it is one pass
  // Space Complexity: O(1) beyond the sort, which works on the input array

  public static int nonOverlappingIntervals(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1])); // SORT by END: ending first leaves the most room
    int n = intervals.length;
    int nonoverlaps = 1; // FIRST: the interval that ends first is always kept
    int prevEnd = intervals[0][1];
    for (int i = 1; i < n; i++) {
      if (intervals[i][0] >= prevEnd) { // TEST: >=, touching the last kept one is not a clash
        prevEnd = intervals[i][1]; // KEEP: no max needed -- sorted by end, this end is >= prevEnd
        nonoverlaps++;
      } // SKIP: one of the removed
    }
    return n - nonoverlaps; // RETURN: removed = n - kept
  }

  public static int count(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1])); // SORT by END
    int overlaps = 0;
    int end = intervals[0][1]; // FIRST: kept
    for (int i = 1; i < intervals.length; i++) {
      int start = intervals[i][0];
      if (start >= end) { // TEST: starts at or after the last kept end
        end = intervals[i][1]; // KEEP: it becomes the last kept
      } else {
        overlaps++; // SKIP: clashes with the last kept one, which ends no later -- remove this one
      }
    }
    return overlaps;
  }

  public static void main(String[] args) {
    // fresh arrays for each call: both methods sort their argument
    // 1, twice: remove [1, 3]
    System.out.println(nonOverlappingIntervals(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } }));
    System.out.println(count(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } }));

    // 2, twice: three copies of one interval, only one stays
    System.out.println(nonOverlappingIntervals(new int[][] { { 1, 2 }, { 1, 2 }, { 1, 2 } }));
    System.out.println(count(new int[][] { { 1, 2 }, { 1, 2 }, { 1, 2 } }));

    // 0, twice: touching is not overlapping
    System.out.println(nonOverlappingIntervals(new int[][] { { 1, 2 }, { 2, 3 } }));
    System.out.println(count(new int[][] { { 1, 2 }, { 2, 3 } }));

    // 1, twice: the inputs that catch sorting by start and sorting by length
    System.out.println(nonOverlappingIntervals(new int[][] { { 1, 10 }, { 2, 3 }, { 4, 5 } }));
    System.out.println(count(new int[][] { { 1, 5 }, { 4, 7 }, { 6, 10 } }));
  }
}
