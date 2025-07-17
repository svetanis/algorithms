package com.svetanis.algorithms.backtracking.additionalstates;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

// 22. Generate Parentheses

// For a given number ‘N’, write a function to generate 
// all combination of ‘N’ pairs of balanced parentheses.

public final class GenerateBalancedParenthesesRecursive {

	public static List<String> parentheses(int n) {
		List<String> list = new ArrayList<>();
		dfs(n, 0, 0, "", list);
		return list;
	}

	private static void dfs(int n, int open, int close, String s, List<String> list) {
		if (open > n || close > n || open < close) {
			return;
		}
		if (open == n && close == n) {
			list.add(s);
			return;
		}
		dfs(n, open + 1, close, s + "(", list);
		dfs(n, open, close + 1, s + ")", list);
	}

	public static ImmutableList<String> generate(int n) {
		List<String> list = newArrayList();
		dfs2(n, n, "", list);
		return newList(list);
	}

	private static void dfs2(int open, int close, String s, List<String> list) {
		// invalid state
		if (open < 0 || close < open) {
			return;
		}
		if (open == 0 && close == 0) {
			list.add(s);
			return;
		}
		if (open > 0) {
			dfs2(open - 1, close, s + '(', list);
		}
		if (close > open) {
			dfs2(open, close - 1, s + ')', list);
		}
	}

	public static void main(String[] args) {
		printLines(generate(3));

		System.out.println(parentheses(2));
		System.out.println();
		System.out.println(parentheses(3));
		System.out.println();
		System.out.println(parentheses(1));
	}
}