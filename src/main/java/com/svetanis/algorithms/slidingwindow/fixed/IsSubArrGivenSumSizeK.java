package com.svetanis.algorithms.slidingwindow.fixed;

import com.svetanis.java.base.utils.Arrays;

// given an array, an integer K and a Sum
// check if there exists a subarray of size K
// which sum equals to the given sum

public final class IsSubArrGivenSumSizeK {

	public static boolean sum(int[] a, int k, int target) {
		// Time Complexity: O(n)
		// Space Complexity: O(1)
		int n = a.length;
		int sum = Arrays.sum(a, k);

		if (sum == target) {
			return true;
		}

		for (int i = k; i < n; i++) {
			sum += a[i] - a[i - k];
			if (sum == target) {
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
