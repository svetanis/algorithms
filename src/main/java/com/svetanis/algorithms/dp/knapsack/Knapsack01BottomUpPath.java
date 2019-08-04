package com.svetanis.algorithms.dp.knapsack;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.dp.knapsack.Knapsack01SpaceOptimized.build;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.max;

import java.util.List;

//Given two integer arrays to represent weights and profits of ‘N’ items, 
//we need to find a subset of these items which will give us maximum profit 
//such that their cumulative weight is not more than a given number ‘C’. 
//Each item can only be selected once, which means either 
//we put an item in the knapsack or we skip it.

public final class Knapsack01BottomUpPath {

  // Time Complexity: O(n*W)
  // Space Complexity: O(n*W)

  public static int knapsack(int[] w, int[] v, int max) {
    int n = w.length;
    int m = v.length;
    if (max <= 0 || n == 0 || n != m) {
      return 0;
    }
    Item[] items = build(v, w);
    return knapsack(items, max, n);
  }

  private static int knapsack(Item[] items, int max, int n) {
    int[][] dp = new int[n + 1][max + 1];
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= max; ++j) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (items[i - 1].weight <= j) {
          int excl = dp[i - 1][j];
          int incl = dp[i - 1][j - items[i - 1].weight] + items[i - 1].value;
          dp[i][j] = max(incl, excl);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    printSelected(dp, items, max);
    return dp[n][max];
  }

  private static void printSelected(int[][] dp, Item[] items, int max) {
    int n = items.length;
    int profit = dp[n][max];
    List<Integer> list = newArrayList();
    for (int i = n - 1; i >= 0; i--) {
      if (profit != dp[i][max]) {
        list.add(items[i].weight);
        max -= items[i].weight;
        profit -= items[i].value;
      }
    }
    if (profit != 0) {
      list.add(items[0].weight);
    }
    print(list);
  }

  public static void main(String[] args) {
    int max = 50;
    int[] value = { 60, 100, 120 };
    int[] weight = { 10, 20, 30 };
    System.out.println(knapsack(weight, value, max));
  }
}
