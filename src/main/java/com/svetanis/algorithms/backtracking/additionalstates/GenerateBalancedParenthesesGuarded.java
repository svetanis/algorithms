package com.svetanis.algorithms.backtracking.additionalstates;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ImmutableList;

// 22. Generate Parentheses

// For a given number ‘N’, write a function to generate
// all combination of ‘N’ pairs of balanced parentheses.

// The two tests sit at the call sites, so a call is never made
// into a state that cannot lead to an answer. Compare
// GenerateBalancedParenthesesRecursive, which makes both calls
// unconditionally and rejects the bad ones on arrival - about
// one call in three there does nothing but return.

// open and close count what is still AVAILABLE rather than what
// has been placed, so the search starts at (n, n) and an answer
// is complete when both reach zero.

// Time Complexity: exponential - only valid prefixes are built,
// but the number of balanced strings is itself exponential in n
// (1430 at n = 8) and every one of them is produced
// Space Complexity: O(n) - the call stack only, at most 2n deep

public final class GenerateBalancedParenthesesGuarded {

	public static ImmutableList<String> generate(int n) {
		List<String> list = newArrayList();
		dfs(n, n, "", list); // both counts start at N and fall to zero
		return newList(list);
	}

	private static void dfs(int open, int close, String s, List<String> list) {
		if (open == 0 && close == 0) {
			list.add(s);
			return;
		}
		if (open > 0) { // the same tests as the Recursive version, moved to the call site
			dfs(open - 1, close, s + '(', list);
		}
		if (close > open) { // so the impossible call is never made at all
			dfs(open, close - 1, s + ')', list);
		}
	}

	public static void main(String[] args) {
		printLines(generate(2));
		System.out.println();
		printLines(generate(3));
	}
}
