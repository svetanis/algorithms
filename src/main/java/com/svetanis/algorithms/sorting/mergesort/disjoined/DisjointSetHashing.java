package com.svetanis.algorithms.sorting.mergesort.disjoined;

import java.util.HashSet;
import java.util.Set;

// Are two arrays disjoint? By hashing (GeeksforGeeks; no LeetCode number)
//
// Input: two int arrays, unsorted, repeats allowed. Returns true when no value is in both. The
// arrays are not changed.
//
// The one idea: put every number of a1 into a hash set, which answers "is this value in a1?" in
// constant time. Then ask that of each number of a2; the first yes means a shared value. No
// sorting is needed.
//
// Time: O(n + m) -- n insertions and m lookups, each constant on average.
// Space: O(n) -- the set.
//
// Siblings: DisjointSetBinary -- sort both and binary-search; DisjointSetMerging -- sort both and
// walk them together; MakeDisjointEfficient -- the same set, counting the shared values.

public final class DisjointSetHashing {

  public static boolean disjoint(int[] a1, int[] a2) {
    int n = a1.length;
    int m = a2.length;

    Set<Integer> set = new HashSet<>(); // every value of a1

    for (int i = 0; i < n; i++) { // RECORD a1
      set.add(a1[i]);
    }

    for (int j = 0; j < m; j++) { // TEST each number of a2
      if (set.contains(a2[j])) {
        return false;
      }
    }
    return true; // RETURN: nothing was found
  }

  public static void main(String[] args) {
    int[] set1 = { 12, 34, 11, 9, 3 };
    int[] set2 = { 2, 1, 3, 5 };

    System.out.println(disjoint(set1, set2)); // false

    int[] set3 = { 12, 34, 11, 9, 3 };
    int[] set4 = { 7, 2, 1, 5 };

    System.out.println(disjoint(set3, set4)); // true
  }
}