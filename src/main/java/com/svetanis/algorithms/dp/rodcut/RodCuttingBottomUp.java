package com.svetanis.algorithms.dp.rodcut;

import static java.lang.Math.max;

// Given a rod of length n inches and an array of prices 
// that contains prices of all pieces of size smaller than n. 
// Find the optimal way to cut rod into smaller rods 
// in order to maximize profit

// rod of len i has a value price[i - 1]
// partition the given rod of len n into two parts of len i and n - i
// recur for rod of len n - i but don't divide rod of len i any further

// R(n) = max {price[i - 1] + rodCut(n - i) } where 1 <= i <= n

public final class RodCuttingBottomUp {

  // Time Complexity: O(n^2)
  // Aux Space Complexity: O(n)

  public static int rodCut(int[] price, int n) {
    int max = 0;
    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        int cost = price[j - 1] + dp[i - j];
        max = max(max, cost);
      }
      dp[i] = max;
    }
    return dp[n];
  }

  public static void main(String[] args) {
    int[] price = { 1, 5, 8, 9, 10, 17, 17, 20 };
    System.out.println(rodCut(price, 4));
  }
}