package com.svetanis.algorithms.sorting.mergesort.intersection;

import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.isBinary;

import java.util.ArrayList;
import java.util.List;

// Intersection of two sorted arrays, by binary search (GeeksforGeeks; LeetCode 349, Intersection of
// Two Arrays, asks the same set for unsorted arrays)
//
// Input: two arrays a1 and a2, each sorted in non-decreasing order; repeats allowed. Returns the
// values that are in both, each once, in sorted order. a1 is the one whose numbers are looked up,
// so it should be the shorter array.
//
// The one idea: take each number of a1 in turn and binary-search a2 for it; a binary search is
// possible because a2 is sorted. Because a1 is sorted too, equal numbers sit side by side, so a
// number equal to the one before it is skipped and each value is reported once. The answer comes
// out in sorted order because a1 is read from the front.
//
// Time: O(n log m) -- n searches of O(log m) each; worth it when n is much smaller than m.
// Space: O(1) extra, plus O(log m) recursion of the search; the returned list is the output.
//
// Siblings: IntersectionSortedMerging -- one two-pointer pass, O(n + m), repeats kept as many
// times as both arrays hold them.

public final class IntersectionSortedBinarySearch {

  public static List<Integer> intersection(int[] a1, int[] a2) {
    int n = a1.length;
    int m = a2.length;
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      boolean one = (i == 0 || a1[i] != a1[i - 1]); // FIRST copy of this value in a1
      boolean found = isBinary(a2, 0, m - 1, a1[i]); // SEARCH a2[0..m - 1]: isBinary's end is the last index
      if (one && found) { // TAKE: in both arrays, and not reported yet
        list.add(a1[i]);
      }
    }
    return list; // RETURN
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 4, 5, 6, 8 };
    int[] a2 = { 2, 3, 5, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
    System.out.println(intersection(a1, a2)); // [2, 5]

    int[] a3 = { 2, 2, 5, 5 };
    int[] a4 = { 2, 5, 5, 5 };
    System.out.println(intersection(a3, a4)); // [2, 5]
  }
}