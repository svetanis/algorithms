package com.svetanis.algorithms.slidingwindow.hashmap;

import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Math.max;

import java.util.Set;

// given two arrays a and b of same size.
// find the max sum possible of a window in b[]
// such that elements of same window in a[] are unique

public final class MaxPossibleSumInWindow {

	public static int maxSum(int[] a, int[] b) {
		// Time complexity: O(n)

		int n = a.length;
		int sum = 0;
		int max = 0;
		int left = 0;

		Set<Integer> set = newHashSet();
		for (int right = 0; right < n; right++) {
			while (set.contains(a[right])) {
				set.remove(a[left]);
				sum -= b[left];
				left++;
			}
			set.add(a[right]);
			sum += b[right];
			max = max(max, sum);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 0, 1, 2, 3, 0, 1, 4 };
		int[] b = { 9, 8, 1, 2, 3, 4, 5 };
		System.out.println(maxSum(a, b));

		int[] a1 = { 0, 1, 2, 0, 2 };
		int[] b1 = { 5, 6, 7, 8, 2 };
		System.out.println(maxSum(a1, b1));

	}
}
