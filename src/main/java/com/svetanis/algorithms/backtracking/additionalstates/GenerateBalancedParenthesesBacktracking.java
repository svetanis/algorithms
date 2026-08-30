package com.svetanis.algorithms.backtracking.additionalstates;

import static com.svetanis.java.base.utils.Print.printLines;

import java.util.ArrayList;
import java.util.List;

// 22. Generate Parentheses

// For a given number ‘N’, write a function to generate
// all combination of ‘N’ pairs of balanced parentheses.

// One StringBuilder is shared by every call and each append is
// undone on the way out, so no intermediate string is ever
// created - only the answers themselves.

// Time Complexity: exponential - only valid prefixes are built,
// but the number of balanced strings is itself exponential in n
// (1430 at n = 8) and every one of them is produced
// Space Complexity: O(n) - one buffer of length 2n, plus a call
// stack at most 2n deep

public final class GenerateBalancedParenthesesBacktracking {

	public static List<String> generate(int n) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<>();
		dfs(n, 0, 0, sb, list);
		return list;
	}

	private static void dfs(int n, int open, int close, 
			StringBuilder sb, List<String> list) {
		if (sb.length() == 2 * n) { // the two guards below make an unbalanced string unbuildable
			list.add(sb.toString());
			return;
		}
		if (open < n) {
			sb.append('('); // choose
			dfs(n, open + 1, close, sb, list); // explore - open + 1 is a value, undoes itself
			sb.deleteCharAt(sb.length() - 1); // UNCHOOSE - the buffer is shared, so by hand
		}
		if (close < open) {
			sb.append(')');
			dfs(n, open, close + 1, sb, list);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public static void main(String[] args) {
		printLines(generate(2));
		System.out.println();
		printLines(generate(3));
	}
}