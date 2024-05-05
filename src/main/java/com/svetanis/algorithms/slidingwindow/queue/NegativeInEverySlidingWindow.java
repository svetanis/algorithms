package com.svetanis.algorithms.slidingwindow.queue;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.Deque;
import java.util.List;

import com.google.common.collect.ImmutableList;

// First negative integer in every window of size k

public final class NegativeInEverySlidingWindow {

  public static ImmutableList<Integer> sw(int[] a, int w) {
    // Time complexity: O(n)
    int n = a.length;

    Deque<Integer> dq = newLinkedList();
    List<Integer> list = newArrayList();

    for (int i = 0; i < w; ++i) {
      if (a[i] < 0) {
        dq.addLast(i);
      }
    }

    for (int i = w; i < n; ++i) {
      list.addAll(getList(dq, a));
      // remove elements which are out of this window
      while (!dq.isEmpty() && dq.getFirst() <= i - w) {
        dq.pollFirst();
      }
      if (a[i] < 0) {
        dq.addLast(i);
      }
    }
    list.addAll(getList(dq, a));
    return newList(list);
  }
  
  private static ImmutableList<Integer> getList(Deque<Integer> dq, int[] a) {
    List<Integer> list = newArrayList();
    if (dq.isEmpty()) {
      list.add(0);
    } else {
      list.add(a[dq.getFirst()]);
    }
    return newList(list);
  }

  public static void main(String[] args) {
    int[] a1 = { 12, -1, -7, 8, -15, 30, 16, 28 };
    print(sw(a1, 3));

    int[] a2 = { -8, 2, 3, -6, 10 };
    print(sw(a2, 2));
  }
}
