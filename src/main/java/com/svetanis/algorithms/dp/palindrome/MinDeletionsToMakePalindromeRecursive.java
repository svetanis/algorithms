package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.min;

// Given a string, find the minimum number of characters 
// that we can remove to make it a palindrome.

public final class MinDeletionsToMakePalindromeRecursive {

  public static int minDeletions(String str) {
    int n = str.length();
    return minDeletions(str, 0, n - 1);
  }

  private static int minDeletions(String str, int start, int end) {
    if (start >= end) {
      return 0;
    }

    if (str.charAt(start) == str.charAt(end)) {
      return minDeletions(str, start + 1, end - 1);
    }
    int left = minDeletions(str, start + 1, end);
    int right = minDeletions(str, start, end - 1);
    return 1 + min(left, right);
  }

  public static void main(String[] args) {
    System.out.println(minDeletions("abdbca"));
    System.out.println(minDeletions("cddpd"));
    System.out.println(minDeletions("pqr"));
  }
}