package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// Given an array of positive integers and a number ‘S,’ 
// find the length of the smallest contiguous subarray 
// whose sum is greater than or equal to ‘S’. 
// Return 0 if no such subarray exists.

public final class MinSizeSubArraySum {
	// Time complexity: O(n)

	public static int minSum(int target, List<Integer> list) {
		int sum = 0;
		int left = 0;
		int n = list.size();
		int min = n;
		for (int right = 0; right < n; right++) {
			sum += list.get(right);
			while (sum >= target && left < n) {
				min = min(min, right - left + 1);
				sum -= list.get(left);
				left++;
			}
		}
		return min;
	}

	public static void main(String[] args) {
		System.out.println(minSum(51, asList(1, 4, 45, 6, 0, 19)));
		System.out.println(minSum(9, asList(1, 10, 5, 2, 7)));
		System.out.println(minSum(280, asList(1, 11, 100, 1, 0, 200, 3, 2, 1, 250)));
		System.out.println(minSum(10, asList(1, 4, 1, 7, 3, 0, 2, 5))); // 2
	}
}
