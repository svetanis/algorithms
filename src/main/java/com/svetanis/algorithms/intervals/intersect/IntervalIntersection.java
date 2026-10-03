package com.svetanis.algorithms.intervals.intersect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.svetanis.algorithms.intervals.Interval;

// 986. Interval List Intersections
//
// Takes two lists of closed intervals [start, end] (both ends included). Within each list the
// intervals are disjoint and sorted by start; either list may be empty. Returns every stretch
// covered by both lists, sorted by start. Intervals that only touch share a single point:
// [1, 3] and [3, 5] give [3, 3].
//
// The shared part of two intervals begins once both have begun and ends as soon as either ends:
//   start = max(a.start, b.start)
//   end = min(a.end, b.end)
// and it exists only if start <= end -- whichever of the two starts first. Keep one index in each
// list. After comparing the two current intervals, move past the one that ends first: every later
// interval in the other list starts after the other current one ends, so after this one ends too,
// and none of them can reach back to it. The one that ends later stays, since it may still overlap
// the next interval on the side that moved.
//
// Two methods, the same walk:
//   intersection           int[][] as LC gives it; the overlap test is start <= end
//   intersectionFourTests  List<Interval>; the overlap test written out as four booleans -- does
//                          either interval's start fall inside the other? On every well-formed
//                          pair the two tests agree. They differ only on a malformed interval
//                          (start > end): [0, 1] against [1, 0] passes the four booleans, and
//                          start <= end rejects it.

public final class IntervalIntersection {
  // Time Complexity: O(n + m), each round moves one of the two indices forward; no sort
  // Space Complexity: O(n + m) for the output

  public static int[][] intersection(int[][] firstList, int[][] secondList) {
    int n = firstList.length;
    int m = secondList.length;
    int i = 0; // POINT: one index per list
    int j = 0;
    List<int[]> intersected = new ArrayList<>();
    while (i < n && j < m) { // STOP: either list used up -> nothing left to share
      int[] first = firstList[i];
      int[] second = secondList[j];
      int start = Math.max(first[0], second[0]); // SHARE: both have begun
      int end = Math.min(first[1], second[1]); //        either has ended
      if (start <= end) { // KEEP: <=, a single shared point counts
        intersected.add(new int[] { start, end });
      }
      if (first[1] < second[1]) { // RETIRE: the one that ends first is used up -- its own index
        i++;
      } else { // a tie moves j; first meets the next second, keeps nothing, then moves
        j++;
      }
    }
    return intersected.toArray(new int[intersected.size()][]); // RETURN
  }

  public static List<Interval> intersectionFourTests(List<Interval> list1, List<Interval> list2) {
    int n = list1.size();
    int m = list2.size();
    int i = 0;
    int j = 0;
    List<Interval> list = new ArrayList<>();
    while (i < n && j < m) {
      Interval interval1 = list1.get(i);
      Interval interval2 = list2.get(j);
      // overlap: interval1 starts inside interval2, or interval2 starts inside interval1
      boolean one = interval1.start >= interval2.start;
      boolean two = interval1.start <= interval2.end;
      boolean three = interval2.start >= interval1.start;
      boolean four = interval2.start <= interval1.end;
      if ((one && two) || (three && four)) {
        int start = Math.max(interval1.start, interval2.start);
        int end = Math.min(interval1.end, interval2.end);
        list.add(new Interval(start, end));
      }
      if (interval1.end < interval2.end) { // move past the one that ends first
        i++;
      } else {
        j++;
      }
    }
    return list;
  }

  public static void main(String[] args) {
    int[][] firstList = { { 0, 2 }, { 5, 10 }, { 13, 23 }, { 24, 25 } };
    int[][] secondList = { { 1, 5 }, { 8, 12 }, { 15, 24 }, { 25, 26 } };
    // [[1, 2], [5, 5], [8, 10], [15, 23], [24, 24], [25, 25]]
    System.out.println(Arrays.deepToString(intersection(firstList, secondList)));

    // one list empty: the loop never runs -> []
    System.out.println(Arrays.deepToString(intersection(new int[][] { { 1, 3 }, { 5, 9 } }, new int[0][])));

    List<Interval> list1 = new ArrayList<>();
    list1.add(new Interval(1, 3));
    list1.add(new Interval(5, 6));
    list1.add(new Interval(7, 9));
    List<Interval> list2 = new ArrayList<>();
    list2.add(new Interval(2, 3));
    list2.add(new Interval(5, 7));
    System.out.println(intersectionFourTests(list1, list2)); // [[2, 3], [5, 6], [7, 7]]

    // malformed [1, 0]: the four booleans call it an overlap, start <= end does not
    System.out.println(intersectionFourTests(List.of(new Interval(0, 1)), List.of(new Interval(1, 0)))); // [[1, 0]]
    System.out.println(Arrays.deepToString(intersection(new int[][] { { 0, 1 } }, new int[][] { { 1, 0 } }))); // []
  }
}
