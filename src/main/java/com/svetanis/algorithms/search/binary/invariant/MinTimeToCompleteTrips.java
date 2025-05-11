package com.svetanis.algorithms.search.binary.invariant;

import java.util.Arrays;

// 2187. Minimum Time to Complete Trips

public final class MinTimeToCompleteTrips {
	// Time Complexity: O(n * log maxTime)
	// Space Complexity: O(1)

	public static long minTime(int[] time, int totalTrips) {
		int min = Arrays.stream(time).min().getAsInt();
		long low = 1;
		long high = (long) min * totalTrips;
		while (low < high) {
			long mid = low + (high - low) / 2;
			if (count(time, mid) >= totalTrips) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private static long count(int[] a, long mid) {
		long total = 0;
		for (int trip : a) {
			total += mid / trip;
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		System.out.println(minTime(a1, 5)); // 3
		int[] a2 = { 2 };
		System.out.println(minTime(a2, 1)); // 2
	}
}
