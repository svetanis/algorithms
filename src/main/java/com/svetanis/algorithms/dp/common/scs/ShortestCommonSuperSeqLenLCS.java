package com.svetanis.algorithms.dp.common.scs;

import static com.svetanis.algorithms.dp.common.lcs.LongestCommonSubSeqLenBottomUp.lcs;

// 1092. Shortest Common Supersequence

public final class ShortestCommonSuperSeqLenLCS {

  public static int scs(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    int len = lcs(s1, s2);
    return n + m - len;
  }

  public static void main(String[] args) {
		System.out.println(scs("geek", "eke"));
		System.out.println(scs("AGGTAB", "GXTXAYB"));
		System.out.println(scs("abac", "cab")); // 5
		System.out.println(scs("aaaaaaaa", "aaaaaaaa")); // 8
  }
}
