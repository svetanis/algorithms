package com.svetanis.algorithms.slidingwindow.array;

// Count subarrays with sum in a given range -- no LeetCode number; source unknown
//
// Given an array of positive integers and a range [start, end], find the number of
// subarrays whose sum lies in the range, both ends included. Assumes start <= end.
//
// Two counts with one bound each make the range: (sum <= end) minus (sum <= start - 1)
// leaves exactly the sums from start to end. Each one-bound count is a counting window:
// with positive values, dropping from the left lowers the sum, so for each end right the
// window keeps the longest a[left..right] with sum <= k, and every start inside it
// works: right - left + 1 of them.
//
// Time: O(n) -- two passes, and in each one right and left only move forward
// Space: O(1) -- a running sum, two indices and a count
//
// Sibling: CountSubArrsSumLessThanK is the one-bound window on its own, counting
// sum < k rather than sum <= k.

public final class CountSubArrsSumInGivenRange {

	public static int countInRange(int[] a, int start, int end) {
		int right = count(a, end);
		int left = count(a, start - 1);
		return right - left; // COUNT: sums in start .. end
	}

	// how many subarrays have sum <= k
	private static int count(int[] a, int k) {
		int n = a.length;
		int sum = 0;
		int count = 0;
		int left = 0;

		for (int right = 0; right < n; right++) {
			sum += a[right]; // ADD
			while (left <= right && sum > k) { // SHRINK while the sum is above k
				sum -= a[left];
				left++;
			}
			count += right - left + 1; // COUNT: starts left .. right, all ending at right
		}
		return count;
	}

	public static void main(String[] args) {

		int[] a1 = { 1, 4, 6 };
		System.out.println(countInRange(a1, 3, 8));

		int[] a2 = { 2, 3, 5, 8 };
		System.out.println(countInRange(a2, 4, 13));

	}
}
