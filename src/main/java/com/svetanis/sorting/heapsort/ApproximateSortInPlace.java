package com.svetanis.sorting.heapsort;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public final class ApproximateSortInPlace {

  public static void sort(int[] a, int k) {
    // Time Complexity: O(n log k)
    // Auxiliary Space: O(k)

    int n = a.length;
    Queue<Integer> queue = init(a, k);
    List<Integer> list = newArrayList();
    for (int i = k, j = 0; j < n; i++, j++) {
      a[j] = queue.poll();
      if (i < n) {
        queue.offer(a[i]);
      }
    }

    // extract the remaining elements in queue
    while (!queue.isEmpty()) {
      list.add(queue.poll());
    }
  }

  private static Queue<Integer> init(int[] a, int k) {
    Queue<Integer> queue = new PriorityQueue<>();
    // push first k elements into queue
    for (int i = 0; i < k; ++i) {
      queue.offer(a[i]);
    }
    return queue;
  }

  public static void main(String[] args) {
    int[] a = { 2, 6, 3, 12, 56, 8 };
    sort(a, 3);
    print(a);
  }

}
