package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Math.abs;

import java.util.Arrays;

// Pair With the Largest Product
//
// Input: an array of integers -- positive, negative or zero.
// Return: a pair {x, y} of values at two different positions with the largest product.
// With fewer than 2 numbers it prints a message and returns {-1, -1}.
//
// The one idea: the largest product is either the two largest numbers or the two most
// negative numbers, since two negatives multiply to a positive. One pass keeps both
// pairs, then the larger product wins. The four trackers start at 0 rather than at
// real numbers. For n >= 3 that is safe: a 0 still left in a tracker can only end up in
// the answer when the best product is 0, and then the array really holds a 0 there.
//
// Siblings:
//   PairMaxProductSorted -- sorts and compares the two ends, O(n log n)
//   PairMinProduct -- the smallest product, positive numbers only
//   PairLargestSum -- the largest SUM, where only the two largest matter
//
// Time: O(n) -- one pass.
// Space: O(1).

public final class PairMaxProduct {

	public static int[] pair(int[] a) {
		int n = a.length;
		if (n < 2) {
			System.out.println("No such pair exists");
			return new int[] { -1, -1 };                   // RETURN: no pair
		}
		if (n == 2) {
			return new int[] { a[0], a[1] };               // RETURN: the only pair
		}
		int firstMax = 0;                                  // START at 0, see the header
		int secondMax = 0;
		int firstMin = 0;
		int secondMin = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] > firstMax) {
				secondMax = firstMax;                      // MOVE the old largest down to second
				firstMax = a[i];
			} else if (a[i] > secondMax) {
				secondMax = a[i];                          // RECORD a new second largest
			}
			if (a[i] < 0 && abs(a[i]) > abs(firstMin)) {
				secondMin = firstMin;                      // MOVE the old most negative down to second
				firstMin = a[i];
			} else if (a[i] < 0 && abs(a[i]) > abs(secondMin)) {
				secondMin = a[i];                          // RECORD a new second most negative
			}
		}
		if (firstMax * secondMax > firstMin * secondMin) { // COMPARE the two candidate products
			return new int[] { firstMax, secondMax };
		} else {
			return new int[] { firstMin, secondMin };
		}
	}

	public static void main(String[] args) {
		int[] x = { 1, 4, 3, 6, 7, 0 };
		System.out.println(Arrays.toString(pair(x))); // [7, 6]

		int[] y = { -1, -3, -4, 2, 0, -5 };
		System.out.println(Arrays.toString(pair(y))); // [-5, -4]
	}
}
