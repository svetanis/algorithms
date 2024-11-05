package com.svetanis.algorithms.search.binary.math;

// 374. Guess Number Higher or Lower

public final class GuessNumber {
	// Time Complexity: O(log n)

	public static int binary(int n) {
		int left = 1;
		int right = n;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			int guess = guess(mid);
			if (guess == 0) {
				return mid;
			} else if (guess < 0) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * Forward declaration of guess API.
	 * 
	 * @param num
	 *          your guess
	 * @return -1 if num is higher than the picked number 1 if num is lower than the picked number otherwise return 0 int
	 *         guess(int num);
	 */

	private static int guess(int n) {
		return 0;
	}

	public static void main(String[] args) {}
}
