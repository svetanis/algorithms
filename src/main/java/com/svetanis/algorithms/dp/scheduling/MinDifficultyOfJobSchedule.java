package com.svetanis.algorithms.dp.scheduling;

// 1335. Minimum Difficulty of a Job Schedule

public final class MinDifficultyOfJobSchedule {
	// Time Complexity: O(n * log n)

	public static int minDifficulty(int[] jobs, int d) {
		return 0;
	}

	public static void main(String[] args) {
		int[] jobs1 = { 6, 5, 4, 3, 2, 1 };
		System.out.println(minDifficulty(jobs1, 2)); // 7

		int[] jobs2 = { 9, 9, 9 };
		System.out.println(minDifficulty(jobs2, 4)); // -1

		int[] jobs3 = { 1, 1, 1 };
		System.out.println(minDifficulty(jobs3, 3)); // 3
	}
}
