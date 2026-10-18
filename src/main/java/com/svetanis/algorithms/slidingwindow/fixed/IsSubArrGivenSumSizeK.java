package com.svetanis.algorithms.slidingwindow.fixed;

// Subarray of size k with a given sum -- no LeetCode number; source unknown
//
// Given an array, an integer k and a sum, check if there exists a subarray of size k
// whose sum equals the given sum. Returns true or false. Assumes 1 <= k <= a.length: a
// larger k, or an empty array, throws ArrayIndexOutOfBoundsException.
//
// One step of a fixed window changes two numbers: a[i] enters on the right and a[i - k]
// leaves k places behind it. So the next window's sum is the last one edited by those two
// numbers, not rebuilt from k additions, and the first window that hits target answers.
//
// Time: O(n) -- k additions build the first window, then one edit per remaining index
// Space: O(1) -- one running sum
//
// Sibling: GivenSumSubArrSizeK is the same method, line for line.

public final class IsSubArrGivenSumSizeK {

	public static boolean sum(int[] a, int k, int target) {
		int n = a.length;
		int sum = 0;
		for (int i = 0; i < k; i++) { // BUILD: the first window, a[0..k-1]
			sum += a[i];
		}

		if (sum == target) { // STOP: the first window hits target
			return true;
		}

		for (int i = k; i < n; i++) {
			sum += a[i] - a[i - k]; // IN a[i], OUT a[i - k]
			if (sum == target) { // STOP: the window ending at i hits target
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {

		int[] a1 = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
		System.out.println(sum(a1, 4, 18));

		int[] a2 = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
		System.out.println(sum(a2, 3, 6));

	}
}
