package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

// Given a string, find the length of its Longest Palindromic Substring (LPS). 
// In a palindromic string, elements read the same backward and forward.

public final class LongestPalindromeSubStrLenRecursive {

  public static int lps(String str) {
    int n = str.length();
    return lps(str, 0, n - 1);
  }

  private static int lps(String str, int low, int high) {
    // Time Complexity: O(3^n)

    if (low > high) {
      return 0;
    }

    // Base case 1:
    // if there is only 1 char
    if (low == high) {
      return 1;
    }

    // Base case 2:
    // if there are only 2 chars
    // and both are same
    if (str.charAt(low) == str.charAt(high) && low + 1 == high) {
      return 2;
    }

    // if the first and last chars match
    if (str.charAt(low) == str.charAt(high)) {
      int len = high - low - 1;
      if (len == lps(str, low + 1, high - 1)) {
        return len + 2;
      }
    }

    // if first and last
    // chars don't match
    int left = lps(str, low + 1, high);
    int right = lps(str, low, high - 1);
    return max(left, right);
  }

  public static void main(String[] args) {
    System.out.println(lps("abdbca")); // 3
    System.out.println(lps("cddpd"));  // 3
    System.out.println(lps("pqr"));    // 1
  }
}