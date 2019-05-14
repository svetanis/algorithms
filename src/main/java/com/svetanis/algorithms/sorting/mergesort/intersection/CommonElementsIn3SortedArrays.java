package com.svetanis.algorithms.sorting.mergesort.intersection;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class CommonElementsIn3SortedArrays {

  public static ImmutableList<Integer> common(int[] a1, int[] a2, int[] a3) {
    // Time complexity: O(n1 + n2 + n3)

    int n1 = a1.length;
    int n2 = a2.length;
    int n3 = a3.length;
    int i = 0, j = 0, k = 0;
    List<Integer> list = newArrayList();
    while (i < n1 && j < n2 && k < n3) {
      if (a1[i] == a2[j] && a2[j] == a3[k]) {
        list.add(a1[i]);
        i++;
        j++;
        k++;
      } else if (a1[i] < a2[j]) {
        i++;
      } else if (a2[j] < a3[k]) {
        j++;
      } else {
        k++;
      }
    }
    return copyOf(list);
  }

  public static void main(String[] args) {
    int[] ar1 = { 1, 5, 10, 20, 40, 80 };
    int[] ar2 = { 6, 7, 20, 80, 100 };
    int[] ar3 = { 3, 4, 15, 20, 30, 70, 80, 120 };
    print(common(ar1, ar2, ar3));

    int[] a1 = { 1, 5, 5 };
    int[] a2 = { 3, 4, 5, 5, 10 };
    int[] a3 = { 5, 5, 10, 20 };
    print(common(a1, a2, a3));
  }
}
