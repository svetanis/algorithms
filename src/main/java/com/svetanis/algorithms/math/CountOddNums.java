package com.svetanis.algorithms.math;

// 1523. Count Odd Numbers in an Interval Range

public final class CountOddNums {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static int countOdds(int low, int high) {
		int n = high - low + 1;
		if (low % 2 == 1 && n % 2 == 1) {
			return n / 2 + 1;
		}
		return n / 2;
	}

	public static int countOdds2(int low, int high) {
		int oddsUpToHigh = (high + 1) / 2;
		int oddsBeforeLow = low / 2;
		return oddsUpToHigh - oddsBeforeLow;
	}

	public static void main(String[] args) {
		System.out.println(countOdds(3, 7)); // 3
		System.out.println(countOdds(8, 10)); // 1

		System.out.println(countOdds2(3, 7)); // 3
		System.out.println(countOdds2(8, 10)); // 1
	}
}