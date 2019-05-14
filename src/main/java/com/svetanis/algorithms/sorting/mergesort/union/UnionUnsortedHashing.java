package com.svetanis.algorithms.sorting.mergesort.union;

import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.Set;

import com.google.common.collect.ImmutableList;

public final class UnionUnsortedHashing {

  public static ImmutableList<Integer> union(int[] a1, int[] a2) {
    // Time Complexity: O(n + m)

    int n = a1.length;
    int m = a2.length;
    Set<Integer> set = newHashSet();

    for (int i = 0; i < n; i++) {
      set.add(a1[i]);
    }

    for (int i = 0; i < m; i++) {
      set.add(a2[i]);
    }
    return newList(set);
  }

  public static void main(String[] args) {
    int[] a1 = { 7, 1, 5, 2, 3, 6 };
    int[] a2 = { 3, 8, 6, 20, 7 };

    print(union(a1, a2));
  }
}