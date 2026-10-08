package com.svetanis.algorithms.twopointers.pairs;

import static java.util.Arrays.sort;

import java.util.Arrays;

// Pair With the Largest Product -- sorting
//
// Input: an array of integers -- positive, negative or zero.
// Return: a pair {x, y} of values at two different positions with the largest product.
// With fewer than 2 numbers it prints a message and returns {-1, -1}.
//
// The one idea: after sorting, the largest product is either the last two numbers (the
// two largest) or the first two (the two most negative), so compare those two
// products. When even the first number is positive, the last two win outright.
//
// Sibling: PairMaxProduct -- one pass without sorting, O(n)
//
// Time: O(n log n) -- the sort.
// Space: O(log n) -- the sort. The array is sorted in place: the caller's order is lost.

public final class PairMaxProductSorted {

	public static int[] pair(int[] a) {
		int n = a.length;
		if (n < 2) {
			System.out.println("No such pair exists");
			return new int[] { -1, -1 };                 // RETURN: no pair
		}
		sort(a);                                         // SORT: the candidates sit at the two ends
		int first = a[0];
		int second = a[1];
		if (n == 2) {
			return new int[] { first, second };          // RETURN: the only pair
		}
		int last = a[n - 1];
		int secondLast = a[n - 2];
		if (first > 0) {
			return new int[] { last, secondLast };       // RETURN: no negatives, the two largest win
		} else if (first * second > last * secondLast) { // COMPARE the two ends
			return new int[] { first, second };
		} else {
			return new int[] { last, secondLast };
		}
	}

	public static void main(String[] args) {
		int[] x = { 1, 4, 3, 6, 7, 0 };
		System.out.println(Arrays.toString(pair(x))); // [7, 6]

		int[] y = { -1, -3, -4, 2, 0, -5 };
		System.out.println(Arrays.toString(pair(y))); // [-5, -4]
	}
}
