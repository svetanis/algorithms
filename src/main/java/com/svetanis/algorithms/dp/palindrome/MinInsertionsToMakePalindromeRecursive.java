package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

public final class MinInsertionsToMakePalindromeRecursive {

  public static int palindrome(String str) {
    int n = str.length();
    return palindrome(str, 0, n - 1);
  }

  private static int palindrome(String str, int low, int high) {

    // base cases
    if (low > high) {
      return MAX_VALUE;
    }

    if (low == high) {
      return 0;
    }

    if (low == high - 1) {
      return (str.charAt(low) == str.charAt(high) ? 0 : 1);
    }

    // check if the first and last chars are same
    if (str.charAt(low) == str.charAt(high)) {
      return palindrome(str, low + 1, high - 1);
    } else {
      int left = palindrome(str, low, high - 1);
      int right = palindrome(str, low + 1, high);
      return 1 + min(left, right);
    }
  }

  public static void main(String[] args) {
    System.out.println(palindrome("geeks"));
  }
}