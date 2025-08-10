package com.svetanis.algorithms.prefixsum;

// 3096. Minimum Levels to Gain More Points

public final class MinLevelsToGainMorePoints {
	// Time complexity: O(n)
	// Space complexity: O(1)

	public static int minLevels(int[] a) {
		int total = totalScore(a);
		int score = 0;
		for (int i = 1; i < a.length; i++) {
			score += (a[i - 1] == 0) ? -1 : 1;
			if (score > total - score) {
				return i;
			}
		}
		return -1;
	}

	private static int totalScore(int[] a) {
		int total = 0;
		for (int num : a) {
			total += (num == 0) ? -1 : 1;
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 1, 0 };
		System.out.println(minLevels(a1)); // 1
		int[] a2 = { 1, 1, 1, 1, 1 };
		System.out.println(minLevels(a2)); // 3
		int[] a3 = { 0, 0 };
		System.out.println(minLevels(a3)); // -1
		int[] a4 = { 1, 1 };
		System.out.println(minLevels(a4)); // -1
	}
}
