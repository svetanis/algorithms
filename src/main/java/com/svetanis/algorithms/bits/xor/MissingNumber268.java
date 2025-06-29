package com.svetanis.algorithms.bits.xor;

// 268. Missing Number

// given an array containing n
// distinct numbers in the range [0, n]
// return the only number in the range
// that is missing from the array

public final class MissingNumber268 {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int single(int[] a) {
    int n = a.length;
    int xor = n;
    // xor all numbers from 1 to n
    for (int i = 0; i < n; i++) {
      xor = xor ^ i;
    }
    // xor all numbers in a[]
    for (int i = 0; i < n; i++) {
      xor = xor ^ a[i];
    }
    // return missing number
    return xor;
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
