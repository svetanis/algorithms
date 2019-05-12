package com.svetanis.algorithms.dp.knapsack;

import static java.lang.Math.max;

public final class Knapsack01Recursive {

  public static int knapsack(int[] w, int[] v, int max, int n) {
    // base case
    if (n == 1 || max == 0) {
      return 0;
    }

    if (w[n - 1] > max) {
      return knapsack(w, v, max, n - 1);
    } else {
      int incl = knapsack(w, v, max - w[n - 1], n - 1) + v[n - 1];
      int excl = knapsack(w, v, max, n - 1);
      return max(incl, excl);
    }
  }

  public static void main(String[] args) {
    int max = 50;
    int[] weight = { 10, 20, 30 };
    int[] value = { 60, 100, 120 };
    System.out.println(knapsack(weight, value, max, value.length));
  }
}
