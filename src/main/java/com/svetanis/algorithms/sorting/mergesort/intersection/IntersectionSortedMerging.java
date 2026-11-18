package com.svetanis.algorithms.sorting.mergesort.intersection;

import java.util.ArrayList;
import java.util.List;

// Intersection of two sorted arrays, by merging (GeeksforGeeks; LeetCode 350, Intersection of Two
// Arrays II, is the unsorted version with the same repeat rule)
//
// Input: two arrays a1 and a2, each sorted in non-decreasing order; repeats allowed. Returns the
// values that are in both, in sorted order, each as many times as the array with fewer copies of
// it holds.
//
// The one idea: i points at the smallest number of a1 not yet looked at and j at the smallest of
// a2. If a1[i] is smaller than a2[j], it cannot be in what is left of a2 (all of it is at least
// a2[j]), so move i; the same for a2[j] smaller. Equal fronts are in both: take one copy and move
// both, so a value with 2 copies in one array and 3 in the other is taken twice.
//
// Time: O(n + m) -- each index only moves right.
// Space: O(1) -- two indices; the returned list is the output.
//
// Siblings: IntersectionSortedBinarySearch -- a binary search per number of the shorter array,
// each common value once.

public final class IntersectionSortedMerging {

  public static List<Integer> intersection(int[] a1, int[] a2) {
    int n = a1.length;
    int m = a2.length;
    List<Integer> list = new ArrayList<>();
    int i = 0;
    int j = 0;
    while (i < n && j < m) {
      if (a1[i] < a2[j]) { // SKIP a1[i]: not in a2
        i++;
      } else if (a2[j] < a1[i]) { // SKIP a2[j]: not in a1
        j++;
      } else if (a1[i] == a2[j]) { // TAKE one copy, and move both
        list.add(a1[i]);
        i++;
        j++;
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
    System.out.println(intersection(a3, a4)); // [2, 5, 5]
  }
}