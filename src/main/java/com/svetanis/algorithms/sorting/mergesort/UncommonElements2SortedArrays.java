package com.svetanis.algorithms.sorting.mergesort;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given two sorted arrays of distinct elements
// find elements from both arrays that are not 
// common in sorted order

public final class UncommonElements2SortedArrays {
  // Time Complexity: O(n + m)
	
  public static ImmutableList<Integer> uncommon(int[] a1, int[] a2) {
    int n = a1.length;
    int m = a2.length;
    int i = 0, j = 0;
    
    List<Integer> list = newArrayList();
    while (i < n && j < m) {
      if (a1[i] < a2[j]) {
        list.add(a1[i++]);
      } else if (a2[j] < a1[i]) {
        list.add(a2[j++]);
      } else {
        i++;
        j++;
      }
    }

    while (i < n) {
      list.add(a1[i++]);
    }
    while (j < m) {
      list.add(a2[j++]);
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[] a1 = {10, 20, 30};
    int[] a2 = {20, 25, 30, 40, 50};
    print(uncommon(a1, a2));
  }
}
