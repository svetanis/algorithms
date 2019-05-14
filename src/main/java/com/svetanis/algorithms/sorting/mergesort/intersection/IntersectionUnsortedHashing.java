package com.svetanis.algorithms.sorting.mergesort.intersection;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;

public final class IntersectionUnsortedHashing {

  public static ImmutableList<Integer> intersection(int[] a1, int[] a2) {
    // Time Complexity: O(n + m)

    int n = a1.length;
    int m = a2.length;
    Set<Integer> hash = newHashSet();
    List<Integer> list = newArrayList();

    for (int i = 0; i < n; i++) {
      hash.add(a1[i]);
    }

    for (int i = 0; i < m; i++) {
      if (hash.contains(a2[i]))
        list.add(a2[i]);
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[] a1 = { 7, 1, 5, 2, 3, 6 };
    int[] a2 = { 3, 8, 6, 20, 7 };

    print(intersection(a1, a2));
  }
}