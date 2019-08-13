package com.svetanis.algorithms.dp.palindrome;

import static com.svetanis.algorithms.dp.common.LongestCommonSubSeqLenDynamic.lcs;
import static org.apache.commons.lang3.StringUtils.reverse;

public final class MinInsertionsToMakePalindromeLcs {

  public static int palindrome(String str) {
    // Time Complexity: O(n^2)
    // Space Complexity: O(n^2)

    int n = str.length();
    String reversed = reverse(str);

    // the output is length of string
    // minus length of lcs of
    // str and its reverse
    int len = lcs(str, reversed);
    return (n - len);
  }

  public static void main(String[] args) {
    System.out.println(palindrome("geeks"));
  }
}