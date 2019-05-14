package com.svetanis.algorithms.sorting.mergesort.intersection;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class IntersectionSortedMerging {

  public static ImmutableList<Integer> intersection(int[] a1, int[] a2) {
    // Time complexity: O(n + m), n ~ m

    int n = a1.length;
    int m = a2.length;
    List<Integer> list = newArrayList();
    int i = 0;
    int j = 0;
    while (i < n && j < m) {
      if (a1[i] < a2[j]) {
        i++;
      } else if (a2[j] < a1[i]) {
        j++;
      } else if (a1[i] == a2[j]) {
        list.add(a1[i]);
        i++; j++;
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 4, 5, 6, 8 };
    int[] a2 = { 2, 3, 5, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
    print(intersection(a1, a2));
  }
}