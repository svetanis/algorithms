package com.svetanis.algorithms.recursive.permutation;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given a set of distinct numbers
// find all of its permutations

// Permutation is defined as the re-arranging 
// of the elements of the set

// if a set has 'n' distinct elements
// it will have n! permutations

public final class PermutationsRecursive {
	// Time Complexity: O(n * n!)
	// Space Complexity: O(n * n!)

	public static ImmutableList<ImmutableList<Integer>> permute(int[] a) {
		List<Integer> list = newArrayList();
		List<ImmutableList<Integer>> lists = newArrayList();
		permute(a, 0, list, lists);
		return newList(lists);
	}

	public static void permute(int[] a, int index, List<Integer> curr, List<ImmutableList<Integer>> lists) {
		if (index == a.length) {
			lists.add(newList(curr));
		} else {
			// create a new permutations by adding
			// number at every position
			for (int i = 0; i <= curr.size(); i++) {
				List<Integer> next = newArrayList();
				next.addAll(curr);
				next.add(i, a[index]);
				permute(a, index + 1, next, lists);
			}
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 5 };
		printLines(permute(a));
	}
}
