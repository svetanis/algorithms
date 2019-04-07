package com.svetanis.algorithms.sorting.heapsort;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

public final class ApproximateSort {

  public static ImmutableList<Integer> sort(int[] a, int k) {
    // Time Complexity: O(n log k)
    // Auxiliary Space: O(n)

    int n = a.length;
    Queue<Integer> queue = init(a, k);
    List<Integer> list = newArrayList();
    for (int i = k; i < n; i++) {
      queue.offer(a[i]);
      list.add(queue.poll());
    }

    // extract the remaining elements in queue
    while (!queue.isEmpty()) {
      list.add(queue.poll());
    }
    return newList(list);
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
    print(sort(a, 3));
  }

}
