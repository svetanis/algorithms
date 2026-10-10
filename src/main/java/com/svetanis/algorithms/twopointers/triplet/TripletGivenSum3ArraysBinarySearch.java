package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;
import java.util.Optional;

// Triplet with a given sum from three arrays, by binary search
//
// Input: three arrays of integers and a target k.
// Return: {x, y, z} with x from a1, y from a2, z from a3 and x + y + z == k -- the first
// found -- or empty when there is none.
//
// The one idea: two numbers fix the third. For every pair (x, y), z = k - x - y is forced,
// and a binary search of the sorted a3 says whether it is there. Arrays.binarySearch returns
// a NEGATIVE number when the value is absent, -(insertion point) - 1, so the test is >= 0;
// -1 is only the "absent, and would go first" case.
//
// Sibling: twopointers.triplet.TripletGivenSum3ArraysHashing -- a HashSet of a1 instead of
//   a sorted a3: an O(1) lookup per pair, O(n1) extra space.
//
// Time: O(n3 log n3 + n1 * n2 * log n3) -- the sort, then one search per pair.
// Space: O(1) besides the sort of a3, which works in place.

public final class TripletGivenSum3ArraysBinarySearch {

	public static Optional<int[]> triplet(int[] a1, int[] a2, int[] a3, int k) {
		Arrays.sort(a3);                                 // SORT a3 for the binary search
		int n1 = a1.length;
		int n2 = a2.length;

		for (int i = 0; i < n1; i++) {                   // FIX x from a1
			for (int j = 0; j < n2; j++) {               // FIX y from a2
				int sum = a1[i] + a2[j];
				int diff = k - sum;                      // the third number is forced
				if (Arrays.binarySearch(a3, diff) >= 0) {
					return Optional.of(new int[] { a1[i], a2[j], diff }); // FOUND: >= 0 means present
				}
			}
		}
		return Optional.empty();                         // RETURN empty: no triplet
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4, 5 };
		int[] a2 = { 2, 3, 6, 1, 2 };
		int[] a3 = { 3, 2, 4, 5, 6 };

		System.out.println(triplet(a1, a2, a3, 9).map(Arrays::toString).orElse("none")); // [1, 2, 6]
		System.out.println(triplet(a1, a2, a3, 100).map(Arrays::toString).orElse("none")); // none
	}
}