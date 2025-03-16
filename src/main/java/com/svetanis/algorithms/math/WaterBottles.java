package com.svetanis.algorithms.math;

// 1518. Water Bottles

public final class WaterBottles {

	public static int waterBottles(int bottles, int exchange) {
		int total = bottles;
		while(bottles >= exchange) {
			int received = bottles/exchange;
			int reminder = bottles % exchange;
			total += received;
			bottles = received + reminder;
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(waterBottles(9, 3)); // 13
		System.out.println(waterBottles(15, 4)); // 19
		System.out.println(waterBottles(2, 3)); // 2
	}
}