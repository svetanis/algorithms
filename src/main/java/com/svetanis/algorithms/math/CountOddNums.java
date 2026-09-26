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

	// odd numbers in 1..x are (x + 1) / 2, so the answer is the odds up to
	// high minus the odds below low
	public static int countOddsByPrefix(int low, int high) {
		int oddsUpToHigh = (high + 1) / 2;
		int oddsBeforeLow = low / 2;
		return oddsUpToHigh - oddsBeforeLow;
	}

	public static void main(String[] args) {
		System.out.println(countOdds(3, 7)); // 3
		System.out.println(countOdds(8, 10)); // 1

		System.out.println(countOddsByPrefix(3, 7)); // 3
		System.out.println(countOddsByPrefix(8, 10)); // 1
	}
}