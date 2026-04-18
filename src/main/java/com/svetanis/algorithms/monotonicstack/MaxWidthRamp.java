package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 962. Maximum Width Ramp

public final class MaxWidthRamp {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static int maxWidthRamp(int[] nums) {
    int n = nums.length;
    Deque<Integer> dq = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      if (dq.isEmpty() || nums[dq.peek()] > nums[i]) {
        dq.push(i);
      }
    }
    int max = 0;
    for (int i = n - 1; i >= 0; i--) {
      while (!dq.isEmpty() && nums[dq.peek()] <= nums[i]) {
        max = Math.max(max, i - dq.pop());
      }
      if (dq.isEmpty()) {
        break;
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
