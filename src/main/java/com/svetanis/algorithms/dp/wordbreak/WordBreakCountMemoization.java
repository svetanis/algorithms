package com.svetanis.algorithms.dp.wordbreak;

import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static java.util.Arrays.fill;

import java.util.List;

public final class WordBreakCountMemoization {

  public static int wbc(String str, List<String> dict) {
    int[] dp = new int[str.length() + 1];
    fill(dp, -1);
    return wbc(str, dict, dp, 0);
  }

  private static int wbc(String str, List<String> dict, int[] dp, int index) {
    int n = str.length();
    if (index == n) {
      return 1;
    }
    if (dp[index] != -1) {
      return dp[index];
    }
    int count = 0;
    for (int i = index + 1; i <= n; i++) {
      if (dict.contains(str.substring(index, i))) {
        count += wbc(str, dict, dp, i);
      }
    }
    dp[index] = count;
    return dp[index];
  }

  public static void main(String[] args) {
    System.out.println(wbc("iamsuperlady", dictionary()));
  }
}
