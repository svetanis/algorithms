package com.svetanis.algorithms.recursive.combination;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given a set of numbers that might
// contain duplicates, find all of 
// its distinct subsets

public final class SubsetWithDuplicates {
	// Time Complexity:  O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static ImmutableList<ImmutableList<Integer>> subsets(int[] a) {
		List<ImmutableList<Integer>> lists = newArrayList();
		lists.add(newList()); // add empty subset
		int start = 0;
		int end = 0;
		for (int i = 0; i < a.length; i++) {
			start = 0;
			// if current and previous elements are same
			// create new subsets only from the subsets
			// added in the previous step
			if (i > 0 && a[i] == a[i - 1]) {
				start = end + 1;
			}
			end = lists.size() - 1;
			for (int j = start; j <= end; j++) {
				// create a new subset from the
				// existing subset and add the
				// current element to it
				List<Integer> list = newArrayList();
				list.addAll(lists.get(j));
				list.add(a[i]);
				lists.add(newList(list));
			}
		}
		return newList(lists);
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 3, 5 };
		System.out.println(subsets(a));
	}
}
