package com.svetanis.algorithms.slidingwindow.array;

// Subarray with a given sum, non-negative values -- no LeetCode number; source unknown
//
// Given an unsorted array of positive integers, find a contiguous subarray which adds to
// a given number. Returns its first and last index, or (-1, -1) when none does. Zeros are
// fine; a negative value breaks the window. Assumes the array is not empty: an empty one
// throws ArrayIndexOutOfBoundsException.
//
// With no negative values, adding an element never lowers the window's sum and dropping
// one never raises it. So after each element enters, drop from the left while the sum is
// above target; if what is left equals target, that is the answer. The window always
// keeps at least one element (start < i - 1). The loop runs to i == n so that the window
// ending at the last element is checked too; at i the window is a[start..i - 1].
//
// Time: O(n) -- i and start only move forward
// Space: O(1) -- a running sum and two indices
//
// Sibling: GivenSumSubArrayHashMap answers the same question with a map of prefix sums,
// which also handles negative values.

public class GivenSumSubArraySlidingWindow {

  // left and right are the subarray's first and last index; (-1, -1) when none exists
  public record Pair(int left, int right) {
    @Override
    public String toString() {
      return "Pair{left=%d, right=%d}".formatted(left, right);
    }
  }

  public static Pair sum(int[] a, int target) {
    int n = a.length;
    int start = 0;
    int sum = a[0];

    for (int i = 1; i <= n; ++i) {
      while (sum > target && start < i - 1) { // SHRINK while the sum is above target
        sum = sum - a[start++];
      }
      if (sum == target) { // RETURN: a[start..i - 1] adds to target
        return new Pair(start, i - 1);
      }
      if (i < n) {
        sum += a[i]; // ADD
      }
    }
    return new Pair(-1, -1); // RETURN: no subarray adds to target
  }

  public static void main(String[] args) {
    int[] a = { 15, 2, 4, 8, 9, 5, 10, 23 };
    System.out.println(sum(a, 23));
    
    int[] a1 = { 1, 4, 20, 3, 10, 5 };
    System.out.println(sum(a1, 33));
    
    int[] a3 = { 1, 4, 0, 0, 3, 10, 5 };
    System.out.println(sum(a3, 7));
    
    int[] a4 = { 1, 4 };
    System.out.println(sum(a4, 0));
  }
}