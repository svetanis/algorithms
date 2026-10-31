package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.List;

// 22. Generate Parentheses
//
// Input: n -- the number of pairs. Returns every string of n "(" and n ")" in which every ")"
// closes an earlier "(", in any order. On LeetCode: 1 <= n <= 8.
//
// The one idea: a string is balanced when, reading left to right, ")" never outnumbers "(" and
// both end up n. So build it from the left, adding "(" or ")" only when the result can still be
// finished. The two tests sit at the call sites, so a call is never made into a state that cannot
// lead to an answer. Here open and close count what is still AVAILABLE rather than what has been
// placed, so the search starts at (n, n) and an answer is complete when both reach zero. A "(" is
// allowed while open > 0; a ")" only while more ")" than "(" are still to place, close > open --
// that is, while some placed "(" is not yet closed.
//
// Time: O(4^n / sqrt(n)) -- there are Catalan(n) answers (1430 at n = 8), each a string of 2n
// characters; the search tree has 6917 calls at n = 8 and every one of them lies on the way to
// an answer.
// Space: O(n^2) -- the recursion is 2n deep and each level holds its own string of up to 2n
// characters, besides the answers.
//
// Siblings: GenerateBalancedParenthesesRecursive makes both calls unconditionally and rejects the
// bad ones on arrival -- 4058 of its 10975 calls at n = 8 do nothing but return;
// GenerateBalancedParenthesesBacktracking shares one buffer instead of a new string per call.

public final class GenerateBalancedParenthesesGuarded {

	public static List<String> generate(int n) {
		List<String> list = new ArrayList<>();
		dfs(n, n, "", list); // both counts start at N and fall to zero
		return list;
	}

	private static void dfs(int open, int close, String s, List<String> list) {
		if (open == 0 && close == 0) { // BASE: all brackets placed
			list.add(s); // RECORD: guarded all the way, so it is balanced
			return;
		}
		if (open > 0) { // the same tests as the Recursive version, moved to the call site
			dfs(open - 1, close, s + '(', list); // OPEN: place a "("
		}
		if (close > open) { // so the impossible call is never made at all
			dfs(open, close - 1, s + ')', list); // CLOSE: place a ")" that closes a "("
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
