package com.svetanis.algorithms.twopointers.closest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Previous smaller element, with a stack of (value, position) entries
//
// Input: an array of integers.
// Return: for each position, the nearest number to its LEFT that is strictly smaller, or -1.
//
// The one idea: NextSmaller with the scan reversed. Walk right to left; the stack holds the
// numbers still waiting for a smaller number on their left, and their values never fall from
// bottom to top. An arriving a[i] is that smaller number for every larger one on top, so
// each popped entry gets a[i] written into its own slot.
//
// Siblings:
//   monotonicstack.PrevSmaller      -- this right-to-left walk with a stack of positions, and
//                                     a left-to-right walk that reads a[i]'s answer off the top
//   twopointers.closest.NextSmaller -- the same scan left to right
//   twopointers.closest.PrevGreater -- the comparison flipped
//
// Time: O(n) -- every position is pushed once and popped at most once.
// Space: O(n) for the stack.

public final class PrevSmaller {

  public static int[] prevSmaller(int[] a) {
    int n = a.length;
    int[] smaller = new int[n];
    Arrays.fill(smaller, -1);                     // DEFAULT: stays -1 when nothing smaller precedes

    Deque<int[]> stack = new ArrayDeque<>();      // WAITING: entries {value, position}
    for (int i = n - 1; i >= 0; i--) {            // START at the right end
      while (!stack.isEmpty() && a[i] < stack.peek()[0]) {  // STOP: a[i] is strictly smaller than the top
        int[] top = stack.pop();                  // FOUND: a[i] is the nearest smaller on its left
        smaller[top[1]] = a[i];                   // RECORD in that number's own slot
      }
      stack.push(new int[] { a[i], i });          // WAIT: a[i] waits for its own answer
    }
    return smaller;                               // RETURN: -1 where nothing smaller precedes
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 6, 4, 10, 2, 5 };
    System.out.println(Arrays.toString(prevSmaller(a1))); // [-1, 1, 1, 4, 1, 2]

    int[] a2 = { 1, 3, 0, 2, 5 };
    System.out.println(Arrays.toString(prevSmaller(a2))); // [-1, 1, -1, 0, 2]
  }
}
