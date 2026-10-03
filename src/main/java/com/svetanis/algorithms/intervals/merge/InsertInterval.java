package com.svetanis.algorithms.intervals.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.svetanis.algorithms.intervals.Interval;

// 57. Insert Interval
//
// Takes intervals [start, end], no two overlapping, and one new interval. Returns every interval
// sorted by start, with the new one merged with all it overlaps. Intervals are closed, so one that
// only touches the new one is merged into it. The new interval is widened in place and is itself
// the merged entry in the answer.
//
// In sorted order the old intervals fall into three groups that follow one another. First, those
// that end before the new one starts: they are copied as they are. Next, those that start at or
// before the new one's end: each of these also ends at or after its start, because the first group
// stopped at the first one that did and later ones end later still, so each overlaps it and is
// absorbed into it. Last, those that start after its end: copied as they are.
//
// The same three loops, once per way the intervals arrive, so each source's form can be copied as is:
//
//   method           takes                                 the old intervals must be
//   insert           int[][], int[] (LC)                   sorted by start -- relied on, not checked
//   insertLists      List<List<Integer>>, List<Integer>    in any order: the list is sorted in place
//                                                          first, since non-overlapping is not sorted
//   insertIntervals  List<Interval>, Interval              sorted by start; the list is not changed

public final class InsertInterval {
  // Time Complexity: O(n), the three loops together pass over the input once; insertLists adds
  // the O(n log n) sort
  // Space Complexity: O(n), the output

  public static int[][] insert(int[][] intervals, int[] interval) {
    int i = 0;
    int n = intervals.length;
    List<int[]> list = new ArrayList<>();
    // add all intervals that come before the new interval
    while (i < n && intervals[i][1] < interval[0]) { // <: touching merges
      list.add(intervals[i]);
      i++;
    }
    // merge all intervals that overlap with new interval
    while (i < n && intervals[i][0] <= interval[1]) { // <=: touching merges
      int start = Math.min(intervals[i][0], interval[0]);
      int end = Math.max(intervals[i][1], interval[1]);
      interval[0] = start;
      interval[1] = end;
      i++;
    }
    list.add(interval); // insert new interval
    while (i < n) { // add all the remaining intervals
      list.add(intervals[i++]);
    }
    return list.toArray(new int[list.size()][]);
  }

  public static List<List<Integer>> insertLists(List<List<Integer>> intervals, List<Integer> interval) {
    intervals.sort((i1, i2) -> Integer.compare(i1.get(0), i2.get(0))); // by start
    List<List<Integer>> list = new ArrayList<>();
    int i = 0;
    int n = intervals.size();
    // add all intervals that come before the new interval
    while (i < n && intervals.get(i).get(1) < interval.get(0)) { // <: touching merges
      list.add(intervals.get(i));
      i++;
    }
    // merge all intervals that overlap with new interval
    while (i < n && intervals.get(i).get(0) <= interval.get(1)) { // <=: touching merges
      interval.set(0, Math.min(intervals.get(i).get(0), interval.get(0)));
      interval.set(1, Math.max(intervals.get(i).get(1), interval.get(1)));
      i++;
    }
    list.add(interval); // insert new interval
    while (i < n) { // add all the remaining intervals
      list.add(intervals.get(i++));
    }
    return list;
  }

  public static List<Interval> insertIntervals(List<Interval> intervals, Interval interval) {
    List<Interval> list = new ArrayList<>();
    int i = 0;
    // copy all intervals that come before the new interval
    while (i < intervals.size() && intervals.get(i).end < interval.start) { // <: touching merges
      list.add(intervals.get(i));
      i++;
    }
    // merge all intervals that overlap with new interval
    while (i < intervals.size() && intervals.get(i).start <= interval.end) { // <=: touching merges
      interval.start = Math.min(interval.start, intervals.get(i).start);
      interval.end = Math.max(interval.end, intervals.get(i).end);
      i++;
    }
    list.add(interval); // insert new interval
    while (i < intervals.size()) { // add all remaining intervals
      list.add(intervals.get(i++));
    }
    return list;
  }

  public static void main(String[] args) {
    // [[1, 5], [6, 9]], three times
    System.out.println(Arrays.deepToString(insert(new int[][] { { 1, 3 }, { 6, 9 } }, new int[] { 2, 5 })));
    System.out.println(insertLists(lists(new int[][] { { 6, 9 }, { 1, 3 } }), new ArrayList<>(List.of(2, 5)))); // any order
    System.out.println(insertIntervals(intervals(new int[][] { { 1, 3 }, { 6, 9 } }), new Interval(2, 5)));

    // [[1, 2], [3, 10], [12, 16]]
    System.out.println(Arrays.deepToString(insert(new int[][] { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } }, new int[] { 4, 8 })));

    System.out.println(insertLists(new ArrayList<>(), new ArrayList<>(List.of(5, 7)))); // [[5, 7]]
    System.out.println(insertIntervals(intervals(new int[][] { { 2, 3 }, { 5, 7 } }), new Interval(1, 4))); // [[1, 4], [5, 7]]
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
