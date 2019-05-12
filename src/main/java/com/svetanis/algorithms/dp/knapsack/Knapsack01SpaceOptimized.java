package com.svetanis.algorithms.dp.knapsack;

import static java.lang.Math.max;

public final class Knapsack01SpaceOptimized {

  public static int knapsack(int max, Item[] items) {
    // Time Complexity: O(n*W)
    // Space Complexity: O(W)

    int n = items.length;
    int[] a = new int[max + 1];

    for (int i = 0; i <= n; ++i) {
      a[i] = 0;
    }

    for (int i = 0; i < n; ++i) {
      for (int w = max; w >= items[i].weight; --w) {
        int incl = a[w - items[i].weight] + items[i].value;
        int excl = a[w];
        a[w] = max(incl, excl);
      }
    }
    return a[max];
  }

  public static void main(String[] args) {
    int max = 50;
    int[] value = { 60, 100, 120 };
    int[] weight = { 10, 20, 30 };
    Item[] items = build(value, weight);
    System.out.println(knapsack(max, items));
  }

  private static Item[] build(int[] value, int[] weight) {
    int n = value.length;
    Item[] items = new Item[n];
    for (int i = 0; i < n; i++) {
      items[i] = new Item(value[i], weight[i]);
    }
    return items;
  }

  private static class Item {
    private int value;
    private int weight;

    public Item(int value, int weight) {
      this.value = value;
      this.weight = weight;
    }

    @Override
    public String toString() {
      return value + ", " + weight;
    }
  }
}

