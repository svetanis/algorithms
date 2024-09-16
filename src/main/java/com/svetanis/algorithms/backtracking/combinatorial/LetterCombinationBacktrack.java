package com.svetanis.algorithms.backtracking.combinatorial;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.util.Arrays.asList;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

// given a positive integer number n 
// find all n-letter words composed 
// by 'a' and 'b' and return them in
// a list of string in lexicographical order

public final class LetterCombinationBacktrack {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	private static final ImmutableList<String> DICT = newList(asList("a", "b"));

	public static ImmutableList<String> letterCombination(int n) {
		List<String> list = newArrayList();
		List<String> lists = newArrayList();
		dfs(n, list, lists);
		return newList(lists);
	}

	// index is redundant and derived from list.size()
	private static void dfs(int n, List<String> list, List<String> lists) {
		if (list.size() == n) {
			lists.add(Joiner.on("").join(list));
			return;
		}

		for (String letter : DICT) {
			list.add(letter);
			dfs(n, list, lists);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {
		printLines(letterCombination(2)); // aa, ab, ba, bb
		System.out.println();
		printLines(letterCombination(3));
		System.out.println();
		printLines(letterCombination(4));
	}
}
