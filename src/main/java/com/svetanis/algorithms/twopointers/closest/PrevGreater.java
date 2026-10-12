package com.svetanis.algorithms.twopointers.closest;

import java.util.Arrays;
import java.util.Stack;

// Previous greater element, with a stack of (value, position) entries
//
// Input: an array of integers.
// Return: for each position, the nearest number to its LEFT that is strictly larger, or -1.
//
// The one idea: NextGreater with the scan reversed. Walk right to left; the stack holds the
// numbers still waiting for a larger number on their left, and their values never rise from
// bottom to top. An arriving a[i] is that larger number for every smaller one on top, so
// each popped entry gets a[i] written into its own slot.
//
// Siblings:
//   monotonicstack.PrevGreater      -- this right-to-left walk with a stack of positions, and
//                                     a left-to-right walk that reads a[i]'s answer off the top
//   twopointers.closest.NextGreater -- the same scan left to right
//   twopointers.closest.PrevSmaller -- the comparison flipped
//
// Time: O(n) -- every position is pushed once and popped at most once.
// Space: O(n) for the stack.

public final class PrevGreater {

  public static int[] prevGreater(int[] a) {
    int n = a.length;
    int[] greater = new int[n];
    Arrays.fill(greater, -1);                     // stays -1 when nothing larger precedes

    Stack<int[]> stack = new Stack<>();           // entries {value, position}
    for (int i = n - 1; i >= 0; i--) {            // START at the right end
      while (!stack.isEmpty() && a[i] > stack.peek()[0]) {
        int[] top = stack.pop();                  // FOUND: a[i] is the nearest larger on its left
        greater[top[1]] = a[i];                   // RECORD in that number's own slot
      }
      stack.push(new int[] { a[i], i });          // a[i] waits for its own answer
    }
    return greater;
  }

  public static void main(String[] args) {
    int[] a1 = { 10, 4, 2, 20, 40, 12, 30 };
    System.out.println(Arrays.toString(prevGreater(a1))); // [-1, 10, 4, -1, -1, 40, 40]

    int[] a2 = { 10, 20, 30, 40 };
    System.out.println(Arrays.toString(prevGreater(a2))); // [-1, -1, -1, -1]

    int[] a3 = { 40, 30, 20, 10 };
    System.out.println(Arrays.toString(prevGreater(a3))); // [-1, 40, 30, 20]
  }
}
