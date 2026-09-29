package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 1063. Number of Valid Subarrays
//
// Count the non-empty subarrays whose leftmost element is not larger than any other element
// in the subarray.
//
// A subarray starting at i stays valid until the first element to its right that is smaller
// than nums[i]. So each i contributes (that position - i) subarrays: a next-smaller search,
// scanned from the right and read at push time.

public final class NumberOfValidSubarrays1063 {
  // Time Complexity: O(n), every position is pushed once and popped at most once
  // Space Complexity: O(n) for the stack

  public static int validSubArrays(int[] nums) {
    int count = 0;
    int n = nums.length;
    Deque<Integer> stack = new ArrayDeque<>(); // positions, values strictly rising to the top
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
        stack.pop(); // not smaller than nums[i]: a subarray from i may include it
      }
      int firstSmaller = stack.isEmpty() ? n : stack.peek(); // n: valid to the end
      count += firstSmaller - i; // the subarrays i..i, i..i+1, ..., i..firstSmaller-1
      stack.push(i);
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 3, 1, 2, 4 };
    System.out.println(validSubArrays(a)); // 7
  }
}
