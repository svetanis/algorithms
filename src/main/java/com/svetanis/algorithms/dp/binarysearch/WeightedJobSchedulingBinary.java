package com.svetanis.algorithms.dp.scheduling;

import static java.lang.Math.max;

import java.util.Arrays;
import java.util.Comparator;

// 1235. Maximum Profit in Job Scheduling

public final class WeightedJobSchedulingBinary {
	// Time Complexity: O(n * log n)

	public static int maxProfit(int[] start, int[] end, int[] profit) {
		// sort jobs according to finish time
		int[][] jobs = zip(start, end, profit);
		Arrays.sort(jobs, Comparator.comparingInt(job -> job[1]));

		int n = profit.length;
		int[] dp = new int[n];
		dp[0] = jobs[0][2];
		for (int i = 1; i < n; i++) {
			int incl = jobs[i][2];
			// int latest = linear(jobs, jobs[i][0], i);
			int latest = binary(jobs, jobs[i][0], i);
			if (latest != -1) {
				incl += dp[latest];
			}
			// store max of including and excluding
			dp[i] = max(incl, dp[i - 1]);
		}
		return dp[n - 1];
	}

	private static int[][] zip(int[] start, int[] end, int[] profit) {
		int n = start.length;
		int[][] jobs = new int[n][3];
		for (int i = 0; i < n; i++) {
			jobs[i] = new int[] { start[i], end[i], profit[i] };
		}
		return jobs;
	}

	private static int binary(int[][] jobs, int start, int i) {
		int low = 0;
		int high = i - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (jobs[mid][1] <= start) {
				if (jobs[mid + 1][1] <= start) {
					low = mid + 1;
				} else {
					return mid;
				}
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}

	private static int linear(int[][] jobs, int start, int i) {
		for (int j = i - 1; j >= 0; j--) {
			if (jobs[j][1] <= start) {
				return j;
			}

		}
		return -1;
	}

	public static void main(String[] args) {
		int[] s1 = { 1, 2, 3, 3 };
		int[] e1 = { 3, 4, 5, 6 };
		int[] p1 = { 50, 10, 40, 70 };
		System.out.println(maxProfit(s1, e1, p1)); // 120

		int[] s2 = { 1, 2, 3, 4, 6 };
		int[] e2 = { 3, 5, 10, 6, 9 };
		int[] p2 = { 20, 20, 100, 70, 60 };
		System.out.println(maxProfit(s2, e2, p2)); // 150

		int[] s3 = { 1, 1, 1 };
		int[] e3 = { 2, 3, 4 };
		int[] p3 = { 5, 6, 4 };
		System.out.println(maxProfit(s3, e3, p3)); // 6

		int[] s4 = { 24, 24, 7, 2, 1, 13, 6, 14, 18, 24 };
		int[] e4 = { 27, 27, 20, 7, 14, 22, 20, 24, 19, 27 };
		int[] p4 = { 6, 1, 4, 2, 3, 6, 5, 6, 9, 8 };
		System.out.println(maxProfit(s4, e4, p4)); // 20

	}
}
