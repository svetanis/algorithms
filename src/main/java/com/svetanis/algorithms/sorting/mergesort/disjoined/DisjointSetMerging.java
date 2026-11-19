package com.svetanis.algorithms.sorting.mergesort.disjoined;

import static java.util.Arrays.sort;

// Are two arrays disjoint? By merging (GeeksforGeeks; no LeetCode number)
//
// Input: two int arrays, unsorted, repeats allowed. Returns true when no value is in both. Both
// arrays are sorted in place as a side effect.
//
// The one idea: after sorting, i points at the smallest number of a1 not yet looked at and j at the
// smallest of a2. If a1[i] is smaller than a2[j], it cannot be in a2 (everything left in a2 is at
// least a2[j]), so move i; the same for a2[j] smaller. Equal fronts are a shared value. If one
// array runs out first, nothing matched.
//
// Time: O(n log n + m log m) -- the sorts; the walk after them is only O(n + m).
// Space: O(log n + log m) -- the sorts' stacks.
//
// Siblings: DisjointSetBinary -- sort both, then a binary search per number of the shorter array;
// DisjointSetHashing -- a set of one array, no sorting; MakeDisjointEfficient -- counts the
// shared values instead.

public final class DisjointSetMerging {

  public static boolean disjoint(int[] a1, int[] a2) {
    int n = a1.length;
    int m = a2.length;

    sort(a1); // SORT both
    sort(a2);

    int i = 0;
    int j = 0;
    while (i < n && j < m) {
      if (a1[i] < a2[j]) { // SKIP a1[i]: not in a2
        i++;
      } else if (a2[j] < a1[i]) { // SKIP a2[j]: not in a1
        j++;
      } else { // a1[i] == a2[j]
        return false; // FOUND a shared value
      }
    }
    return true; // RETURN: one array ran out with no match
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