package com.svetanis.algorithms.search.binary;

import java.util.Arrays;

// 1011. Capacity To Ship Packages Within D Days

public final class CapacityToShipPackages {

	public static int shipWithinDays(int[] weights, int days) {
		int low = Arrays.stream(weights).max().getAsInt();
		int high = Arrays.stream(weights).sum();
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (canShip(weights, mid, days)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private static boolean canShip(int[] weights, int capacity, int days) {
		int day = 1;
		int load = 0;
		for (int weight : weights) {
			load += weight;
			if (load > capacity) {
				load = weight;
				day++;
			}
		}
		return day <= days;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(shipWithinDays(a1, 5)); // 15

		int[] a2 = { 3, 2, 2, 4, 1, 4 };
		System.out.println(shipWithinDays(a2, 3)); // 6

		int[] a3 = { 1, 2, 3, 1, 1 };
		System.out.println(shipWithinDays(a3, 4)); // 3
	}
}
