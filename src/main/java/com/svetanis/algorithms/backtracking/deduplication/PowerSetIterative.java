package com.svetanis.algorithms.backtracking.deduplication;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Math.pow;
import static java.util.Arrays.sort;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;

// given a set, generate all distinct subsets
// i.e. find distinct power set of a set

// to exclude duplicates, sort the set
// and insert each subset into Java Set

// Power set of any set S is the set of all
// subsets of S, including the empty set 
// and S itself

// Power set can be found by generating all
// binary numbers between 0 and 2^n - 1
// where n is the size of the given set

// 0 = 000 = {}
// 1 = 001 = {z}
// 2 = 010 = {y}
// 3 = 011 = {y, z}
// 4 = 100 = {x}
// 5 = 101 = {x, z}
// 6 = 110 = {x, y}
// 7 = 111 = {x, y, z}

public final class PowerSetIterative {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static ImmutableList<ImmutableList<Integer>> subsets(int[] a) {
		sort(a);
		// n is total number of subsets
		int n = new Double(pow(2, a.length)).intValue();
		Set<ImmutableList<Integer>> sets = newHashSet();
		for (int i = 0; i < n; i++) {
			List<Integer> list = newArrayList();
			// check every bit of i
			for (int j = 0; j < a.length; j++) {
				// if j'th bit of i is set
				// append a[j] to subset
				if ((i & (1 << j)) != 0) {
					list.add(a[j]);
				}
			}
			sets.add(newList(list));
		}
		return newList(sets);
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 1 };
		System.out.println(subsets(a));

		int[] a1 = { 1, 3, 3, 5 };
		System.out.println(subsets(a1));
	}
}
