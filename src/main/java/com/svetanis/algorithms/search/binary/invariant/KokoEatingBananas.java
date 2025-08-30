package com.svetanis.algorithms.search.binary.invariant;

import java.util.Arrays;

// 875. Koko Eating Bananas

public final class KokoEatingBananas {
	// Time Complexity: O(n * log (max(piles))

	public static int minEatingSpeed(int[] piles, int h) {
		int left = 1; // min possible rate
		int right = (int) 1e9; // max possible rate
		while (left < right) {
			// potential eating speed
			int mid = left + (right - left) / 2;
			int total = timeTaken(piles, mid);
			if (total <= h) {
				// rate is sufficient, try slower
				right = mid;
			} else {
				// rate too slow, need faster rate
				left = mid + 1;
			}
		}
		return left;
	}

	private static int timeTaken(int[] piles, int rate) {
		int total = 0; // total time needed at this rate
		for (int pile : piles) {
			total += (pile + rate - 1) / rate; // ceiling division
		}
		return total;
	}

	// -----------------------------------------------------------
	public static int minEatingSpeed2(int[] piles, int h) {
		int left = 1; // min possible rate
		int right = Arrays.stream(piles).max().getAsInt(); // max possible rate
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (canEatAllBananas(piles, h, mid)) {
				// rate is sufficient, try slower
				right = mid;
			} else {
				// rate too slow, need faster rate
				left = mid + 1;
			}
		}
		return left;
	}

	private static boolean canEatAllBananas(int[] piles, int h, int rate) {
		int total = 0; // total time needed at this rate
		for (int pile : piles) {
			total += (int) Math.ceil((double) pile / rate);
		}
		return total <= h;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 6, 7, 11 };
		System.out.println(minEatingSpeed(a1, 8)); // 4
		int[] a2 = { 30, 11, 23, 4, 20 };
		System.out.println(minEatingSpeed(a2, 5)); // 30
		int[] a3 = { 30, 11, 23, 4, 20 };
		System.out.println(minEatingSpeed2(a3, 6)); // 23
		int[] a4 = { 805306368, 805306368, 805306368 };
		System.out.println(minEatingSpeed(a4, 1000000000)); // 3
	}
}
