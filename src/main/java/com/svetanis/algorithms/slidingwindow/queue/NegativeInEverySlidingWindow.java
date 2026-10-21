package com.svetanis.algorithms.slidingwindow.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// First negative integer in every window of size k (GeeksforGeeks; no LeetCode number)
//
// An array a and a window size w, 1 <= w <= a.length. Returns, for each window of w neighbours
// from left to right, the first negative number in it, or 0 when the window has none.
//
// The first negative of a window is the oldest negative still inside it. So keep the indexes of
// the negatives, oldest at the front, in a queue: a new negative joins at the back, and the
// front leaves once it slides out of the window. The front is then always the answer. Positive
// numbers never matter, so they never join.
//
// Siblings: none for this problem. The same queue that keeps only the numbers that can still
// be an answer is SlidingWindowMaxDeque / SlidingWindowMaxSubmit (LC 239), where an older
// number is also thrown out from the back.

public final class NegativeInEverySlidingWindow {
  // Time complexity: O(n), each index joins the queue at most once and leaves it at most once
  // Space complexity: O(w) for the queue, it holds indexes inside one window;
  // O(n - w + 1) for the returned list

  public static List<Integer> sw(int[] a, int w) {
    int n = a.length;

    Deque<Integer> dq = new ArrayDeque<>(); // indexes of the window's negatives, oldest first
    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < w; ++i) {
      if (a[i] < 0) {
        dq.addLast(i); // BUILD: the first window's negatives JOIN
      }
    }

    for (int i = w; i < n; ++i) {
      list.addAll(getList(dq, a)); // RECORD: the previous window, a[i - w .. i - 1]
      // remove elements which are out of this window
      while (!dq.isEmpty() && dq.getFirst() <= i - w) {
        dq.pollFirst(); // DROP: index i - w slid out
      }
      if (a[i] < 0) {
        dq.addLast(i); // JOIN: the newcomer is a negative
      }
    }
    list.addAll(getList(dq, a)); // RECORD: the last window
    return list;
  }

  private static List<Integer> getList(Deque<Integer> dq, int[] a) {
    List<Integer> list = new ArrayList<>();
    if (dq.isEmpty()) {
      list.add(0); // no negative in this window
    } else {
      list.add(a[dq.getFirst()]); // the oldest negative inside is the first one
    }
    return list;
  }

  private static void print(List<Integer> list) {
    StringBuilder sb = new StringBuilder();
    for (int value : list) {
      sb.append(value).append(' ');
    }
    System.out.println(sb);
  }

  public static void main(String[] args) {
    int[] a1 = { 12, -1, -7, 8, -15, 30, 16, 28 };
    print(sw(a1, 3)); // -1 -1 -7 -15 -15 0

    int[] a2 = { -8, 2, 3, -6, 10 };
    print(sw(a2, 2)); // -8 0 -6 -6
  }
}
