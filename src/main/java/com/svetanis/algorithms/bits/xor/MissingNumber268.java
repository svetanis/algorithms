package com.svetanis.algorithms.bits.xor;

// 268. Missing Number
//
// Input: an int array of n distinct numbers, each from 0 to n. Exactly one number of 0 .. n is not
// in it. Returns that number. On LeetCode: 1 <= n <= 10^4, 0 <= nums[i] <= n, all distinct.
//
// The one idea: ^ of a number with itself is 0, ^ with 0 leaves a number as it is, and the order of
// the ^s does not matter. So ^ together every number from 0 to n and every number of the array:
// each present number appears twice and cancels, and the missing one is all that is left. The index
// loop covers 0 .. n - 1, so xor starts at n.
//
// Time: O(n) -- two passes.
// Space: O(1) -- one int.
//
// Siblings: MissingNumber is the CSES version, with the numbers 1 .. n.

public final class MissingNumber268 {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int single(int[] a) {
    int n = a.length;
    int xor = n; // START: n itself, which the loop below does not reach
    // xor all numbers from 0 to n
    for (int i = 0; i < n; i++) { // ALL: 0 .. n - 1
      xor = xor ^ i;
    }
    // xor all numbers in a[]
    for (int i = 0; i < n; i++) { // PRESENT: each cancels its copy
      xor = xor ^ a[i];
    }
    // every present number met its copy; the missing one is left
    return xor; // RETURN
  }


  public static void main(String[] args) {
    int[] a1 = { 1, 5, 2, 6, 4, 0 };
    System.out.println(single(a1)); // 3

    int[] a2 = { 3, 0, 1 };
    System.out.println(single(a2)); // 2

    int[] a3 = { 0, 1 };
    System.out.println(single(a3)); // 2

    int[] a4 = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
    System.out.println(single(a4)); // 8
  }
}
