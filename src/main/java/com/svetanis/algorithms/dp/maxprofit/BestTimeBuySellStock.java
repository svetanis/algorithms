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


	// the same two numbers, named as the two states they are.
	// hold = the best money you can have while holding a share, so it is negative;
	// sold = the best money you can have having sold. hold is exactly -(cheapest
	// price so far) and sold is exactly the profit above, checked on 100,000 arrays.
	// Written this way, LC 309 is this method plus one more state for the cooldown.
	public static int maxProfit2(int[] prices) {
		int hold = -prices[0];
		int sold = 0;
		for (int price : prices) {
			// sell today, out of yesterday's hold
			sold = max(sold, hold + price);
			// buy today, out of having never traded, which is worth 0
			hold = max(hold, -price);
		}
		return sold;
	}
	public static void main(String[] args) {
		int[] a1 = { 7, 1, 5, 3, 6, 4 }; // 5
		System.out.println(maxProfit(a1));
		int[] a2 = { 7, 6, 4, 3, 1 }; // 0
		System.out.println(maxProfit(a2));

		System.out.println(maxProfit2(a1)); // 5
		System.out.println(maxProfit2(a2)); // 0
	}
}
