package com.svetanis.algorithms.search.quickselect;

import static com.svetanis.algorithms.sorting.quicksort.impl.Partition.reversePartition;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Random.randomIndex;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.toObject;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class TopK {

  public static ImmutableList<Integer> select(int[] a, int k) {
    // Time complexity: O(n)
    // Worst case: O(n^2)

    int n = a.length;
    int pivot = select(a, 0, n - 1, k);
    List<Integer> list = asList(toObject(a));
    return newList(list.subList(0, pivot + 1));
  }

  private static int select(int[] a, int left, int right, int k) {
    int random = randomIndex(left, right);
    int pivot = reversePartition(a, left, right, random);
    int dist = pivot - left + 1;
    if (dist == k) {
      return pivot;
    } else if (dist > k) {
      return select(a, left, pivot - 1, k);
    } else {
      return select(a, pivot + 1, right, k - dist);
    }
  }

  public static void main(String[] args) {
    int[] a = { 6, 7, 3, 1, 5, 9, 11 };
    System.out.println(select(a, 4));
  }
}