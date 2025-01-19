package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// 209. Minimum Size Subarray Sum

// Given an array of positive integers and a number ‘S,’ 
// find the length of the smallest contiguous subarray 
// whose sum is greater than or equal to ‘S’. 
// Return 0 if no such subarray exists.

public final class MinSizeSubArraySum {
	// Time complexity: O(n)

	public static int minSum(int[] a, int target) {
		int sum = 0;
		int left = 0;
		int n = a.length;
		int min = n + 1;
		for (int right = 0; right < n; right++) {
			sum += a[right];
			while (left < n && sum >= target) {
				min = min(min, right - left + 1);
				sum -= a[left];
				left++;
			}
		}
		return min <= n ? min : 0;
	}

	public static int minSum(int target, List<Integer> list) {
		int sum = 0;
		int left = 0;
		int n = list.size();
		int min = Integer.MAX_VALUE;
		for (int right = 0; right < n; right++) {
			sum += list.get(right);
			while (sum >= target && left < n) {
				min = min(min, right - left + 1);
				sum -= list.get(left);
				left++;
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 1, 2, 4, 3 };
		int[] a2 = { 1, 4, 4 };
		int[] a3 = { 1, 1, 1, 1, 1, 1, 1, 1 };
		System.out.println(minSum(a1, 7)); // 2
		System.out.println(minSum(a2, 4)); // 1
		System.out.println(minSum(a3, 11)); // 0

		System.out.println(minSum(51, asList(1, 4, 45, 6, 0, 19)));
		System.out.println(minSum(9, asList(1, 10, 5, 2, 7)));
		System.out.println(minSum(280, asList(1, 11, 100, 1, 0, 200, 3, 2, 1, 250)));
		System.out.println(minSum(10, asList(1, 4, 1, 7, 3, 0, 2, 5))); // 2
	}
}
