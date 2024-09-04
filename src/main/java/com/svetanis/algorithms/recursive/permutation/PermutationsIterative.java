package com.svetanis.algorithms.recursive.permutation;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// given a set of distinct numbers
// find all of its permutations

// Permutation is defined as the re-arranging 
// of the elements of the set

// if a set has 'n' distinct elements
// it will have n! permutations

public final class PermutationsIterative {
	// Time Complexity:  O(n * n!)
	// Space Complexity: O(n * n!)

	public static ImmutableList<ImmutableList<Integer>> permute(int[] a) {
		List<ImmutableList<Integer>> lists = newArrayList();
		Queue<List<Integer>> queue = newLinkedList();
		queue.add(newList());
		for (int num : a) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				List<Integer> prev = queue.poll();
				// create a new permutation by adding
				// the current number at every position
				for (int j = 0; j <= prev.size(); j++) {
					List<Integer> list = newArrayList();
					list.addAll(prev);
					list.add(j, num);
					if (list.size() == a.length) {
						lists.add(newList(list));
					} else {
						queue.add(list);
					}
				}
			}
		}
		return newList(lists);
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, 5 };
		printLines(permute(a));
	}
}
