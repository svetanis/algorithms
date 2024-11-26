package com.svetanis.algorithms.dp.maxprofit;

import static java.lang.Math.max;
import static java.lang.Math.min;

// 121. Best Time to Buy and Sell Stock

// maximize your profit by choosing a single day 
// to buy one stock and choosing a different day 
// in the future to sell that stock.

public final class BestTimeBuySellStock {
	// Time Complexity: O(n)

	public static int maxProfit(int[] prices) {
		int max = 0; // max profit
		int min = prices[0]; // min buying price
		for (int price : prices) {
			// calculate the maxProfit by comparing
			// current maxProfit with the difference
			// of the current price and the minPrice
			max = max(max, price - min);
			// update the minPrice if a lower price found
			min = min(min, price);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 7, 1, 5, 3, 6, 4 }; // 5
		System.out.println(maxProfit(a1));
		int[] a2 = { 7, 6, 4, 3, 1 }; // 0
		System.out.println(maxProfit(a2));
	}
}
