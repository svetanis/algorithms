package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

import com.svetanis.java.base.utils.Print;

// 1475. Final Prices With a Special Discount in a Shop
//
// Item i is discounted by the price of the first item to its right that costs the same or
// less; with no such item there is no discount. Return what is paid for each item.
//
// The discount is the next smaller-or-equal price. Scanning from the right, the answer is
// read at push time: after popping every higher price, the top is the discount.

public final class FinalPricesWithSpecialDiscount {
  // Time Complexity: O(n), every price is pushed once and popped at most once
  // Space Complexity: O(n) for the stack

  public static int[] finalPrices(int[] prices) {
    int n = prices.length;
    int[] paid = new int[n];
    Deque<Integer> stack = new ArrayDeque<>(); // prices to the right, never falling to the top
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() > prices[i]) {
        stack.pop(); // higher: never the discount for i or anyone to its left
      }
      paid[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek(); // push-time reading
      stack.push(prices[i]);
    }
    return paid;
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
