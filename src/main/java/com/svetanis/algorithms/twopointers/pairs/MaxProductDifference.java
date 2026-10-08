package com.svetanis.algorithms.twopointers.pairs;

// 1913. Maximum Product Difference Between Two Pairs
//
// Input: an array of n >= 4 integers, each from 1 to 10^4.
// Return: the largest (a * b) - (c * d) where a, b, c, d sit at four different positions.
//
// The one idea: every value is positive, so the largest product is the two largest
// values and the smallest product is the two smallest values. With n >= 4 those are
// four different positions. One pass keeps the two largest, another the two smallest.
//
// Sibling: MaxProductDifferenceSort -- sorts and reads the two numbers at each end
//
// Time: O(n) -- two passes.
// Space: O(1).

public final class MaxProductDifference {

	private static final int MIN = Integer.MIN_VALUE;
	private static final int MAX = Integer.MAX_VALUE;

	public static int maxDiff(int[] a) {
		int lp = largest(a);
		int sp = smallest(a);
		return lp - sp; // RETURN: the largest product minus the smallest
	}

	// the product of the two smallest values
	private static int smallest(int[] a) {
		int first = MAX;
		int second = MAX;
		for (int i = 0; i < a.length; ++i) {
			if (a[i] < first) {
				second = first; // MOVE the old smallest to second
				first = a[i];
			} else if (a[i] < second) {
				second = a[i];  // RECORD a new second smallest
			}
		}
		return first * second;
	}

	// the product of the two largest values
	private static int largest(int[] a) {
		int first = MIN;
		int second = MIN;
		for (int i = 0; i < a.length; ++i) {
			if (a[i] > first) {
				second = first; // MOVE the old largest to second
				first = a[i];
			} else if (a[i] > second) {
				second = a[i];  // RECORD a new second largest
			}
		}
		return first * second;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 6, 2, 7, 4 };
		System.out.println(maxDiff(a1)); // 34

		int[] a2 = { 4, 2, 5, 9, 7, 4, 8 };
		System.out.println(maxDiff(a2)); // 64
	}
}
