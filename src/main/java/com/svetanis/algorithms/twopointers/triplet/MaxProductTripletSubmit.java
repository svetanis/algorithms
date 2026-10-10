package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;

// 628. Maximum Product of Three Numbers
//
// Input: an array of at least three integers.
// Return: the largest product of three numbers at three different positions.
//
// The one idea: the best three are either the three LARGEST numbers, or the two SMALLEST
// with the largest -- two negatives make a positive. Compare those two products.
//
// Two methods:
//   maxProduct  -- one pass keeping the top three and the bottom two. O(n) time.
//   maxProduct2 -- sort, then read the candidates off both ends. O(n log n) time.
//
// Sibling: twopointers.triplet.MaxProductTriplet -- the one-pass version with > / <, and -1
//   for fewer than three numbers.
//
// Time: O(n) for maxProduct, O(n log n) for maxProduct2.
// Space: O(1) for both; maxProduct2 sorts in place.

public final class MaxProductTripletSubmit {

	public static int maxProduct(int[] a) {
		final int infinity = Integer.MAX_VALUE;
		int min1 = infinity;                             // the two smallest, smallest first
		int min2 = infinity;
		int max1 = -infinity;                            // the three largest, largest first
		int max2 = -infinity;
		int max3 = -infinity;
		for (int num : a) {
			if (num <= min1) {                           // RECORD a new smallest
				min2 = min1;
				min1 = num;
			} else if (num <= min2) {
				min2 = num;
			}
			if (num >= max1) {                           // RECORD a new largest
				max3 = max2;
				max2 = max1;
				max1 = num;
			} else if (num >= max2) {
				max3 = max2;
				max2 = num;
			} else if (num >= max3) {
				max3 = num;
			}
		}
		int prod1 = min1 * min2 * max1;                  // COMPARE two smallest and the largest
		int prod2 = max1 * max2 * max3;                  // with the three largest
		return Math.max(prod1, prod2);
	}

	public static int maxProduct2(int[] a) {
		Arrays.sort(a);                                  // SORT: the candidates sit at the ends
		int n = a.length;
		int prod1 = a[n - 1] * a[n - 2] * a[n - 3];      // COMPARE the three largest
		int prod2 = a[0] * a[1] * a[n - 1];              // with the two smallest and the largest
		return Math.max(prod1, prod2);
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		System.out.println(maxProduct(a1)); // 6
		int[] a2 = { 1, 2, 3, 4 };
		System.out.println(maxProduct(a2)); // 24
		int[] a3 = { -1, -2, -3 };
		System.out.println(maxProduct(a3)); // -6
		int[] a4 = { -100, -98, -1, 2, 3, 4};
		System.out.println(maxProduct(a4)); // 39200
		System.out.println(maxProduct2(a4)); // 39200
	}
}