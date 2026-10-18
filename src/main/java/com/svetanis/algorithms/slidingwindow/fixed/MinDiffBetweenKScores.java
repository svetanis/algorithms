package com.svetanis.algorithms.slidingwindow.fixed;

import java.util.Arrays;

// 1984. Minimum Difference Between Highest and Lowest of K Scores 
//
// Takes the students' scores as nums and k, 1 <= k <= nums.length. Picks k scores and
// returns the smallest possible difference between the highest and the lowest of them.
// Both methods sort nums in place, so the caller's array comes back reordered.
//
// Once the scores are sorted, the best k sit next to each other: for any k chosen scores,
// the k sorted scores starting at the lowest chosen one end no higher than the highest
// chosen one. So a fixed window of k slides over the sorted scores, and a window's
// difference is its last score minus its first. minDiff walks the window's first index;
// minDiff2 walks its last index and waits until a whole window has been seen.
//
// Time: O(n log n) -- the sort; the window pass after it is O(n)
// Space: O(log n) -- the sort's recursion; the window itself is two numbers
//
// No other file solves 1984.

public final class MinDiffBetweenKScores {

	public static int minDiff(int[] nums, int k) {
		if (k == 1) {
			return 0; // RETURN: one score has no spread
		}
		Arrays.sort(nums);
		int n = nums.length;
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; i <= n - k; i++) { // i is the window's first index
			int curr = nums[i];
			minDiff = Math.min(minDiff, nums[i + k - 1] - curr); // RECORD: last minus first
		}
		return minDiff;
	}

	public static int minDiff2(int[] nums, int k) {
		if (k == 1) {
			return 0; // RETURN: one score has no spread
		}
		Arrays.sort(nums);
		int n = nums.length;
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) { // i is the window's last index
			int curr = nums[i];
			if (i + 1 >= k) { // the first whole window ends at k - 1
				minDiff = Math.min(minDiff, curr - nums[i - k + 1]); // RECORD: last minus first
			}
		}
		return minDiff;
	}

	public static void main(String[] args) {
		int[] a1 = { 90 };
		System.out.println(minDiff(a1, 1)); // 0

		int[] a2 = { 9, 4, 1, 7 };
		System.out.println(minDiff(a2, 2)); // 2
	}
}
