package com.svetanis.algorithms.sorting.mergesort.disjoined;

import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.isBinary;
import static java.lang.Math.min;
import static java.util.Arrays.sort;

// Are two arrays disjoint? By binary search (GeeksforGeeks; no LeetCode number)
//
// Input: two int arrays, unsorted, repeats allowed. Returns true when no value is in both. Both
// arrays are sorted in place as a side effect.
//
// The one idea: two arrays are disjoint exactly when no number of one is found in the other. Sort
// both, then take each number of the shorter array and binary-search the longer one for it: a
// binary search needs a sorted array, and testing the shorter array's numbers means fewer
// searches.
//
// Time: O(n log n + m log m) -- the two sorts; the searches add O(min(n, m) * log max(n, m)),
//   which is no more.
// Space: O(log n + log m) -- the sorts' stacks and the recursion of the search.
//
// Siblings: DisjointSetMerging -- sort both, then one two-pointer pass; DisjointSetHashing -- a
// set of one array, no sorting; MakeDisjointEfficient -- counts the shared values instead.

public final class DisjointSetBinary {

  public static boolean disjoint(int[] a1, int[] a2) {
    sort(a1); // SORT both: binary search needs a sorted array
    sort(a2);

    int n = a1.length;
    int m = a2.length;

    int size = min(n, m);
    if (size == n) { // a1 is the shorter: look each of its numbers up in a2
      for (int i = 0; i < n; i++) {
        if (isBinary(a2, 0, m - 1, a1[i])) { // SEARCH a2[0..m - 1]: the end is the last index
          return false;
        }
      }
    } else { // a2 is the shorter
      for (int j = 0; j < m; j++) {
        if (isBinary(a1, 0, n - 1, a2[j])) { // SEARCH a1[0..n - 1]
          return false;
        }
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