package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.List;

// 22. Generate Parentheses
//
// Input: n -- the number of pairs. Returns every string of n "(" and n ")" in which every ")"
// closes an earlier "(", in any order. On LeetCode: 1 <= n <= 8.
//
// The one idea: a string is balanced when, reading left to right, ")" never outnumbers "(" and
// both end up n. So build it from the left, and at each step add "(" while fewer than n are
// used, and ")" while fewer ")" than "(" are used; those two tests make an unbalanced string
// impossible to build, and every prefix built can be finished to an answer. The state is
// open and close -- how many "(" and ")" are already in the buffer. One StringBuilder is shared
// by every call and each append is undone on the way out, so no intermediate string is ever
// created -- only the answers themselves.
//
// Time: O(4^n / sqrt(n)) -- there are Catalan(n) answers (1430 at n = 8), each a string of 2n
// characters copied once; the search tree has 6917 nodes at n = 8 and every one of them lies on
// the way to an answer.
// Space: O(n) -- one buffer of length 2n, plus a call stack at most 2n deep, besides the answers.
//
// Siblings: GenerateBalancedParenthesesGuarded and GenerateBalancedParenthesesRecursive build a
// new string per call instead of undoing a shared one; GenerateBalancedParenthesesIterative uses
// a queue; GenerateBalancedParenthesesBruteForce builds every string and tests it last.

public final class GenerateBalancedParenthesesBacktracking {

	public static List<String> generate(int n) {
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<>();
		dfs(n, 0, 0, sb, list);
		return list;
	}

	private static void dfs(int n, int open, int close,
			StringBuilder sb, List<String> list) {
		if (sb.length() == 2 * n) { // BASE: the two guards below make an unbalanced string unbuildable
			list.add(sb.toString()); // RECORD: a finished string is an answer
			return;
		}
		if (open < n) { // GUARD: a "(" is allowed while fewer than n are used
			sb.append('('); // choose
			dfs(n, open + 1, close, sb, list); // explore - open + 1 is a value, undoes itself
			sb.deleteCharAt(sb.length() - 1); // UNCHOOSE - the buffer is shared, so by hand
		}
		if (close < open) { // GUARD: a ")" is allowed while it closes an unclosed "("
			sb.append(')');
			dfs(n, open, close + 1, sb, list);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public static void main(String[] args) {
		printLines(generate(2)); // (()) then ()()
		System.out.println();
		printLines(generate(3)); // ((())) (()()) (())() ()(()) ()()()
	}

	private static void printLines(List<String> list) {
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println();
	}
}