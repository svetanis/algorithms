package com.svetanis.algorithms.recursive.combination;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given a set of distinct elements,
// find all of its distinct subsets

public final class SubsetNoDuplicates {
	// Time Complexity:  O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static ImmutableList<ImmutableList<Integer>> subsets(int[] a) {
		List<ImmutableList<Integer>> lists = newArrayList();
		lists.add(newList()); // add empty subset
		for (int num : a) {
			// take all existing subsets
			// insert current element in
			// them to create new subsets
			int size = lists.size();
			for (int i = 0; i < size; i++) {
				List<Integer> list = newArrayList();
				list.addAll(lists.get(i));
				list.add(num);
				lists.add(newList(list));
			}
		}
		return newList(lists);
	}

	public static void main(String[] args) {
		int[] a = { 1, 3 };
		System.out.println(subsets(a));

		int[] a2 = { 1, 5, 3 };
		System.out.println(subsets(a2));
	}
}
