package com.svetanis.algorithms.sorting.mergesort.union;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class UnionSorted {

  public static ImmutableList<Integer> union(int[] a1, int[] a2) {
    // Time complexity: O(n + m)

    int n = a1.length;
    int m = a2.length;
    List<Integer> list = newArrayList(); 
    
    int i = 0, j = 0;
    while (i < n && j < m) {
      if (a1[i] < a2[j]) {
        list.add(a1[i]);
        i++;
      } else if (a2[j] < a1[i]) {
        list.add(a2[j]);
        j++;
      } else if (a1[i] == a2[j]) {
        list.add(a1[i]);
        i++; j++;
      }
    }
    // remaining elements
    while (i < n) {
      list.add(a1[i]);
      i++;
    }
    while (j < m) {
      list.add(a2[j]);
      j++;
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 4, 5, 6, 8 };
    int[] a2 = { 2, 3, 5, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
    print(union(a1, a2));
  }
}