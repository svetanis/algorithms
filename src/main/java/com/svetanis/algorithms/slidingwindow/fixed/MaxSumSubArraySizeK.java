package com.svetanis.algorithms.slidingwindow.fixed;

import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.List;

// Maximum sum subarray of size k -- no LeetCode number; source unknown
//
// Given an array of positive numbers and a positive number ‘k’, 
// find the maximum sum of any contiguous subarray of size ‘k’.
// Returns that sum, or -1 when the list is shorter than k. A sum past 2^31 - 1 wraps.
//
// One step of a fixed window changes two numbers: list[i] enters on the right and
// list[i - k] leaves k places behind it, so the sum is edited, not rebuilt.
//
// Time: O(n) -- k additions build the first window, then one edit per remaining index
// Space: O(1) -- the running sum and the best sum
//
// Siblings: MaxAverageSubArraySubmit (643) is the same loop over an int[], dividing the
// best sum by k, with no k > n guard. MaxAverageSubArray also returns where the window is.

public final class MaxSumSubArraySizeK {

	public static int maxSum(int k, List<Integer> list) {
		int n = list.size();
		if (n < k) {
			return -1; // RETURN: no window of size k fits
		}
		int max = sum(list, k); // BUILD: the first window, list[0..k-1]
		int sum = max;
		for (int i = k; i < n; i++) {
			sum += list.get(i) - list.get(i - k); // IN list[i], OUT list[i - k]
			max = max(max, sum); // RECORD
		}
		return max;
	}

	private static int sum(List<Integer> list, int k) {
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += list.get(i);
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(maxSum(4, asList(1, 4, 2, 10, 2, 3, 1, 0, 20))); // 24
		System.out.println(maxSum(3, asList(2, 1, 5, 1, 3, 2))); // 9
		System.out.println(maxSum(2, asList(2, 3, 4, 1, 5))); // 7
	}
}
