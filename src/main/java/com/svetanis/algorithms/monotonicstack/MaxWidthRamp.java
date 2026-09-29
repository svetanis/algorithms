package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 962. Maximum Width Ramp
//
// A ramp is a pair i < j with nums[i] <= nums[j]; its width is j - i. Return the largest
// width, or 0 if there is no ramp.
//
// Only a value smaller than everything before it can start the widest ramp, so the first pass
// stacks those starts, strictly falling. The second pass walks j from the right end: every
// start that nums[j] reaches is popped, since no later j, being further left, could give it
// a wider ramp.

public final class MaxWidthRamp {
  // Time Complexity: O(n)
  // Space Complexity: O(n) for the stack

  public static int maxWidthRamp(int[] nums) {
    int n = nums.length;
    Deque<Integer> starts = new ArrayDeque<>(); // positions, values strictly falling to the top
    for (int i = 0; i < n; i++) {
      if (starts.isEmpty() || nums[starts.peek()] > nums[i]) {
        starts.push(i); // a new smallest value so far
      }
    }
    int max = 0;
    for (int j = n - 1; j >= 0; j--) {
      while (!starts.isEmpty() && nums[starts.peek()] <= nums[j]) {
        max = Math.max(max, j - starts.pop()); // the widest ramp this start can have
      }
      if (starts.isEmpty()) {
        break; // every start has its widest ramp
      }
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a1 = { 6, 0, 8, 2, 1, 5 };
    System.out.println(maxWidthRamp(a1)); // 4
    int[] a2 = { 9, 8, 1, 0, 1, 9, 4, 0, 4, 1 };
    System.out.println(maxWidthRamp(a2)); // 7
  }
}
