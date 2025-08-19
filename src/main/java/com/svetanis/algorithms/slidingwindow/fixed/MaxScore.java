package com.svetanis.algorithms.slidingwindow.fixed;

// 1423. Maximum Points You Can Obtain from Cards

public final class MaxScore {
	// Time Complexity: O(k)
	// Aux Space: O(1)

	public static int maxScore(int[] a, int k) {
		int n = a.length;
		int totalScore = 0;
		for (int i = 0; i < n; i++) {
			totalScore += a[i];
		}
		if (k >= n) {
			return totalScore;
		}
		int left = 0;
		int max = 0;
		int score = 0;
		for (int right = 0; right < n; right++) {
			score += a[right];
			if (right - left + 1 == n - k) {
				max = Math.max(max, totalScore - score);
				score -= a[left];
				left++;
			}
		}
		return max;
	}

	public static int maxScoreTwoPointers(int[] a, int k) {
		int n = a.length;
		int left = 0;
		int leftScore = 0;
		for (left = 0; left < k; left++) {
			leftScore += a[left];
		}
		int max = leftScore;
		int right = n - 1;
		int rightScore = 0;
		for (left = k - 1; left >= 0; left--) {
			leftScore -= a[left];
			rightScore += a[right--];
			max = Math.max(max, leftScore + rightScore);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4, 5, 6, 1 };
		System.out.println(maxScore(a1, 3)); // 12
		int[] a2 = { 2, 2, 2 };
		System.out.println(maxScore(a2, 2)); // 4
		int[] a3 = { 9, 7, 7, 9, 7, 7, 9 };
		System.out.println(maxScore(a3, 7)); // 55
		int[] a4 = { 1, 79, 80, 1, 1, 1, 200, 1 };
		System.out.println(maxScore(a4, 3)); // 202
		int[] a5 = { 100, 40, 17, 9, 73, 75 };
		System.out.println(maxScore(a5, 3)); // 248
	}
}
