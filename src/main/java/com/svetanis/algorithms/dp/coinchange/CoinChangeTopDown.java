package com.svetanis.algorithms.dp.coinchange;

// Given a value N, if we want to make change for N cents, 
// and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins,
// how many ways can we make the change? The order of coins doesn’t matter.

// n - size of array of coins a
// v - coin value
// returns the count of ways we can sum
// a[0 ... n-1] coins to get sum v

// f(a) : min number of coins needed to make 
//        the amount of a using denominations
//        d0, ..., dk-1

// f(a) = 1 + min(f(a - d0), f(a - d1), ..., f(a - dk))

public final class CoinChangeTopDown {

  public static int count(int[] a, int v) {
    int n = a.length;
    Integer[][] dp = new Integer[a.length + 1][v + 1];
    return count(a, n, v, dp);
  }

  private static int count(int[] a, int n, int v, Integer[][] dp) {
    // base case
    if (v == 0) {
      return 1;
    }
    // if V < 0 then no solution exists
    if (v < 0) {
      return 0;
    }
    // if there are no coins and V > 0,
    // then no solution exists
    if (n <= 0 && v >= 1) {
      return 0;
    }
    
    if(dp[n][v] != null) {
      return dp[n][v];
    }

    // return the sum of solutions
    // 1. include a[n - 1]: count(a[], n, v - a[n-1])
    int incl = count(a, n, v - a[n - 1], dp);
    // 2. excluding a[n - 1]: count(a[], n - 1, v)
    int excl = count(a, n - 1, v, dp);
    dp[n][v] = incl + excl;
    return dp[n][v];
  }

  public static void main(String[] args) {
    int[] coins = { 1, 2, 3 };
    System.out.println(count(coins, 4));
  }
}
