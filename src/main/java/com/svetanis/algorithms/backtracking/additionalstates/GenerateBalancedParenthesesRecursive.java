package com.svetanis.algorithms.backtracking.additionalstates;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ImmutableList;

// For a given number ‘N’, write a function to generate 
// all combination of ‘N’ pairs of balanced parentheses.

public final class GenerateBalancedParenthesesRecursive {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static ImmutableList<String> generate(int n) {
		List<String> list = newArrayList();
		add("", n, n, list);
		return newList(list);
	}

	private static void add(String s, int left, int right, List<String> list) {

		// invalid state
		if (left < 0 || right < left) {
			return;
		}

		if (left == 0 && right == 0) {
			list.add(s);
			return;
		}

		if (left > 0) {
			add(s + '(', left - 1, right, list);
		}

		if (right > left) {
			add(s + ')', left, right - 1, list);
		}
	}

	public static void main(String[] args) {
		printLines(generate(3));
	}
}