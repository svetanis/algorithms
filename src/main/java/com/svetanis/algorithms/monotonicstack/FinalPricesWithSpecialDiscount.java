package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

import com.svetanis.java.base.utils.Print;

// 1475. Final Prices With a Special Discount in a Shop

public final class FinalPricesWithSpecialDiscount {
  // Time complexity: O(n)
  // Space complexity: O(1)

  public static int[] finalPrices(int[] prices) {
    int n = prices.length;
    int[] a = new int[n];
    Deque<Integer> dq = new ArrayDeque<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!dq.isEmpty() && dq.peek() > prices[i]) {
        dq.pop();
      }
      a[i] = dq.isEmpty() ? prices[i] : prices[i] - dq.peek();
      dq.push(prices[i]);
    }
    return a;
  }

  public static void main(String[] args) {
    int[] a1 = { 8, 4, 6, 2, 3 };
    Print.print(finalPrices(a1)); // [4, 2, 4, 2, 3]

    int[] a2 = { 1, 2, 3, 4, 5 };
    Print.print(finalPrices(a2)); // [1, 2, 3, 4, 5]

    int[] a3 = { 10, 1, 1, 6 };
    Print.print(finalPrices(a3)); // [9, 0, 1, 6]
  }
}
