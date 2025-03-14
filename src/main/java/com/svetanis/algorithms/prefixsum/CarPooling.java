package com.svetanis.algorithms.prefixsum;

// 1094. Car Pooling

public final class CarPooling {
	// Time complexity: O(n + m)
	
	public static boolean carPooling(int[][] trips, int capacity) {
		int[] passengers = new int[1001];
		for (int[] trip : trips) {
			int count = trip[0];
			int start = trip[1];
			int end = trip[2];
			passengers[start] += count;
			passengers[end] -= count;
		}
		int current = 0;
		for (int passenger : passengers) {
			current += passenger;
			if (current > capacity) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] trips1 = { { 2, 1, 5 }, { 3, 3, 7 } };
		System.out.println(carPooling(trips1, 4)); // false
		System.out.println(carPooling(trips1, 5)); // true
	}
}
