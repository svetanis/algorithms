package com.svetanis.algorithms.intervals.merge;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.svetanis.algorithms.intervals.Interval;

// 252. Meeting Rooms -- also Educative's "Conflicting Appointments", the same problem.
//
// Takes meetings [start, end] with start < end, in any order (LC: 0 <= start < end <= 10^6, and
// there may be no meetings, which gives true). Returns true when one person can attend them all,
// that is, when no two meetings overlap. A meeting that ends at 10 and one that starts at 10 do not
// overlap. Educative's examples: [[1,4],[2,5],[7,9]] false, [[6,7],[2,4],[8,12]] true,
// [[4,5],[2,3],[3,6]] false.
//
// Sort by start. If two meetings overlap at all, then some meeting overlaps the one right after it
// in sorted order: when meeting A overlaps a later-starting meeting C, the meeting B that comes
// right after A starts no earlier than A and no later than C, so B also starts before A ends.
// Comparing each meeting with its neighbour is therefore enough.
//
// The same check, once per way the meetings arrive, so each source's form can be copied as is:
//
//   method              takes                  the input
//   canAttend           int[][] (LC)           is sorted in place
//   canAttendLists      List<List<Integer>>    is sorted in place
//   canAttendIntervals  List<Interval>         is not changed: a sorted copy is checked

public final class MeetingRoom {
  // Time Complexity: O(n log n), the sort; the scan after it is one pass
  // Space Complexity: O(n), the sort's buffer (and the sorted copy in canAttendIntervals)

  public static boolean canAttend(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // by start
    for (int i = 1; i < intervals.length; i++) {
      int[] prev = intervals[i - 1];
      int[] curr = intervals[i];
      if (prev[1] > curr[0]) { // >: ending exactly when the next starts is fine
        return false;
      }
    }
    return true;
  }

  public static boolean canAttendLists(List<List<Integer>> intervals) {
    intervals.sort((i1, i2) -> Integer.compare(i1.get(0), i2.get(0))); // by start
    for (int i = 1; i < intervals.size(); i++) {
      List<Integer> prev = intervals.get(i - 1);
      List<Integer> curr = intervals.get(i);
      if (prev.get(1) > curr.get(0)) { // >: ending exactly when the next starts is fine
        return false;
      }
    }
    return true;
  }

  public static boolean canAttendIntervals(List<Interval> intervals) {
    List<Interval> sorted = new ArrayList<>(intervals); // a copy: the caller's list keeps its order
    sorted.sort((a, b) -> Integer.compare(a.start, b.start));
    for (int i = 1; i < sorted.size(); i++) {
      if (sorted.get(i).start < sorted.get(i - 1).end) { // <: touching is not a conflict
        return false; // two appointments overlap
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(canAttend(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } })); // false
    System.out.println(canAttend(new int[][] { { 7, 10 }, { 2, 4 } })); // true

    System.out.println(canAttendLists(lists(new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } }))); // false
    System.out.println(canAttendLists(lists(new int[][] { { 7, 10 }, { 2, 4 } }))); // true

    // Educative's three examples: false, true, false
    System.out.println(canAttendIntervals(intervals(new int[][] { { 1, 4 }, { 2, 5 }, { 7, 9 } })));
    System.out.println(canAttendIntervals(intervals(new int[][] { { 6, 7 }, { 2, 4 }, { 8, 12 } })));
    System.out.println(canAttendIntervals(intervals(new int[][] { { 4, 5 }, { 2, 3 }, { 3, 6 } })));
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
