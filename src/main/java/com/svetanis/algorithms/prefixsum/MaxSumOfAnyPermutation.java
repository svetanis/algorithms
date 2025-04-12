package com.svetanis.algorithms.prefixsum;

import java.util.Arrays;

// 1589. Maximum Sum Obtained of Any Permutation

public final class MaxSumOfAnyPermutation {
	// Time complexity: O(n + m)

	private static final int MOD = (int) 1e9 + 7;

	public static int maxSum(int[] nums, int[][] requests) {
		int n = nums.length;
		int[] freq = indexFrequency(n, requests);
		for(int i = 1; i < n; i++) {
			freq[i] += freq[i - 1];
		}
		Arrays.sort(nums);
		Arrays.sort(freq);
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum = (sum + 1L* nums[i] * freq[i]) % MOD;
		}
		return (int) sum;
	}

	private static int[] indexFrequency(int n, int[][] requests) {
		int[] a = new int[n];
		for (int[] request : requests) {
			int start = request[0];
			int end = request[1];
			a[start]++;
			if (end + 1 < n) {
				a[end + 1]--;
			}
		}
		return a;
	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 3, 4, 5 };
		int[][] requests1 = { { 1, 3 }, { 0, 1 } };
		System.out.println(maxSum(nums1, requests1)); // 19

		int[] nums2 = { 1, 2, 3, 4, 5, 6 };
		int[][] requests2 = { { 0, 1 } };
		System.out.println(maxSum(nums2, requests2)); // 11

		int[] nums3 = { 1, 2, 3, 4, 5, 10 };
		int[][] requests3 = { { 0, 2 }, { 1, 3 }, { 1, 1 } };
		System.out.println(maxSum(nums3, requests3)); // 47
	}
}
