package com.svetanis.algorithms.slidingwindow.fixed;

import static java.lang.Math.max;

// Maximum sum and product of k consecutive digits -- no LeetCode number; source unknown
//
// Given a number in the form of a string of decimal digits and a window size k,
// 1 <= k <= str.length(), find the max sum and the max product of k consecutive digits.
// Returns both; they can come from different windows.
//
// A fixed window moves one digit at a time: the digit entering is added to the sum and the
// digit leaving is subtracted. The product is edited the same way -- divide the leaving digit
// out, multiply the entering digit in -- except that a 0 cannot be divided out. So the
// product holds only the window's non-zero digits, a second counter holds how many zeros are
// in the window, and while any zero is inside, the window's product is 0. Nine nines is the
// largest product that fits in an int, so k <= 9 never wraps.
//
// Time: O(n) -- each digit enters the window once and leaves once
// Space: O(n) -- the digits as ints; the window itself is a few counters
//
// Sibling: MaxSumAndProductSubArraySizeK is the same method in a second file.

public final class MaxSumAndProduct {

  public record SumProduct(int maxSum, int maxProduct) {}

  public static SumProduct maxSumProd(String str, int k) {
    int n = str.length();
    int sum = 0;
    int prod = 1; // the product of the non-zero digits in the window
    int zeros = 0; // how many zeros are in the window
    int[] digits = new int[n];
    for (int i = 0; i < n; i++) {
      digits[i] = str.charAt(i) - '0';
    }

    for (int i = 0; i < k; i++) { // BUILD: the first window, digits[0..k-1]
      sum += digits[i];
      if (digits[i] == 0) {
        zeros++;
      } else {
        prod *= digits[i];
      }
    }

    int maxSum = sum;
    int maxProd = zeros > 0 ? 0 : prod;
    for (int i = k; i < n; i++) {
      sum += digits[i] - digits[i - k]; // IN digits[i], OUT digits[i - k]
      if (digits[i - k] == 0) { // OUT first, so the product never holds k + 1 digits
        zeros--;
      } else {
        prod /= digits[i - k];
      }
      if (digits[i] == 0) { // IN
        zeros++;
      } else {
        prod *= digits[i];
      }
      maxSum = max(maxSum, sum); // RECORD
      maxProd = max(maxProd, zeros > 0 ? 0 : prod); // RECORD: one zero makes the product 0
    }
    return new SumProduct(maxSum, maxProd);
  }

  public static void main(String[] args) {

    String s1 = "3675356291";
    System.out.println(maxSumProd(s1, 5));

    String s2 = "2709360626";
    System.out.println(maxSumProd(s2, 5));

  }
}
