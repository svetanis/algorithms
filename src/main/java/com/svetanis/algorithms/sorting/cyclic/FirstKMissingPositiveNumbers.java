package com.svetanis.algorithms.sorting.cyclic;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Swap.swap;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;

// given an unsorted array containing numbers and k
// find the first k missing positive numbers in the array

public final class FirstKMissingPositiveNumbers {

	public static ImmutableList<Integer> smpn(int[] a, int k) {
		// Time Complexity: O(n + k)
		// Space Complexity: O(k)

		sort(a);
		Set<Integer> set = newHashSet();
		List<Integer> list = newArrayList();
		for (int i = 0; i < a.length && list.size() < k; i++) {
			if (a[i] != i + 1) {
				set.add(a[i]);
				list.add(i + 1);
			}
		}
		// add remaining missing numbers
		for (int i = 1; list.size() < k; i++) {
			int candidate = i + a.length;
			// ignore if array contains the candidate
			if (!set.contains(candidate)) {
				list.add(candidate);
			}
		}
		return newList(list);
	}

	public static void sort(int[] a) {
		// Time Complexity: O(n)

		int n = a.length;
		int i = 0;
		while (i < n) {
			int j = a[i] - 1;
			if (a[i] > 0 && a[i] <= n && a[i] != a[j]) {
				swap(a, i, j);
			} else {
				i++;
			}
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 3, -1, 4, 5, 5 };
		System.out.println(smpn(a1, 3)); // 1, 2, 6
		int[] a2 = { 2, 3, 4 };
		System.out.println(smpn(a2, 3)); // 1, 5, 6
		int[] a3 = { -2, -3, 4 };
		System.out.println(smpn(a3, 2)); // 1, 2
		int[] a4 = { 2, 1, 3, 6, 5 };
		System.out.println(smpn(a4, 2));

	}
}
