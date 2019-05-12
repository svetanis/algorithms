package com.svetanis.algorithms.dp.coins;

public final class CoinChangeRecursive {
  // n - size of array of coins S
  // V - coin value
  // returns the count of ways we can sum
  // S[0 ... n-1] coins to get sum V
  public static int count(int[] s, int n, int v) {
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

    // return the sum of solutions
    // 1. including S[n - 1]: count(S[], n, V - S[n-1])
    int incl = count(s, n, v - s[n - 1]); 
    // 2. excluding S[n - 1]: count(S[], n - 1, V)
    int excl = count(s, n - 1, v);
    return incl + excl; 
  }

  public static void main(String[] args) {
    int v = 4;
    int[] coins = { 1, 2, 3 };
    int n = coins.length;
    System.out.println(count(coins, n, v));
  }
}
