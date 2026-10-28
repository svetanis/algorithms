package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 1475. Final Prices With a Special Discount in a Shop
//
// prices[i] is the price of item i in the shop; the answer is an int array of the same length.
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
    int[] paid = new int[n]; // ANSWER: paid[i] is what item i costs after its discount
    Deque<Integer> stack = new ArrayDeque<>(); // prices to the right, never falling to the top
    for (int i = n - 1; i >= 0; i--) { // WALK: right to left, item i arrives
      while (!stack.isEmpty() && stack.peek() > prices[i]) { // DROP: strictly higher; an equal price is a discount and stays
        stack.pop(); // POP: higher: never the discount for i or anyone to its left
      }
      paid[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek(); // RECORD: push-time reading
      stack.push(prices[i]); // PUSH: prices[i] may be the discount for an item further left
    }
    return paid; // RETURN: one price paid per item
  }

  public static void main(String[] args) {
    int[] a1 = { 8, 4, 6, 2, 3 };
    System.out.println(Arrays.toString(finalPrices(a1))); // [4, 2, 4, 2, 3]

    int[] a2 = { 1, 2, 3, 4, 5 };
    System.out.println(Arrays.toString(finalPrices(a2))); // [1, 2, 3, 4, 5]

    int[] a3 = { 10, 1, 1, 6 };
    System.out.println(Arrays.toString(finalPrices(a3))); // [9, 0, 1, 6]
  }
}
