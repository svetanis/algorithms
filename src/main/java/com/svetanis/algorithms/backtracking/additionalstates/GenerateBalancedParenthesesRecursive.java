package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.List;

// 22. Generate Parentheses
//
// Input: n -- the number of pairs. Returns every string of n "(" and n ")" in which every ")"
// closes an earlier "(", in any order. On LeetCode: 1 <= n <= 8.
//
// The one idea: a string is balanced when, reading left to right, ")" never outnumbers "(" and
// both end up n. One call holds the string so far and how many "(" and ")" are in it. Both next
// characters are tried unconditionally, and a state that cannot lead to an answer -- more than n
// of either bracket, or more ")" than "(" -- is rejected on arrival, by the guard at the top.
//
// Time: O(4^n / sqrt(n)) -- there are Catalan(n) answers (1430 at n = 8), each a string of 2n
// characters; the rejected calls add a constant factor: 4058 of 10975 calls at n = 8.
// Space: O(n^2) -- the recursion is 2n deep and each level holds its own string of up to 2n
// characters, besides the answers.
//
// Siblings: GenerateBalancedParenthesesGuarded moves the same two tests to the call sites, so no
// call is wasted; GenerateBalancedParenthesesBacktracking shares one buffer instead of a new
// string per call.

public final class GenerateBalancedParenthesesRecursive {

	public static List<String> parentheses(int n) {
		List<String> list = new ArrayList<>();
		dfs(n, 0, 0, "", list); // both counts start at ZERO and climb
		return list;
	}

	private static void dfs(int n, int open, int close, String s, List<String> list) {
		if (open > n || close > n || open < close) { // GUARD: a state that cannot be finished
			return; // the reject - but the call was already made
		}
		if (open == n && close == n) { // BASE: all brackets placed
			list.add(s); // RECORD
			return;
		}
		dfs(n, open + 1, close, s + "(", list); // both calls happen unconditionally
		dfs(n, open, close + 1, s + ")", list); // s + "(" is a NEW string; the caller's is untouched
	}

	public static void main(String[] args) {
		printLines(parentheses(2)); // (()) then ()()
		System.out.println();
		printLines(parentheses(3)); // ((())) (()()) (())() ()(()) ()()()
	}

	private static void printLines(List<String> list) {
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println();
	}
}