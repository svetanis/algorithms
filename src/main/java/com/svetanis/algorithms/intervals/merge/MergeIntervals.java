package com.svetanis.algorithms.intervals.merge;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.svetanis.algorithms.intervals.Interval;

// 56. Merge Intervals
//
// Takes intervals [start, end] in any order. Returns the fewest intervals that cover the same
// points, sorted by start. Intervals are closed, so two that only touch, [1, 4] and [4, 5], merge
// into [1, 5].
//
// Once the intervals are sorted by start, the next one either starts at or before the run's end,
// and then it belongs to the run and can only push the run's end further; or it starts after the
// run's end, and since every later interval starts later still, nothing can join the run any more,
// so the next interval starts a new run.
//
// The same walk, once per way the intervals arrive, so each source's form can be copied as is.
// Two places to keep the run being built:
//
//   method          takes                  the run is kept         input           empty input
//   merge           int[][] (LC)           as the last answer row  sorted, rows    throws
//                                                                  overwritten
//   mergeHeld       int[][] (LC)           in start and end        sorted          throws
//   mergeLists      List<List<Integer>>    as the last answer row  sorted, lists   throws
//                                                                  overwritten
//   mergeIntervals  List<Interval>         in start and end        not changed     empty list
//
// "Kept as the last answer row" widens that row in place. "Held in start and end" adds the run
// only once it is finished, so it needs one more add after the loop for the run still held.

public final class MergeIntervals {
  // Time Complexity: O(n log n), the sort; the scan after it is one pass
  // Space Complexity: O(n), the output and the sort's buffer (and the sorted copy in mergeIntervals)

  public static int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // by start
    List<int[]> list = new ArrayList<>();
    list.add(intervals[0]); // throws on an empty array
    for (int i = 1; i < intervals.length; i++) {
      int start = intervals[i][0];
      int end = intervals[i][1];
      int[] prev = list.get(list.size() - 1);
      // overlapping: extend the last interval
      if (start <= prev[1]) { // <=: touching intervals merge
        prev[1] = Math.max(prev[1], end); // a contained interval must not shorten the run
      } else {
        list.add(intervals[i]);
      }
    }
    return list.toArray(new int[list.size()][]);
  }

  // HOLD one merged run in start and end; TEST each next row against it, never against the row
  // before it -- that row may sit inside the run and end much earlier.
  public static int[][] mergeHeld(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // SORT by start: then a TEST is one comparison
    int start = intervals[0][0]; // HOLD: the merged run so far, starting as the first row
    int end = intervals[0][1];
    List<int[]> merged = new ArrayList<>();
    for (int i = 1; i < intervals.length; i++) { // from 1: intervals[0] is already held
      int currStart = intervals[i][0];
      int currEnd = intervals[i][1];
      if (end < currStart) { // TEST: <, not <= -- touching rows overlap
        merged.add(new int[] { start, end }); // CLOSE: no later row can reach back to it
        start = currStart; // hold this row instead
        end = currEnd;
      } else {
        end = Math.max(end, currEnd); // EXTEND: max -- currEnd may lie inside the run
      }
    }
    merged.add(new int[] { start, end }); // LAST: the run still held never got a CLOSE
    return merged.toArray(new int[merged.size()][]);
  }

  public static List<List<Integer>> mergeLists(List<List<Integer>> intervals) {
    intervals.sort((i1, i2) -> Integer.compare(i1.get(0), i2.get(0))); // by start
    List<List<Integer>> list = new ArrayList<>();
    list.add(intervals.get(0)); // throws on an empty list
    for (int i = 1; i < intervals.size(); i++) {
      List<Integer> prev = list.get(list.size() - 1);
      List<Integer> curr = intervals.get(i);
      if (curr.get(0) <= prev.get(1)) { // <=: touching intervals merge
        int end = Math.max(prev.get(1), curr.get(1)); // a contained interval must not shorten the run
        prev.set(1, end);
        list.set(list.size() - 1, prev); // a no-op: prev is already that element
      } else {
        list.add(curr);
      }
    }
    return list;
  }

  public static List<Interval> mergeIntervals(List<Interval> intervals) {
    if (intervals.isEmpty()) { // no first interval to start a run
      return new ArrayList<>();
    }
    List<Interval> list = new ArrayList<>();
    List<Interval> sorted = new ArrayList<>(intervals); // a copy: the caller's list keeps its order
    sorted.sort((a, b) -> Integer.compare(a.start, b.start));
    Iterator<Interval> iter = sorted.iterator();
    Interval interval = iter.next();
    int start = interval.start;
    int end = interval.end;
    while (iter.hasNext()) {
      Interval next = iter.next();
      int left = next.start;
      int right = next.end;
      if (left <= end) { // <=: touching intervals merge
        end = Math.max(end, right); // a contained interval must not shorten the run
      } else {
        list.add(new Interval(start, end));
        start = left;
        end = right;
      }
    }
    list.add(new Interval(start, end)); // the last run is never closed inside the loop
    return list;
  }

  public static void main(String[] args) {
    // fresh input for each call: merge, mergeHeld and mergeLists sort their argument
    // [[1, 6], [8, 10], [15, 18]], four times
    System.out.println(Arrays.deepToString(merge(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } })));
    System.out.println(Arrays.deepToString(mergeHeld(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } })));
    System.out.println(mergeLists(lists(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } })));
    System.out.println(mergeIntervals(intervals(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } })));

    // [[1, 5]], twice: touching intervals merge
    System.out.println(Arrays.deepToString(merge(new int[][] { { 1, 4 }, { 4, 5 } })));
    System.out.println(mergeIntervals(intervals(new int[][] { { 1, 4 }, { 4, 5 } })));

    // [[1, 10], [12, 14]], twice: an interval inside an earlier run catches a test against the neighbour
    System.out.println(Arrays.deepToString(mergeHeld(new int[][] { { 1, 10 }, { 2, 3 }, { 4, 5 }, { 12, 14 } })));
    System.out.println(mergeLists(lists(new int[][] { { 1, 10 }, { 2, 3 }, { 4, 5 }, { 12, 14 } })));

    // [[1, 9]], twice: [2, 4] sorts second and ends early -- without the max the run would shrink
    System.out.println(mergeLists(lists(new int[][] { { 6, 8 }, { 1, 9 }, { 2, 4 }, { 4, 7 } })));
    System.out.println(mergeIntervals(intervals(new int[][] { { 6, 8 }, { 1, 9 }, { 2, 4 }, { 4, 7 } })));

    System.out.println(mergeIntervals(new ArrayList<>())); // []: the one method that takes no input
  }

  private static List<List<Integer>> lists(int[][] rows) {
    List<List<Integer>> list = new ArrayList<>();
    for (int[] row : rows) {
      list.add(new ArrayList<>(List.of(row[0], row[1])));
    }
    return list;
  }

  private static List<Interval> intervals(int[][] rows) {
    List<Interval> list = new ArrayList<>();
    for (int[] row : rows) {
      list.add(new Interval(row[0], row[1]));
    }
    return list;
  }
}
