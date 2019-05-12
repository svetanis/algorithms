package com.svetanis.algorithms.dp.wordbreak;

import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;

import java.util.List;

public final class WordBreakCountDynamicBottomUp {

  public static int wbcBackward(String str, List<String> dict) {
    int n = str.length();
    int[] dp = new int[n + 1];
    dp[n] = 1;
    for (int i = n - 1; i >= 0; i--) {
      int count = 0;
      for (int j = i; j <= n; j++) {
        if (dict.contains(str.substring(i, j))) {
          count += dp[j];
        }
        dp[i] = count;
      }
    }
    return dp[0];
  }

  public static int wbcForward(String str, List<String> dict) {
    int n = str.length();
    int[] dp = new int[n + 1];
    dp[0] = 1;
    for (int i = 1; i <= n; i++) {
      int count = 0;
      for (int j = 0; j < i; j++) {
        if (dict.contains(str.substring(j, i))) {
          count += dp[j];
        }
        dp[i] = count;
      }
    }
    return dp[n];
  }

  public static void main(String[] args) {
    List<String> dict = dictionary();
    System.out.println(wbcBackward("iamsuperlady", dict));
    System.out.println(wbcForward("iamsuperlady", dict));
  }
}
