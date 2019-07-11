package com.svetanis.algorithms.dp.rodcut;

import static java.lang.Math.max;

//Given a rod of length n inches and an array of prices 
//that contains prices of all pieces of size smaller than n. 
//Determine the maximum value obtainable by cutting up the rod and selling the pieces. 

//Pieces can have the same length: i - length, p[i] - price

//R(n) = max(0 < i <= n) {p[i] + R(n - i) }

public final class RodCuttingDynamic {

  // returns the best obtainable price
  // for a rod of length n and
  public static int cutRod(int[] price, int n) {
    int max = 0;
    int[] dp = new int[n + 1];
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j < i; ++j) {
        max = max(max, price[j] + dp[i - j - 1]);
      }
      dp[i] = max;
    }
    return dp[n];
  }

  public static void main(String[] args) {
    int[] price = { 1, 5, 8, 9, 10, 17, 17, 20 };
    System.out.println(cutRod(price, price.length));
  }
}