package com.svetanis.algorithms.twopointers.closest;

import java.util.Arrays;
import java.util.Stack;

// Maximum of minimums for every window length
//
// Input: an array of NON-NEGATIVE integers. result[] starts as all 0s, which a negative
// answer could never replace.
// Return: an array of n + 1 numbers whose slot w, for 1 <= w <= n, holds the largest
// minimum over all windows of w consecutive numbers. Slot 0 is not an answer; it comes out
// equal to slot 1.
//
// The one idea: turn the question around. For each a[i], find the LONGEST window in which it
// is the minimum: from just after the previous smaller number to just before the next
// smaller one, length right[i] - left[i] - 1. Two monotonic-stack scans give left[] and
// right[]. a[i] is then a candidate answer for that length. A window of length w holds
// windows of every shorter length whose minimum is at least as large, so the answer for w
// is also reachable for every shorter length: fill from the right with
// result[w] = max(result[w], result[w + 1]).
//
// Siblings -- the two scans on their own, recording values rather than positions:
//   twopointers.closest.PrevSmaller, twopointers.closest.NextSmaller
//
// Time: O(n) -- each scan pushes and pops every position at most once.
// Space: O(n) for the stacks and the arrays.

public final class MaxOfMinsStack {

  public static int[] maxOfMins(int[] a) {
    int n = a.length;
    int[] result = new int[n + 1];

    int[] left = prevSmaller(a);                  // position of the previous smaller, or -1
    int[] right = nextSmaller(a);                 // position of the next smaller, or n

    for (int i = 0; i < n; i++) {
      int len = right[i] - left[i] - 1;           // the longest window where a[i] is the minimum
      result[len] = Math.max(result[len], a[i]);  // RECORD a[i] as a candidate for that length
    }

    for (int i = n - 1; i >= 0; i--) {
      result[i] = Math.max(result[i], result[i + 1]); // FILL: a longer window's answer serves shorter ones
    }
    return result;
  }

  public static int[] prevSmaller(int[] a) {
    int n = a.length;
    int[] left = new int[n + 1];                  // slot n is never written
    Arrays.fill(left, -1);
    Stack<Integer> stack = new Stack<Integer>();  // positions, their values rising bottom to top
    for (int i = 0; i < n; i++) {
      while (!stack.empty() && a[stack.peek()] >= a[i]) {
        stack.pop();                              // DROP: not smaller than a[i], useless from now on
      }

      if (!stack.isEmpty()) {
        left[i] = stack.peek();                   // RECORD: the survivor on top is the previous smaller
      }
      stack.push(i);
    }
    return left;
  }

  private static int[] nextSmaller(int[] a) {
    int n = a.length;
    int[] right = new int[n + 1];                 // slot n is never written
    Arrays.fill(right, n);
    Stack<Integer> stack = new Stack<Integer>();  // positions, their values rising bottom to top
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.empty() && a[stack.peek()] >= a[i]) {
        stack.pop();                              // DROP: not smaller than a[i], useless from now on
      }

      if (!stack.empty()) {
        right[i] = stack.peek();                  // RECORD: the survivor on top is the next smaller
      }
      stack.push(i);
    }
    return right;
  }

  public static void main(String[] args) {
    int[] a = { 10, 20, 30, 50, 10, 70, 30 };
    System.out.println(Arrays.toString(maxOfMins(a))); // [70, 70, 30, 20, 10, 10, 10, 10]
  }
}
