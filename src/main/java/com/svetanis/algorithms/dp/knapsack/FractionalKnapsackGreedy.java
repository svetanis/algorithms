package com.svetanis.algorithms.dp.knapsack;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.min;
import static java.util.Collections.sort;
import java.util.List;

public final class FractionalKnapsackGreedy {

  public static double knapsack(int max, int[] weights, int[] values) {
    int n = values.length;
    double value = 0;
    List<Item> items = convert(weights, values);
    for (int i = 0; i < n; i++) {
      if (max == 0) {
        return value;
      }
      int current = min(items.get(i).weight, max);
      max = max - current;
      value += (double) (current * items.get(i).unit);
    }
    return value;
  }

  private static List<Item> convert(int[] weights, int[] values) {
    int n = values.length;
    List<Item> items = newArrayList();
    for (int i = 0; i < n; i++) {
      double unit = (double) values[i] / weights[i];
      items.add(new Item(values[i], weights[i], unit));
    }
    sort(items);
    return copyOf(items);
  }

  public static void main(String[] args) {
    int max = 50;
    int[] values = { 60, 100, 120 };
    int[] weights = { 10, 20, 30 };
    System.out.println(knapsack(max, weights, values));
  }

  private static class Item implements Comparable<Item> {
    private int value;
    private int weight;
    private double unit;

    public Item(int value, int weight, double unit) {
      this.value = value;
      this.weight = weight;
      this.unit = unit;
    }

    @Override
    public int compareTo(Item other) {
      if (other.unit > this.unit) {
        return 1;
      } else if (other.unit < this.unit) {
        return -1;
      } else {
        return 0;
      }
    }

    @Override
    public String toString() {
      return unit + ", " + value + ", " + weight;
    }
  }
}

