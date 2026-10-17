package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 611. Valid Triangle Number
//
// Input: an array of non-negative integers.
// Return: how many triples of positions can be the three side lengths of a triangle,
// that is, every two of the sides add up to MORE than the third.
//
// The one idea: sort, then FIX the largest side a[i]. With sides x <= y <= a[i], two of
// the three tests hold for free and only x + y > a[i] is left. Converge on a[0..i-1]:
// when a[left] + a[right] > a[i], every left' from left to right - 1 also works with
// right, because a[left'] >= a[left] -- count all right - left of them in one step and
// drop right. Otherwise a[left] is too short even with the longest partner: drop left.
// No + 1: these are pairs of two different positions, and right cannot pair with itself.
//
// Sibling: todo.twopointers.CountTrianglesToDo -- the same loop, with the count reduced
//   modulo 10^9 + 7 at the end.
//
// Time: O(n^2) -- the sort is O(n log n), then n fixed sides with an O(n) converging pass each.
// Space: O(1) besides the sort, which works in place.

public final class ValidTriangleNumber611 {

  public static int vtn(int[] a) {
    Arrays.sort(a);                               // SORT: so the largest side is known
    int count = 0;
    for (int i = a.length - 1; i >= 2; i--) {     // FIX the LARGEST side, walking down
      int left = 0;                               // START: the two ENDS of a[0..i-1]
      int right = i - 1;
      while (left < right) {                      // STOP: a pair needs two positions
        int sum = a[left] + a[right];             // COMPARE with the largest side
        if (sum > a[i]) {
          count += right - left;                  // COUNT: every left' in [left, right-1] also works
          right--;                                // DROP RIGHT: all its pairs are counted
        } else {
          left++;                                 // DROP LEFT: too short even with the longest partner
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 2, 2, 3, 4 };
    System.out.println(vtn(a)); // 3

    int[] a1 = { 4, 2, 3, 4 };
    System.out.println(vtn(a1)); // 4
  }
}