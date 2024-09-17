package com.svetanis.algorithms.dp.wordbreak;

import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class WordBreakCountDynamicBottomUpIK {

  public static int wbcBackward(String str, List<String> dict) {
    
    int n = str.length();
    Set<String> set = new HashSet<>();
    set.addAll(dict);
    int[] dp = new int[n + 1];
    dp[n] = 1;
    for (int i = n - 1; i >= 0; i--) {
      dp[i] = 0;
      for (int j = i + 1; j <= n; j++) {
        if (set.contains(str.substring(i, j))) {
          dp[i] += dp[j];
          dp[i] = dp[i] % 1000000007;
        }
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
