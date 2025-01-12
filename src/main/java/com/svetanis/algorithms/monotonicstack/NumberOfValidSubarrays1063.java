package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 1063. Number of Valid Subarrays

public final class NumberOfValidSubarrays1063 {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static int validSubArrays(int[] a) {
    int count = 0;
    int n = a.length;
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && a[stack.peek()] >= a[i]) {
        stack.pop();
      }
      int last = stack.isEmpty() ? n : stack.peek();
      count += last - i;
      stack.push(i);
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 3, 1, 2, 4 };
    System.out.println(validSubArrays(a)); // 9
  }
}