package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;

// 1913. Maximum Product Difference Between Two Pairs
//
// Input: an array of n >= 4 integers, each from 1 to 10^4.
// Return: the largest (a * b) - (c * d) where a, b, c, d sit at four different positions.
//
// The one idea: every value is positive, so the largest product is the two largest
// values and the smallest product is the two smallest. After sorting they are the last
// two and the first two numbers -- four different positions, since n >= 4.
//
// Sibling: MaxProductDifference -- one pass keeping the two largest and two smallest, O(n)
//
// Time: O(n log n) -- the sort.
// Space: O(log n) -- the sort. The array is sorted in place: the caller's order is lost.

public final class MaxProductDifferenceSort {

	public static int maxDiff(int[] a) {
		Arrays.sort(a);                // SORT: the smallest two first, the largest two last
		int n = a.length;
		int max = a[n - 1] * a[n - 2]; // the largest product
		int min = a[0] * a[1];         // the smallest product
		return max - min;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 6, 2, 7, 4 };
		System.out.println(maxDiff(a1)); // 34

		int[] a2 = { 4, 2, 5, 9, 7, 4, 8 };
		System.out.println(maxDiff(a2)); // 64
	}
}
