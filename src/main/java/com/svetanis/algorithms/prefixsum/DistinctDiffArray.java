package com.svetanis.algorithms.prefixsum;

import java.util.HashSet;
import java.util.Set;

// 2670. Find the Distinct Difference Array
//
// Input: an array a of length n. Returns an array diff where diff[i] is the number of distinct
// values in the prefix a[0 .. i] minus the number of distinct values in the suffix
// a[i + 1 .. n - 1] (an empty suffix has 0). On LeetCode: 1 <= n <= 50, 1 <= a[i] <= 50.
//
// The one idea: count the distinct values on each side once, not once per index. suffix[i] holds
// how many distinct values a[i .. n - 1] has, filled right to left by adding a[i] to a set and
// reading its size; suffix[n] = 0 is the empty suffix. Then one left-to-right pass adds a[i] to a
// second set, whose size is the distinct count of a[0 .. i], and subtracts suffix[i + 1], the part
// strictly to the right of i.
//
// Time: O(n) -- two passes, one set insertion per number in each, O(1) on average
// Space: O(n) -- the n + 1 suffix cells, the two sets and the output

public final class DistinctDiffArray {

	public static int[] dda(int[] a) {
		int n = a.length;
		int[] suffix = suffix(a);
		Set<Integer> set = new HashSet<>(); // SEEN: the values of a[0 .. i]
		int[] diff = new int[n];
		for (int i = 0; i < n; i++) {
			set.add(a[i]);
			diff[i] = set.size() - suffix[i + 1]; // DIFF: prefix count minus the suffix after i
		}
		return diff;
	}

	private static int[] suffix(int[] a) {
		int n = a.length;
		Set<Integer> set = new HashSet<>(); // SEEN: the values of a[i .. n - 1]
		int[] suff = new int[n + 1]; // CELLS: distinct in a[i .. n - 1]; suff[n] = 0
		for (int i = n - 1; i >= 0; i--) {
			set.add(a[i]);
			suff[i] = set.size(); // SUFFIX: right to left
		}
		return suff;
	}

	private static void print(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int value : a) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4, 5 };
		print(dda(a1)); // -3 -1 1 3 5

		int[] a2 = { 3, 2, 3, 4, 2 };
		print(dda(a2)); // -2 -1 0 2 3
	}
}
