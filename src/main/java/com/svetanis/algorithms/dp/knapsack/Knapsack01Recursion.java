package com.svetanis.algorithms.dp.knapsack;

import static com.svetanis.algorithms.dp.knapsack.Knapsack01SpaceOptimized.build;
import static java.lang.Math.max;

// Given two integer arrays to represent weights and profits of ‘N’ items, 
// we need to find a subset of these items which will give us maximum profit 
// such that their cumulative weight is not more than a given number ‘C’. 
// Each item can only be selected once, which means either 
// we put an item in the knapsack or we skip it.

public final class Knapsack01Recursion {

  // Time Complexity: O(2^n)
  // Space Complexity: O(n)

  public static int knapsack(int[] w, int[] v, int max) {
    int n = w.length;
    int m = v.length;
    if(max <= 0 || n == 0 || n != m) {
      return 0;
    }
    Item[] items = build(v, w);
    return knapsack(items, max, v.length);
  }

  private static int knapsack(Item[] items, int max, int n) {
    // base case
    if (n == 1 || max <= 0) {
      return 0;
    }

    if (items[n - 1].weight > max) {
      return knapsack(items, max, n - 1);
    } else {
      int incl = knapsack(items, max - items[n - 1].weight, n - 1) + items[n - 1].value;
      int excl = knapsack(items, max, n - 1);
      return max(incl, excl);
    }
  }

  public static void main(String[] args) {
    int max = 50;
    int[] weight = { 10, 20, 30 };
    int[] value = { 60, 100, 120 };
    System.out.println(knapsack(weight, value, max));
  }
}
