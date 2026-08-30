package com.svetanis.algorithms.backtracking.additionalstates;

import static com.svetanis.java.base.utils.Print.printLines;

import java.util.ArrayList;
import java.util.List;

// 22. Generate Parentheses

// For a given number ‘N’, write a function to generate
// all combination of ‘N’ pairs of balanced parentheses.

// Both calls are made unconditionally and an impossible state is
// rejected on arrival, by the guard at the top. Compare
// GenerateBalancedParenthesesGuarded, which moves the same two
// tests to the call sites - about one call in three here does
// nothing but return.

// Time Complexity: exponential - only valid prefixes are built,
// but the number of balanced strings is itself exponential in n
// (1430 at n = 8) and every one of them is produced
// Space Complexity: O(n) - the call stack only, at most 2n deep

public final class GenerateBalancedParenthesesRecursive {

	public static List<String> parentheses(int n) {
		List<String> list = new ArrayList<>();
		dfs(n, 0, 0, "", list); // both counts start at ZERO and climb
		return list;
	}

	private static void dfs(int n, int open, int close, String s, List<String> list) {
		if (open > n || close > n || open < close) {
			return; // the reject - but the call was already made
		}
		if (open == n && close == n) {
			list.add(s);
			return;
		}
		dfs(n, open + 1, close, s + "(", list); // both calls happen unconditionally
		dfs(n, open, close + 1, s + ")", list); // s + "(" is a NEW string; the caller's is untouched
	}

	public static void main(String[] args) {
		printLines(parentheses(2));
		System.out.println();
		printLines(parentheses(3));
	}
}