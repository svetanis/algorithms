package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.min;
import static org.apache.commons.lang3.StringUtils.reverse;

//Any string will be called K-palindromic if it can be transformed 
//into a palindrome by removing at most ‘K’ characters from it.

public final class KthPalindromeRecursive {

  public static boolean isKthPalindrome(String str, int k) {
    // Time Complexity: O(2^n)
    int n = str.length();
    String reversed = reverse(str);
    return isKthPalindrome(str, reversed, n, n) <= 2 * k;
  }

  private static int isKthPalindrome(String s1, String s2, int n, int m) {

    if (m == 0 || n == 0) {
      return n + m;
    }

    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      return isKthPalindrome(s1, s2, n - 1, m - 1);
    }

    int first = isKthPalindrome(s1, s2, n - 1, m);
    int second = isKthPalindrome(s1, s2, n, m - 1);
    return 1 + min(first, second);
  }

  public static void main(String[] args) {
    System.out.println(isKthPalindrome("acdcb", 2));
  }
}