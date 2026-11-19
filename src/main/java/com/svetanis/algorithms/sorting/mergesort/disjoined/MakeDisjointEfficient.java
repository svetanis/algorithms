package com.svetanis.algorithms.sorting.mergesort.disjoined;

import java.util.HashSet;
import java.util.Set;

// Make two arrays disjoint (GeeksforGeeks; no LeetCode number)
//
// Input: two int arrays, unsorted, repeats allowed. Returns how many numbers must be deleted from
// a2 so that no value of a2 is in a1: every element of a2 whose value is in a1, each repeat counted.
// The arrays are not changed.
//
// The one idea: put the values of a1 into a hash set, which answers "is this value in a1?" in
// constant time, then count the elements of a2 for which the answer is yes. Deleting exactly
// those elements leaves a2 with nothing in common with a1.
//
// Time: O(n + m) -- n insertions and m lookups, each constant on average.
// Space: O(n) -- the set.
//
// Siblings: DisjointSetHashing -- the same set, but it stops at the first shared value and
// answers true or false; DisjointSetBinary, DisjointSetMerging -- the same question by sorting.

public final class MakeDisjointEfficient {

  public static int disjoint(int[] a1, int[] a2) {
    int m = a2.length;

    Set<Integer> set = new HashSet<>(); // every value of a1
    for (int number : a1) { // RECORD a1
      set.add(number);
    }
    int count = 0;
    for (int i = 0; i < m; i++) { // COUNT the elements of a2 whose value is in a1
      if (set.contains(a2[i])) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a1 = { 20, 21, 22 };
    int[] a2 = { 22, 23, 24, 25, 20 };
    System.out.println(disjoint(a1, a2)); // 2
  }
}
