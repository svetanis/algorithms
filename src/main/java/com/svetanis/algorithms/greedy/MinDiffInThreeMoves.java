package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
//
// "Change an element to any value" is really "delete it": a changed element can
// always be parked on top of one that survives, where it affects neither the max
// nor the min. So the question becomes -- delete 3 elements, minimize the spread
// of what's left.
//
// The greedy claim: those 3 deletions only ever pay off at the ENDS of the
// sorted array. Deleting an interior element leaves both the max and the min
// where they were, so it buys nothing. What is NOT decided in advance is how the
// 3 split between the two ends, and that is the whole problem: there are exactly
// 4 splits (3+0, 2+1, 1+2, 0+3), so try all four and keep the best.

public final class MinDiffInThreeMoves {
  // Time Complexity: O(n log n) -- the sort; the scan after it is O(1)
  // Space Complexity: O(1) -- sorts in place, which does mutate the caller's array

  public static int minDiff(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    if (n <= 4) {
      // 3 moves on 4 or fewer elements can flatten them all to one value
      return 0;
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < 4; i++) {
      // drop i from the front and the remaining 3 - i from the back, so the
      // survivors are the window nums[i .. n - 4 + i]
      // i = 0 removes the 3 largest, i = 3 removes the 3 smallest
      min = Math.min(min, nums[n - 4 + i] - nums[i]);
    }
    return min;
  }

  public static void main(String[] args) {
    int[] p1 = { 5, 3, 2, 4 };
    System.out.println(minDiff(p1)); // 0
    int[] p2 = { 1, 5, 0, 10, 14 };
    System.out.println(minDiff(p2)); // 1
    int[] p3 = { 3, 100, 20 };
    System.out.println(minDiff(p3)); // 0
  }
}
