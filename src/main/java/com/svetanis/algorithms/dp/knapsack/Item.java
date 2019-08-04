package com.svetanis.algorithms.dp.knapsack;

public final class Item {
  protected int value;
  protected int weight;

  public Item(int value, int weight) {
    this.value = value;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return value + ", " + weight;
  }
}