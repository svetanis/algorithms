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
//
// Input: an array of integers nums. Returns: the largest j - i over pairs i < j with
// nums[i] <= nums[j], or 0 when no such pair exists.

public final class MaxWidthRamp {
  // Time Complexity: O(n), two walks, and every start is pushed once and popped at most once
  // Space Complexity: O(n) for the stack

  public static int maxWidthRamp(int[] nums) {
    int n = nums.length;
    Deque<Integer> starts = new ArrayDeque<>(); // STARTS: positions, values strictly falling to the top
    for (int i = 0; i < n; i++) { // WALK: first pass, left to right
      if (starts.isEmpty() || nums[starts.peek()] > nums[i]) { // LOW: nums[i] is below every start so far
        starts.push(i); // PUSH: a new smallest value so far
      }
    }
    int max = 0; // BEST: no ramp yet
    for (int j = n - 1; j >= 0; j--) { // WALK: second pass, j from the right end
      while (!starts.isEmpty() && nums[starts.peek()] <= nums[j]) { // REACH: nums[j] is at least the top start's value
        max = Math.max(max, j - starts.pop()); // RECORD: the widest ramp this start can have
      }
      if (starts.isEmpty()) { // DONE: no start left to reach
        break; // END: every start has its widest ramp
      }
    }
    return max; // RETURN: the widest ramp
  }

  public static void main(String[] args) {
    int[] a1 = { 6, 0, 8, 2, 1, 5 };
    System.out.println(maxWidthRamp(a1)); // 4
    int[] a2 = { 9, 8, 1, 0, 1, 9, 4, 0, 4, 1 };
    System.out.println(maxWidthRamp(a2)); // 7
  }
}
