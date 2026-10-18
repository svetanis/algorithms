package com.svetanis.algorithms.slidingwindow.fixed;

// 1423. Maximum Points You Can Obtain from Cards
//
// Takes the cards' points as a (each at least 1) and k, 1 <= k <= a.length. Cards are taken
// one at a time from either end of the row, k cards in all. Returns the largest total of the
// cards taken.
//
// Whatever is taken from the two ends, the cards left behind are one unbroken block of
// n - k cards in the middle. So the most that can be taken is the total minus the least that
// can be left behind, and the block left behind is a fixed window of size n - k.
//
// Time: maxScore O(n) -- one pass for the total, then each card enters the window once and
//   leaves once; maxScoreTwoPointers O(k) -- it reads only the k cards at each end
// Space: O(1) for both -- a few running sums
//
// No other file solves 1423; the two methods below are its two solutions.

public final class MaxScore {

	// take k cards from the two ends => the cards LEFT BEHIND are a
	// contiguous window of size n - k. Minimize that window instead.

	// Time Complexity: O(n) -- sums the whole array, then scans it
	// Aux Space: O(1)
	public static int maxScore(int[] a, int k) {
		int n = a.length;
		int totalScore = 0;
		for (int i = 0; i < n; i++) {
			totalScore += a[i]; // COUNT: every card
		}
		if (k >= n) {
			return totalScore; // RETURN: every card is taken, no block is left behind
		}
		int left = 0;
		int max = 0;
		int score = 0;
		for (int right = 0; right < n; right++) {
			score += a[right]; // IN: the block LEFT BEHIND grows by a[right]
			if (right - left + 1 == n - k) { // the block holds n - k cards
				max = Math.max(max, totalScore - score); // RECORD: what was taken = total - what is left
				score -= a[left]; // OUT
				left++;
			}
		}
		return max;
	}

	// Time Complexity: O(k) -- touches only the k cards at each end,
	// never the middle of the array
	// Aux Space: O(1)
	public static int maxScoreTwoPointers(int[] a, int k) {
		int n = a.length;
		int left = 0;
		int leftScore = 0;
		for (left = 0; left < k; left++) {
			leftScore += a[left]; // BUILD: all k cards from the left end
		}
		int max = leftScore;
		int right = n - 1;
		int rightScore = 0;
		for (left = k - 1; left >= 0; left--) {
			leftScore -= a[left]; // OUT: give back the innermost card from the left
			rightScore += a[right--]; // IN: take one more card from the right end
			max = Math.max(max, leftScore + rightScore); // RECORD
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
