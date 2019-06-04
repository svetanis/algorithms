package com.svetanis.algorithms.search.quickselect.median;

import static com.svetanis.algorithms.search.quickselect.median.MedianUnsortedMinSelect.minSelect;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Math.abs;

import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// Design an O(n) time algorithm to compute the k elements closest to the median of an array A.

public final class ClosestToMedian {

  public static ImmutableList<Integer> closestToMedian(int[] a, int k) {
    int n = a.length;
    int median = minSelect(a, n / 2);
    return closest(a, k, median);
  }

  private static ImmutableList<Integer> closest(int[] a, int k, int median) {
    int n = a.length;
    Queue<Integer> pq = init(a, k);
    for (int i = k; i < n; ++i) {
      if (abs(median - pq.peek()) > abs(median - a[i])) {
        pq.poll();
        pq.offer(a[i]);
      }
    }
    return newList(pq);
  }

  private static Queue<Integer> init(int[] a, int k) {
    Queue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < k; ++i) {
      pq.offer(a[i]);
    }
    return pq;
  }

  public static void main(String[] args) {
    int[] a = { 7, 14, 10, 12, 2, 11, 29, 3, 4 };
    System.out.println(closestToMedian(a, 5));
  }
}
