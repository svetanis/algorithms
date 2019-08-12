package com.svetanis.algorithms.dp.palindrome;

import static com.svetanis.algorithms.dp.common.LongestCommonSubSeq.lcs;
import static org.apache.commons.lang3.StringUtils.reverse;

public final class LongestPalindromeSubSeqLcs {

  public static String lps(String str) {
    // Time Complexity: O(n^2)
    // Space Complexity: (O(n^2)

    String reversed = reverse(str);
    return lcs(str, reversed);
  }

  public static void main(String[] args) {
    String str = "ABBDCACB";
    System.out.println(lps(str));
  }
}