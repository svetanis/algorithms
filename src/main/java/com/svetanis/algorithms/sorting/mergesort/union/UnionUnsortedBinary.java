package com.svetanis.algorithms.sorting.mergesort.union;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.isBinary;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.sort;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class UnionUnsortedBinary {

  public static ImmutableList<Integer> union(int[] a1, int[] a2) {
    // Time complexity: MIN( (m + n) log m, (m + n) log n)

    int n = a1.length;
    int m = a2.length;
    int[] smaller = null, larger = null;
    List<Integer> list = newArrayList();

    if (n < m) {
      sort(a1);
      for (int i = 0; i < n; i++) {
        list.add(a1[i]);
      }
      smaller = a1;
      larger = a2;
    } else {
      sort(a2);
      for (int i = 0; i < m; i++) {
        list.add(a2[i]);
      }
      smaller = a2;
      larger = a1;
    }

    for (int i = 0; i < larger.length; ++i) {
      boolean one = (i == 0 || larger[i] != larger[i - 1]);
      boolean found = isBinary(smaller, 0, smaller.length, larger[i]);
      if (one && !found) {
        list.add(larger[i]);
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[] a1 = { 7, 1, 5, 2, 3, 6 };
    int[] a2 = { 3, 8, 6, 20, 7 };

    print(union(a1, a2));
  }
}