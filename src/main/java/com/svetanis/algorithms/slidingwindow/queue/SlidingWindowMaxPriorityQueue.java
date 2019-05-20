package com.svetanis.algorithms.slidingwindow.queue;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.PriorityQueue;

// Sliding Window Maximum

public final class SlidingWindowMaxPriorityQueue {

  public static List<Integer> maxSlidingWindow(int[] a, int w) {
    // Time complexity: O(n log n)

    int n = a.length;
    PriorityQueue<Pair> queue = new PriorityQueue<>();
    List<Integer> list = newArrayList();
    for (int i = 0; i < w; ++i) {
      queue.offer(new Pair(a[i], i));
    }
    for (int i = w; i < n; ++i) {
      Pair p = queue.peek();
      list.add(p.value);
      while (p.index <= i - w) {
        queue.poll();
        p = queue.peek();
      }
      queue.offer(new Pair(a[i], i));
    }
    // max element of last window
    list.add(queue.peek().value);
    return list;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
    print(maxSlidingWindow(a1, 3));

    int[] a2 = { 6, 0, -6 };
    print(maxSlidingWindow(a2, 2));
  }

  private static class Pair implements Comparable<Pair> {
    int value;
    int index;

    Pair(int value, int index) {
      this.value = value;
      this.index = index;
    }

    @Override
    public int compareTo(Pair other) {
      return other.value - this.value;
    }
  }
}
