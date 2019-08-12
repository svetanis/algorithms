package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.max;

public final class LongestPalindromeSubStrExpandAroundCenter {

  public static String lps(String str) {
    // Time Complexity: O(n^2)
    // Auxiliary Space: O(1)

    int n = str.length();
    int start = 0;
    int end = 0;
    // one by one consider every char
    // as center point of even and
    // odd length palindromes
    for (int i = 0; i < n; ++i) {
      int odd = expand(str, i, i);
      int even = expand(str, i, i + 1);
      int len = max(odd, even);
      if (len > end - start) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }
    return str.substring(start, end + 1);
  }

  private static int expand(String str, int left, int right) {
    int n = str.length();
    int low = left;
    int high = right;
    while (low >= 0 && high < n && str.charAt(low) == str.charAt(high)) {
      low--;
      high++;
    }
    return high - low - 1;
  }

  public static void main(String[] args) {
    String str = "forgeeksskeegfor";
    System.out.println(lps(str));
  }
}