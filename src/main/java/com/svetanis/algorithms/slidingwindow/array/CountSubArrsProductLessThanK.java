package com.svetanis.algorithms.slidingwindow.array;

// 713. Subarray Product Less Than K
//
// Input: an array of positive integers and a number k.
// Return: how many contiguous subarrays have a product strictly less than k.
//
// Every value is at least 1, so a shorter range never has a larger product. Once the
// window a[left..right] has product below k, every subarray ending at right that starts
// inside the window does too: right - left + 1 of them, counted at the one position
// where each ends, so none is counted twice. The window drops from the left while its
// product is k or more. A zero or a negative value breaks that "shorter is smaller"
// property and the count goes wrong; a zero with k = 0 divides by zero.
//
// k <= 1 needs care: the smallest product is 1, so no window is allowed, and an empty
// window's product is still 1 >= k -- the shrink would run past right. The two methods
// stop it two ways: count guards the shrink with left <= right, which stops at the empty
// window and counts right - left + 1 = 0; numSubarrayProductLessThanK returns 0 before
// the loop, so with k > 1 the empty window's product, 1, ends the shrink by itself.
//
// Time: O(n) -- right and left only move forward
// Space: O(1) -- the product, two indices and a count
// The product is an int: it is below k before each multiply, so k * max(a) must fit.
//
// Siblings: SubArrsProductLessThanK runs the same window and lists the subarrays instead
// of counting them. CountSubArrsSumLessThanK is the same count for a sum.
// CountSubArrsGivenProduct counts the product equal to k.

public final class CountSubArrsProductLessThanK {

	public static int count(int[] a, int k) {
		int prod = 1;
		int count = 0;
		int left = 0;
		for (int right = 0; right < a.length; right++) {
			prod *= a[right]; // ADD
			while (left <= right && prod >= k) { // SHRINK while the product is k or more
				prod /= a[left++];
			}
			count += (right - left + 1); // COUNT: starts left .. right, all ending at right
		}
		return count;
	}

	// the same window with no guard on the shrink: k <= 1 is answered first.

	public static int numSubarrayProductLessThanK(int[] nums, int k) {
		if (k <= 1) { // EARLY: no product is below 1, and 1 is not below 1
			return 0;
		}
		int left = 0;
		int product = 1;
		int count = 0;
		for (int right = 0; right < nums.length; right++) {
			product *= nums[right]; // ADD
			while (product >= k) { // SHRINK while broken; k > 1 stops it by the empty window
				product /= nums[left];
				left += 1;
			}
			count += right - left + 1; // COUNT: one allowed window per start left .. right
		}
		return count;
	}

	public static void main(String[] args) {

		int[] a1 = { 1, 2, 3, 4 };
		System.out.println(count(a1, 10));

		int[] a2 = { 1, 9, 2, 8, 6, 4, 3 };
		System.out.println(count(a2, 100));

		int[] a3 = { 5, 3, 2 };
		System.out.println(count(a3, 16));

		int[] a4 = { 100, 200 };
		System.out.println(count(a4, 100));

		int[] a5 = { 100, 200 };
		System.out.println(count(a5, 101));

		int[] a6 = { 10, 5, 2, 6 };
		System.out.println(count(a6, 100)); // 8

		int[] a7 = { 1, 2, 3 };
		System.out.println(count(a7, 0)); // 0

		int[] a8 = { 1, 1, 1 };
		System.out.println(count(a8, 1)); // 0

		System.out.println(numSubarrayProductLessThanK(a6, 100)); // 8
		System.out.println(numSubarrayProductLessThanK(a7, 0)); // 0
		System.out.println(numSubarrayProductLessThanK(a8, 1)); // 0
	}
}
