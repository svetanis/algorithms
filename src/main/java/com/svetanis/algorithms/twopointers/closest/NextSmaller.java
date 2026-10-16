package com.svetanis.algorithms.twopointers.closest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Next smaller element, with a stack of (value, position) entries
//
// Input: an array of integers.
// Return: for each position, the first number to its RIGHT that is strictly smaller, or -1.
//
// The one idea: NextGreater with the comparison flipped. Scan left to right; the stack holds
// the numbers still waiting for a smaller number, and their values never fall from bottom to
// top. An arriving number answers every larger one on top. Each entry carries its position
// as well as its value, so the answer lands in the right slot.
//
// Siblings:
//   monotonicstack.NextSmaller      -- the same scan with a stack of positions only
//   twopointers.closest.PrevSmaller -- the same scan run right to left: the nearest smaller
//                                     number on the LEFT
//   twopointers.closest.NextGreater -- the comparison flipped
//
// Time: O(n) -- every position is pushed once and popped at most once.
// Space: O(n) for the stack.

public final class NextSmaller {

  public static int[] nextSmaller(int[] a) {
    int n = a.length;
    int[] smaller = new int[n];
    Arrays.fill(smaller, -1);                     // DEFAULT: stays -1 when nothing smaller follows

    Deque<int[]> stack = new ArrayDeque<>();      // WAITING: entries {value, position}
    for (int i = 0; i < n; i++) {                 // WALK: a[i] arrives
      while (!stack.isEmpty() && a[i] < stack.peek()[0]) {  // STOP: a[i] is strictly smaller than the top
        int[] top = stack.pop();                  // FOUND: a[i] is the first number below it
        smaller[top[1]] = a[i];                   // RECORD in that number's own slot
      }
      stack.push(new int[] { a[i], i });          // WAIT: a[i] waits for its own answer
    }
    return smaller;                               // RETURN: -1 where nothing smaller follows
  }

  public static void main(String[] args) {
    int[] a1 = { 5, 1, 3, 4, 6, 2 };
    System.out.println(Arrays.toString(nextSmaller(a1))); // [1, -1, 2, 2, 2, -1]

    int[] a2 = { 1, 3, 3, 2, 5 };
    System.out.println(Arrays.toString(nextSmaller(a2))); // [-1, 2, 2, -1, -1]

    int[] a3 = { 4, 2, 1, 5, 3 };
    System.out.println(Arrays.toString(nextSmaller(a3))); // [2, 1, -1, 3, -1]

    int[] a4 = { 1, 2, 3, 4, 5 };
    System.out.println(Arrays.toString(nextSmaller(a4))); // [-1, -1, -1, -1, -1]

    int[] a5 = { 5, 4, 3, 2, 1 };
    System.out.println(Arrays.toString(nextSmaller(a5))); // [4, 3, 2, 1, -1]

    int[] a6 = { 1, 3, 5, 4, 2 };
    System.out.println(Arrays.toString(nextSmaller(a6))); // [-1, 2, 4, 2, -1]

    int[] a7 = { 6, 4, 2 };
    System.out.println(Arrays.toString(nextSmaller(a7))); // [4, 2, -1]
  }
}
