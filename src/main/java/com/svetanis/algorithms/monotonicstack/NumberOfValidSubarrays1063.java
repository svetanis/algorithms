package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 1063. Number of Valid Subarrays
//
// Count the non-empty subarrays whose leftmost element is not larger than any other element
// in the subarray.
//
// A subarray starting at i stays valid until the first element to its right that is smaller
// than nums[i]. So each i contributes (that position - i) subarrays -- a next-smaller search.
// An equal value does not stop a start: the first element only has to be not larger.
//
// Two methods, the same counts found from opposite ends:
//   validSubArrays scans from the right and reads each start's first smaller at push time.
//     It pops values that are larger OR EQUAL (>=), since an equal value is no stopper,
//     and an empty stack means the subarrays run to the end, n.
//   validSubarraysLeftToRight scans from the left: each start waits on the stack until a strictly
//     smaller value arrives (>), and is counted when it is popped. The starts never popped
//     have nothing smaller to their right and are counted at the end with stopper n.
//
// The count is an int: the largest answer, all values equal, is n(n+1)/2, about 1.25 * 10^9
// at the problem's n <= 5 * 10^4. A larger n needs a long.

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

  // Time Complexity: O(n), every position is pushed once and popped once
  // Space Complexity: O(n) for the stack

  public static int validSubarraysLeftToRight(int[] nums) {
    int count = 0, n = nums.length;
    Deque<Integer> dq = new ArrayDeque<>(); // WAITING starts (indexes), values never falling to the top
    for (int i = 0; i < n; i++) { // WALK: nums[i] arrives
      while (!dq.isEmpty() && nums[dq.peek()] > nums[i]) { // STOP: strictly smaller; an equal value waits on
        count += i - dq.pop(); // COUNT: stopper i - start, the subarrays start..i-1
      }
      dq.push(i); // WAIT: i is a start too
    }
    while (!dq.isEmpty()) {
      count += n - dq.pop(); // END: never stopped -- the stopper is one past the end, n
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 3, 1, 2, 4 };
    System.out.println(validSubArrays(a)); // 7
    System.out.println(validSubarraysLeftToRight(a)); // 7

    int[] b = { 1, 4, 2, 5, 3 };
    System.out.println(validSubArrays(b)); // 11
    System.out.println(validSubarraysLeftToRight(b)); // 11

    int[] c = { 2, 2, 2 };
    System.out.println(validSubArrays(c)); // 6
    System.out.println(validSubarraysLeftToRight(c)); // 6
  }
}
