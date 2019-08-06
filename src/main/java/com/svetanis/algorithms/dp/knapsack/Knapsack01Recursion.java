package com.svetanis.algorithms.dp.knapsack;

import static com.svetanis.algorithms.dp.knapsack.Knapsack01SpaceOptimized.build;
import static java.lang.Integer.MIN_VALUE;
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
    if (max <= 0 || n == 0 || n != m) {
      return 0;
    }
    Item[] items = build(v, w);
    return knapsack(items, max, n - 1);
  }

  private static int knapsack(Item[] items, int max, int n) {

    // base case: negative capacity
    if (max < 0) {
      return MIN_VALUE;
    }

    // base case: no items left or capacity becomes 0
    if (n < 0 || max == 0) {
      return 0;
    }

    // 1. include current item n in knapsack and recur for
    // remaining n - 1 with decreased capacity max - w[n]
    int incl =  items[n].value + knapsack(items, max - items[n].weight, n - 1);
    // 2. exclude current item n from knapsack and
    // recur for remaining items n - 1
    int excl = knapsack(items, max, n - 1);

    return max(incl, excl);
  }

  public static void main(String[] args) {
    int max = 10;
    int[] weight = { 1, 2, 3, 8, 7, 4 };
    int[] value = { 20, 5, 10, 40, 15, 25 };
    System.out.println(knapsack(weight, value, max));
  }
}
