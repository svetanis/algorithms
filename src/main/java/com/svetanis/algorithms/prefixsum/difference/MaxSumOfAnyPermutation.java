package com.svetanis.algorithms.prefixsum.difference;

import java.util.Arrays;

// 1589. Maximum Sum Obtained of Any Permutation
//
// Input: nums -- a list of n numbers; requests[i] = [start, end] -- asks for
// nums[start] + ... + nums[end], both ends included. Arrange nums in any order so that the total
// of all the request sums is as large as possible, and return that total modulo 1e9 + 7. nums is
// sorted in place, so the caller's array comes back reordered. On LeetCode: 1 <= n <= 10^5,
// 0 <= nums[i] <= 10^5, 1 <= requests.length <= 10^5, 0 <= start <= end < n, so one product
// nums[i] * coverage reaches 10^10 and needs a long.
//
// The one idea: a request sum adds nums[i] once for every request that covers index i, so the
// total is the sum of nums[i] * (number of requests covering i). The best order puts the largest
// number on the most covered index, the next largest on the next most covered, and so on: if a
// bigger number sat on a less covered index than a smaller one, swapping the two would add
// (bigger - smaller) * (difference in coverage), which is never negative. So sort both lists and
// multiply them position by position.
//
// The coverage counts come from a mark array. One cell freq[i] starts as the change in coverage
// at index i: +1 where a request starts, -1 at end + 1, because index end itself is still
// covered. A request that ends at the last index has no end + 1 cell and needs none. A running
// sum then turns each cell into the number of requests covering that index.
//
// Time: O(n log n + m) -- two marks per request (m requests), a running sum, then two sorts of n.
// Space: O(n) -- the coverage counts; both sorts are in place.

public final class MaxSumOfAnyPermutation {

	private static final int MOD = (int) 1e9 + 7;

	public static int maxSum(int[] nums, int[][] requests) {
		int n = nums.length;
		int[] freq = indexFrequency(n, requests); // MARKS
		for (int i = 1; i < n; i++) { // WALK: running sum
			freq[i] += freq[i - 1]; // now: number of requests covering index i
		}
		Arrays.sort(nums); // SORT: the numbers, smallest first
		Arrays.sort(freq); // SORT: the coverages too, so largest meets largest at the same position
		long sum = 0;
		for (int i = 0; i < n; i++) { // PAIR: position by position
			sum = (sum + 1L * nums[i] * freq[i]) % MOD; // 1L: the product can pass the int range
		}
		return (int) sum; // RETURN
	}

	private static int[] indexFrequency(int n, int[][] requests) {
		int[] a = new int[n];
		for (int[] request : requests) {
			int start = request[0];
			int end = request[1];
			a[start]++; // START
			if (end + 1 < n) { // STOP: end is included, so coverage drops one index later
				a[end + 1]--;
			}
		}
		return a; // RETURN
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
