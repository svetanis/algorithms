package com.svetanis.algorithms.sorting.mergesort.intersection;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.isBinary;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class IntersectionSortedBinarySearch {

  public static ImmutableList<Integer> intersection(int[] a1, int[] a2) {
    // Time complexity O(n log m), n << m
    
    int n = a1.length;
    int m = a2.length; 
    List<Integer> list = newArrayList(); 
    for (int i = 0; i < n; ++i) {
      boolean one = (i == 0 || a1[i] != a1[i - 1]); 
      boolean found = isBinary(a2, 0, m, a1[i]);
      if (one && found){
        list.add(a1[i]);
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