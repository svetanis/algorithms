package com.svetanis.algorithms.slidingwindow.queue;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.Deque;
import java.util.List;

import com.google.common.collect.ImmutableList;

// Given an array and an integer k, 
// find the maximum for each and 
// every contiguous subarray of size k.

public final class SlidingWindowMaxDeque {

  private static ImmutableList<Integer> subArrayMax(int[] a, int w) {
    // Time complexity: O(n);
    // Auxiliary Space: O(k)

    int n = a.length;
    Deque<Integer> q = newLinkedList();
    List<Integer> list = newArrayList();

    for (int i = 0; i < w; ++i) {
      windowMax(q, a, i);
    }

    for (int i = w; i < n; ++i) {
      // max element of prev window
      int front = q.getFirst();
      list.add(a[front]);

      // remove the elements
      // which are out of this window
      while (!q.isEmpty() && q.getFirst() <= i - w) {
        q.removeFirst();
      }
      windowMax(q, a, i);
    }
    // max element of last window
    list.add(a[q.getFirst()]);
    return newList(list);
  }

  private static void windowMax(Deque<Integer> q, int[] a, int i) {
    while (!q.isEmpty() && a[q.getLast()] <= a[i]) {
      System.out.println(a[i] + " " + q.getLast());
      q.removeLast();
    }
    q.addLast(i);
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, -1, -3, 5, 3, 6, 7 };
    print(subArrayMax(a, 3));

    int[] a1 = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
    print(subArrayMax(a1, 3));

    int[] a2 = { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
    print(subArrayMax(a2, 4));

    int[] a3 = { 6, 0, -6 };
    print(subArrayMax(a3, 2));
  }
}