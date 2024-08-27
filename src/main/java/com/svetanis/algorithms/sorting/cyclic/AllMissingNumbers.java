package com.svetanis.algorithms.sorting.cyclic;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Swap.swap;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given an unsorted array containing numbers
// taken from the range 1 to n. the array can
// have duplicates, which means some numbers
// will be missing. find all missing numbers

public final class AllMissingNumbers {

	public static ImmutableList<Integer> missing(int[] a) {
		// Time Complexity: O(n)

		sort(a);
		List<Integer> list = newArrayList();
		for (int i = 0; i < a.length; i++) {
			if (a[i] != i + 1) {
				list.add(i + 1);
			}
		}
		return newList(list);
	}
	
	public static void sort(int[] a) {
		// Time Complexity: O(n)

		int i = 0;
		while (i < a.length) {
			int j = a[i] - 1;
			if (a[i] != a[j]) {
				swap(a, i, j);
			} else {
				i++;
			}
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 1, 8, 2, 3, 5, 1 };
		System.out.println(missing(a1));
		int[] a2 = { 2, 4, 1, 2 };
		System.out.println(missing(a2));
		int[] a3 = { 2, 3, 2, 1 };
		System.out.println(missing(a3));
	}
}
