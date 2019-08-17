package com.svetanis.algorithms.dp.common.lcs;

import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.algorithms.dp.common.lcs.LongestCommonSubSeq.length;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.String.valueOf;

import java.util.Set;

import com.google.common.collect.ImmutableList;

public final class LongestCommonSubSeqs {

  private static int[][] DP;

  public static ImmutableList<String> lcs(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    DP = length(s1, s2);
    return newList(lcs(s1, s2, n, m));
  }

  private static Set<String> lcs(String s1, String s2, int n, int m) {
    Set<String> set = newHashSet();

    if (n == 0 || m == 0) {
      set.add("");
      return set;
    }

    if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
      Set<String> current = lcs(s1, s2, n - 1, m - 1);
      for (String s : current) {
        set.add(s + valueOf(s1.charAt(n - 1)));
      }
    } else {

      // construct from top side of matrix
      if (DP[n - 1][m] >= DP[n][m - 1]) {
        set = lcs(s1, s2, n - 1, m);
      }

      // construct from left side of matrix
      if (DP[n][m - 1] >= DP[n - 1][m]) {
        Set<String> current = lcs(s1, s2, n, m - 1);
        set.addAll(current);
      }
    }
    return set;
  }

  public static void main(String[] args) {
    String s1 = "AGTGATG";
    String s2 = "GTTAG";
    System.out.println(lcs(s1, s2));
  }
}
