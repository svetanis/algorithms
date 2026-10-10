package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// 927. Three Equal Parts
//
// Input: an array of 0s and 1s, length at least 3.
// Return: {i, j} with i + 1 < j such that a[0..i], a[i+1..j-1] and a[j..n-1], each read
// as a binary number (leading zeros allowed), are equal; {-1, -1} when no such split
// exists.
//
// The one idea: equal numbers hold equal counts of 1s, so the 1s split into thirds and
// each part's number starts at the first 1 of its third. Put one pointer on each of
// those three first 1s and walk them forward together while they read the same digit.
// The third part ends at the end of the array, so its trailing zeros are fixed, and the
// walk succeeds only if the third pointer reaches the end; the other two pointers then
// sit just past their parts. An array of 0s splits anywhere, so {0, n - 1} is returned.
//
// Time: O(n) -- one pass to count the 1s, three to find the starts, one walk.
// Space: O(1).

public final class ThreeEqualParts {

	public static int[] threeEqualParts(int[] a) {
		int n = a.length;
		int ones = Arrays.stream(a).sum();     // COUNT the 1s
		if (ones == 0) {
			return new int[] { 0, n - 1 };     // RETURN: all zeros, any split works
		}
		if (ones % 3 != 0) {
			return new int[] { -1, -1 };       // RETURN: the 1s cannot split into thirds
		}
		int count = ones / 3;
		int i = find(a, 1);                    // START: the first 1 of the first part
		int j = find(a, count + 1);            // START: the first 1 of the second part
		int k = find(a, 2 * count + 1);        // START: the first 1 of the third part
		while (k < n && a[i] == a[j] && a[j] == a[k]) {
			i++;                               // COMPARE: all three parts read the same digit
			j++;
			k++;
		}
		return k == n ? new int[] { i - 1, j } : new int[] { -1, -1 }; // RETURN: k reached the end, or the parts differ
	}

	// the index of the 1 that brings the running count of 1s to the given number
	private static int find(int[] a, int one) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum == one) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 0, 1, 0, 1 };
		System.out.println(Arrays.toString(threeEqualParts(a1))); // [0, 3]

		int[] a2 = { 1, 1, 0, 1, 1 };
		System.out.println(Arrays.toString(threeEqualParts(a2))); // [-1, -1]

		int[] a3 = { 1, 1, 0, 0, 1 };
		System.out.println(Arrays.toString(threeEqualParts(a3))); // [0, 2]
	}
}
