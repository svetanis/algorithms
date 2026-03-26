package com.svetanis.algorithms.dp.countways;

// 2222. Number of Ways to Select Buildings

public final class CountWaysToSelectBuildings {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public long countWays(String s) {
		int[] left = new int[2];
		int[] right = new int[2];
		for (char c : s.toCharArray()) {
			int digit = c - '0';
			right[digit] += 1;
		}
		long count = 0;
		for (char c : s.toCharArray()) {
			int digit = c - '0';
			right[digit] -= 1;
			int flipped = digit ^ 1;
			count += (long) left[flipped] * right[flipped];
			left[digit] += 1;
		}
		return count;
	}

	public static void main(String[] args) {
		CountWaysToSelectBuildings cwt = new CountWaysToSelectBuildings();
		System.out.println(cwt.countWays("001101")); // 6
		System.out.println(cwt.countWays("11100")); // 0
	}
}
