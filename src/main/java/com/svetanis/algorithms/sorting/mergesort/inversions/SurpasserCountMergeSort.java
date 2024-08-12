package com.svetanis.algorithms.sorting.mergesort.inversions;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.copyOf;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

// given an array of distinct integers, 
// for each element find its surpasser count
// i.e. count the number of elements to the
// right that are greater than that element

public final class SurpasserCountMergeSort {

  public static ImmutableList<Pair<Integer, Integer>> surpasser(int[] a) {
    int n = a.length;
    Map<Integer, Integer> map = newHashMap();
    int[] copy = copyOf(a, n);
    mergeSort(copy, 0, n - 1, map);
    return pairs(a, map);
  }

  private static ImmutableList<Pair<Integer, Integer>> pairs(int[] a, Map<Integer, Integer> map) {
    int n = a.length;
    List<Pair<Integer, Integer>> list = newArrayList();
    for (int i = 0; i < n - 1; i++) {
      int superpasser = n - 1 - i - map.get(a[i]);
      list.add(Pair.build(a[i], superpasser));
    }
    return newList(list);
  }

  private static void mergeSort(int[] a, int left, int right, Map<Integer, Integer> map) {
    // Time Complexity: O(n log n)

    if (right > left) {
      int mid = left + (right - left) / 2;
      mergeSort(a, left, mid, map);
      mergeSort(a, mid + 1, right, map);
      merge(a, left, mid + 1, right, map);
    }
  }

  private static void merge(int[] a, int left, int mid, int right, Map<Integer, Integer> map) {
    int count = 0;
    int i = left; // i is index for left subarray
    int j = mid; // i is index for right subarray
    int k = left; // i is index for resultant merged subarray

    int[] temp = new int[mid + right];
    while ((i <= mid - 1) && (j <= right)) {
      if (a[i] <= a[j]) {
    	int freq = map.getOrDefault(a[i], 0);
    	map.put(a[i], freq + count);
        temp[k++] = a[i++];
      } else {
        temp[k++] = a[j++];
        count++;
      }
    }

    while (i <= mid - 1) {
   	  int freq = map.getOrDefault(a[i], 0);
      map.put(a[i], freq + count);
      temp[k++] = a[i++];
    }

    while (j <= right) {
      temp[k++] = a[j++];
    }

    for (i = left; i <= right; i++) {
      a[i] = temp[i];
    }
  }

  public static void main(String[] args) {
    // int[] a = {2, 4, 1, 3, 5};
    int[] a = { 2, 7, 5, 3, 0, 8, 1 };
    print(surpasser(a));
  }
}