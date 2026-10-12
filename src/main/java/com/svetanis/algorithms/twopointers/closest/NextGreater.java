package com.svetanis.algorithms.twopointers.closest;

import java.util.Arrays;
import java.util.Stack;

// Next greater element, with a stack of (value, position) entries
//
// Input: an array of integers.
// Return: for each position, the first number to its RIGHT that is strictly larger, or -1.
//
// The one idea: scan left to right. The stack holds the numbers still waiting for a larger
// number, and their values never rise from bottom to top. An arriving number answers every
// smaller one on top: it is the first larger number each of them has met. Each entry
// carries its position as well as its value, so the answer lands in the right slot.
//
// Siblings:
//   monotonicstack.NextGreater      -- the same scan with a stack of positions only; values
//                                     are read back through a[position]
//   twopointers.closest.PrevGreater -- the same scan run right to left: the nearest larger
//                                     number on the LEFT
//   twopointers.closest.NextSmaller -- the comparison flipped
//
// Time: O(n) -- every position is pushed once and popped at most once.
// Space: O(n) for the stack.

public final class NextGreater {

  public static int[] nextGreater(int[] a) {
    int n = a.length;
    int[] greater = new int[n];
    Arrays.fill(greater, -1);                     // stays -1 when nothing larger follows

    Stack<int[]> stack = new Stack<>();           // entries {value, position}
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && a[i] > stack.peek()[0]) {
        int[] top = stack.pop();                  // FOUND: a[i] is the first number to beat it
        greater[top[1]] = a[i];                   // RECORD in that number's own slot
      }
      stack.push(new int[] { a[i], i });          // a[i] waits for its own answer
    }
    return greater;
  }

  public static void main(String[] args) {
    int[] a1 = { 11, 13, 21, 3 };
    System.out.println(Arrays.toString(nextGreater(a1))); // [13, 21, -1, -1]

    int[] a2 = { 4, 5, 2, 25 };
    System.out.println(Arrays.toString(nextGreater(a2))); // [5, 25, 25, -1]

    int[] a3 = { 13, 7, 6, 12 };
    System.out.println(Arrays.toString(nextGreater(a3))); // [-1, 12, 12, -1]

    int[] a4 = { 4, 5, 2, 10 };
    System.out.println(Arrays.toString(nextGreater(a4))); // [5, 10, 10, -1]

    int[] a5 = { 3, 2, 1 };
    System.out.println(Arrays.toString(nextGreater(a5))); // [-1, -1, -1]
  }
}