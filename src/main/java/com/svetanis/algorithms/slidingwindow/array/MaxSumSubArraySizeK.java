package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.List;

// Given an array of positive numbers and a positive number ‘k’, 
// find the maximum sum of any contiguous subarray of size ‘k’.

public final class MaxSumSubArraySizeK {
	// Time Complexity: O(n)
	// Aux Space: O(1)

	public static int maxSum(int k, List<Integer> list) {
		int n = list.size();
		if (n < k) {
			return -1;
		}
		int max = sum(list, k);
		int sum = max;
		for (int i = k; i < n; i++) {
			sum += list.get(i) - list.get(i - k);
			max = max(max, sum);
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
