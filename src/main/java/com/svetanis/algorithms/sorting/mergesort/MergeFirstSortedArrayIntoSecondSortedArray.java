package com.svetanis.algorithms.sorting.mergesort;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class MergeFirstSortedArrayIntoSecondSortedArray {

  public static ImmutableList<Integer> merge(List<Integer> larger, List<Integer> smaller) {
    // Time complexity: O(n)
    // Auxiliary Space: O(1)
    // Space Complexity: O(n) due to input size

    int n = larger.size();
    int m = smaller.size();

    int i = n - 1; // current index of input part of largerArray
    int j = m - 1; // current index of smallerArray
    int k = n + m - 1; // current index of output part of largerArray

    ensureSize(larger, k + 1);

    while (j >= 0) {
      if (i >= 0 && larger.get(i) > smaller.get(j)) {
        larger.set(k, larger.get(i));
        i--;
      } else {
        larger.set(k, smaller.get(j));
        j--;
      }
      k--;
    }
    return newList(larger);
  }

  public static void ensureSize(List<Integer> list, int size) {
    while (list.size() < size) {
      list.add(null);
    }
  }

  public static void main(String[] args) {
    List<Integer> smaller = newArrayList(1, 3, 5);
    List<Integer> larger = newArrayList(2, 4, 6);
    print(merge(larger, smaller));

    List<Integer> small = newArrayList(2);
    List<Integer> large = newArrayList(-4, -3, 0);
    print(merge(large, small));
  }
}
