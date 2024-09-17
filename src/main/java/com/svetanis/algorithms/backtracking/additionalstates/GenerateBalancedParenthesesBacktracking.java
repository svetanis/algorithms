package com.svetanis.algorithms.backtracking.additionalstates;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

// For a given number ‘N’, write a function to generate 
// all combination of ‘N’ pairs of balanced parentheses.

public final class GenerateBalancedParenthesesBacktracking {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static ImmutableList<String> generate(int n) {
		List<String> list = newArrayList();
		List<Character> path = newArrayList();
		dfs(n, 0, 0, path, list);
		return newList(list);
	}

	private static void dfs(int n, int open, int close, List<Character> path, 
			List<String> list) {
		if (path.size() == 2 * n) {
			list.add(Joiner.on("").join(path));
			return;
		}

		if (open < n) {
			path.add('(');
			dfs(n, open + 1, close, path, list);
			path.remove(path.size() - 1);
		}
		if (close < open) {
			path.add(')');
			dfs(n, open, close + 1, path, list);
			path.remove(path.size() - 1);
		}
	}

	public static void main(String[] args) {
		printLines(generate(2));
		System.out.println();
		printLines(generate(3));
	}
}