package com.svetanis.algorithms.dp.common.scs;

import static com.svetanis.algorithms.dp.common.lcs.LongestCommonSubSeqLenBottomUp.lcs;

public final class ShortestCommonSuperSeqLenLCS {

  public static int scs(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    int len = lcs(s1, s2);
    return n + m - len;
  }

  public static void main(String[] args) {
    String s1 = "geek";
    String s2 = "eke";
    System.out.println(scs(s1, s2));

    String s3 = "AGGTAB";
    String s4 = "GXTXAYB";
    System.out.println(scs(s3, s4));
  }
}
