package com.svetanis.algorithms.math;

// 1518. Water Bottles

public final class WaterBottles {
	// Time Complexity: O(log bottles), base exchange: each round divides the
	// empties by about exchange
	// Space Complexity: O(1)

	// bottles holds the empties after each round of drinking
	public static int waterBottles(int bottles, int exchange) {
		int total = bottles;
		while (bottles >= exchange) {
			int received = bottles / exchange;
			int remainder = bottles % exchange;
			total += received;
			bottles = received + remainder;
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(waterBottles(9, 3)); // 13
		System.out.println(waterBottles(15, 4)); // 19
		System.out.println(waterBottles(2, 3)); // 2
	}
}