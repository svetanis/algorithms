package com.svetanis.algorithms.dp.palindrome;

import static com.svetanis.algorithms.dp.palindrome.LongestPalindromeSubSeqLenBottomUp.lps;

// Given a string, find the minimum number of characters 
// that we can remove to make it a palindrome.

public final class MinDeletionsToMakePalindrome {

  public static int minDeletions(String str) {
    int n = str.length();
    int len = lps(str);
    return n - len;
  }

  public static void main(String[] args) {
    System.out.println(minDeletions("abdbca"));
    System.out.println(minDeletions("cddpd"));
    System.out.println(minDeletions("pqr"));
  }
}