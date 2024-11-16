package com.svetanis.algorithms.search.binary;

// 875. Koko Eating Bananas

public final class KokoEatingBananas {
	// Time Complexity: O(n * log (max(piles))

	public static int minEatingSpeed(int[] piles, int h) {
		int min = 1;
		int max = (int) 1e9;
		while (min < max) {
			// potential eating speed
			int mid = min + (max - min) / 2;
			int total = totalHours(piles, mid);
			if (total <= h) {
				max = mid;
			} else {
				min = mid + 1;
			}
		}
		return min;
	}

	private static int totalHours(int[] piles, int speed) {
		int total = 0;
		for (int pile : piles) {
			total += (pile + speed - 1) / speed;
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 6, 7, 11 };
		System.out.println(minEatingSpeed(a1, 8)); // 4
		int[] a2 = { 30, 11, 23, 4, 20 };
		System.out.println(minEatingSpeed(a2, 5)); // 30
		int[] a3 = { 30, 11, 23, 4, 20 };
		System.out.println(minEatingSpeed(a3, 6)); // 23
	}
}
