package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 901. Online Stock Span

public final class OnlineStockSpan901 {
  // Time complexity: O(n)
  // Space complexity: O(n)

  Deque<Stock> dq = new ArrayDeque<>();

  public int next(int price) {
    int count = 1;
    while (!dq.isEmpty() && dq.peek().price <= price) {
      count += dq.pop().count;
    }
    dq.push(new Stock(price, count));
    return count;
  }

  public static void main(String[] args) {
    OnlineStockSpan901 oss = new OnlineStockSpan901();
    System.out.println(oss.next(100)); // 1
    System.out.println(oss.next(80)); // 1
    System.out.println(oss.next(60)); // 1
    System.out.println(oss.next(70)); // 2
    System.out.println(oss.next(60)); // 1
    System.out.println(oss.next(75)); // 4
    System.out.println(oss.next(85)); // 6
  }

  private static class Stock {
    private int price;
    private int count;

    public Stock(int price, int count) {
      this.price = price;
      this.count = count;
    }
  }
}