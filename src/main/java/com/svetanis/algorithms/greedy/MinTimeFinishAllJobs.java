package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 2323. Find Minimum Time to Finish All Jobs II

public final class MinTimeFinishAllJobs {
	// Time Complexity: O(n log n)
	// Space Complexity: O(1)

	public static int minTime(int[] jobs, int[] workers) {
		Arrays.sort(jobs);
		Arrays.sort(workers);
		int minDays = 0;
		int n = jobs.length;
		for (int i = 0; i < n; i++) {
			int job = jobs[i];
			int worker = workers[i];
			int days = (job + worker - 1) / worker;
			minDays = Math.max(minDays, days);
		}
		return minDays;
	}

	public static void main(String[] args) {
		int[] jobs = { 5, 2, 4 };
		int[] workers = { 1, 7, 5 };
		System.out.println(minTime(jobs, workers)); // 2

		int[] jobs1 = { 3, 18, 15, 9 };
		int[] workers1 = { 6, 5, 1, 3 };
		System.out.println(minTime(jobs1, workers1)); // 3
	}
}
