package com.svetanis.algorithms.prefixsum;

import java.util.HashSet;
import java.util.Set;

import com.svetanis.java.base.utils.Print;

// 2670. Find the Distinct Difference Array

public final class DistinctDiffArray {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] dda(int[] a) {
		int n = a.length;
		int[] suffix = suffix(a);
		Set<Integer> set = new HashSet<>();
		int[] diff = new int[n];
		for (int i = 0; i < n; i++) {
			set.add(a[i]);
			diff[i] = set.size() - suffix[i + 1];
		}
		return diff;
	}

	private static int[] suffix(int[] a) {
		int n = a.length;
		Set<Integer> set = new HashSet<>();
		int[] suff = new int[n + 1];
		for (int i = n - 1; i >= 0; i--) {
			set.add(a[i]);
			suff[i] = set.size();
		}
		return suff;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4, 5 };
		Print.print(dda(a1));

		int[] a2 = { 3, 2, 3, 4, 2 };
		Print.print(dda(a2));
	}
}
