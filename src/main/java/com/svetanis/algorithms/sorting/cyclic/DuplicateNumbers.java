package com.svetanis.algorithms.sorting.cyclic;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Swap.swap;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given an unsorted array containing n + 1 numbers
// taken from the range 1 to n. the array has only
// one duplicate but it can be repeated multiple times
// find that duplicate number without using any extra 
// space. allowed to modify the input array 

public final class DuplicateNumbers {

	public static ImmutableList<Integer> duplicate(int[] a) {
		// Time Complexity: O(n)

		sort(a);
		List<Integer> list = newArrayList();
		for (int i = 0; i < a.length; i++) {
			if (a[i] != i + 1) {
				list.add(a[i]);
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
		int[] a1 = { 3, 4, 4, 5, 5 };
		System.out.println(duplicate(a1));
		int[] a2 = { 5, 4, 7, 2, 3, 5, 3 };
		System.out.println(duplicate(a2));
	}
}
