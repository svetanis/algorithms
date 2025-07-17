package com.svetanis.algorithms.backtracking.additionalstates;

import static com.svetanis.java.base.utils.Print.printLines;

import java.util.ArrayList;
import java.util.List;

// 22. Generate Parentheses

// For a given number ‘N’, write a function to generate 
// all combination of ‘N’ pairs of balanced parentheses.

public final class GenerateBalancedParenthesesBacktracking {

	public static List<String> generate(int n) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<>();
		dfs(n, 0, 0, sb, list);
		return list;
	}

	private static void dfs(int n, int open, int close, 
			StringBuilder sb, List<String> list) {
		if (sb.length() == 2 * n) {
			list.add(sb.toString());
			return;
		}
		if (open < n) {
			sb.append('(');
			dfs(n, open + 1, close, sb, list);
			sb.deleteCharAt(sb.length() - 1);
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