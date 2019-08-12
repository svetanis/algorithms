package com.svetanis.algorithms.dp.palindrome;

import static com.svetanis.algorithms.dp.common.LongestCommonSubSeqLenDynamic.lcs;
import static org.apache.commons.lang3.StringUtils.reverse;

public final class LongestPalindromeSubSeqLenLcs {

  public static int lps(String str) {
    // Time Complexity: O(n^2)
    // Space Complexity: (O(n^2)

    String reversed = reverse(str);
    return lcs(str, reversed);
  }

  public static void main(String[] args) {
    String str = "GEEKS FOR GEEKS";
    System.out.println(lps(str));
  }
}