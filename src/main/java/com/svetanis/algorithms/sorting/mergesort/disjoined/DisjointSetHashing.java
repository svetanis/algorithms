package com.svetanis.algorithms.sorting.mergesort.disjoined;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

public final class DisjointSetHashing {

  public static boolean disjoint(int[] a1, int[] a2) {
    // Time complexity: O(n + m)
    // Space complexity: O(n)

    int n = a1.length;
    int m = a2.length;

    Set<Integer> set = newHashSet();

    for (int i = 0; i < n; i++) {
      set.add(a1[i]);
    }

    for (int j = 0; j < m; j++) {
      if (set.contains(a2[j])) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[] set1 = { 12, 34, 11, 9, 3 };
    int[] set2 = { 2, 1, 3, 5 };

    System.out.println(disjoint(set1, set2));

    int[] set3 = { 12, 34, 11, 9, 3 };
    int[] set4 = { 7, 2, 1, 5 };

    System.out.println(disjoint(set3, set4));
  }
}