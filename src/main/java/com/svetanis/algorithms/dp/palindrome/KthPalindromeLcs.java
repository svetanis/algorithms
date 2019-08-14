package com.svetanis.algorithms.dp.palindrome;

import static com.svetanis.algorithms.dp.common.LongestCommonSubSeqLenDynamic.lcs;
import static org.apache.commons.lang3.StringUtils.reverse;

public final class KthPalindromeLcs {

  public static boolean isKthPalindrome(String str, int k) {
    // Time Complexity: O(n^2)
    // Space Complexity: (O(n^2)

    int n = str.length();
    String reversed = reverse(str);
    int lps = lcs(str, reversed);
    return n - lps <= k;
  }

  public static void main(String[] args) {
    int k = 2;
    String str = "abcdeca";
    System.out.println(isKthPalindrome(str, k));
  }
}