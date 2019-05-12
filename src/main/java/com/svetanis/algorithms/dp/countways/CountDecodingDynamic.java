package com.svetanis.algorithms.dp.countways;

public final class CountDecodingDynamic {

  public static int decoding(String str) {

    int n = str.length();

    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    return dp[n];
  }

  public static void main(String[] args) {
    String str = "1234";
    // count = 3: ABCD, LCD, AWD
    System.out.println(decoding(str));
  }
}
