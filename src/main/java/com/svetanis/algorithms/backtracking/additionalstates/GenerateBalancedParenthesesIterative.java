package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 22. Generate Parentheses
//
// Input: n -- the number of pairs. Returns every string of n "(" and n ")" in which every ")"
// closes an earlier "(", in any order. On LeetCode: 1 <= n <= 8.
//
// The one idea: a string is balanced when, reading left to right, ")" never outnumbers "(" and
// both end up n. One queue entry is a half-built string with the count of "(" and of ")" in it.
// Taking an entry out adds "(" while fewer than n are used, and ")" while fewer ")" than "(" are
// used, and puts the new entries back; an entry with n of each is an answer. It is the same search
// as GenerateBalancedParenthesesBacktracking with the pending work moved off the call stack and
// into a queue, so a whole level of the search is alive at the same time rather than a single path.
//
// Time: O(4^n / sqrt(n)) -- there are Catalan(n) answers (1430 at n = 8), each a string of 2n
// characters; every entry lies on the way to an answer.
// Space: O(4^n / sqrt(n)) -- the queue at its widest holds one entry per answer (1430 at n = 8),
// each a string of up to 2n characters, not one path.
//
// Siblings: GenerateBalancedParenthesesBacktracking and GenerateBalancedParenthesesGuarded are the
// recursive forms of this search.

public final class GenerateBalancedParenthesesIterative {

	public static List<String> generate(int n) {
		List<String> list = new ArrayList<>();
		Queue<Parentheses> queue = new LinkedList<>();
		queue.add(new Parentheses("", 0, 0)); // the state a recursive call would have carried
		while (!queue.isEmpty()) {
			Parentheses p = queue.poll();
			if (p.open == n && p.close == n) { // BASE: all brackets placed
				list.add(p.str); // RECORD
			} else {
				if (p.open < n) { // same test as the Guarded version's open > 0
					String s = p.str + "(";
					int open = p.open + 1;
					queue.add(new Parentheses(s, open, p.close)); // OPEN: place a "("
				}
				if (p.open > p.close) { // same test as its close > open
					String s = p.str + ")";
					int close = p.close + 1;
					queue.add(new Parentheses(s, p.open, close)); // CLOSE: place a ")" that closes a "("
				}
			}
		}
		return list; // no undo anywhere - each queued state owns its own string
	}

	public static void main(String[] args) {
		printLines(generate(3)); // ((())) (()()) (())() ()(()) ()()()
	}

	private static void printLines(List<String> list) {
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println();
	}

	private static final class Parentheses {
		private String str;
		private int open;
		private int close;

		public Parentheses(String str, int open, int close) {
			this.str = str;
			this.open = open;
			this.close = close;
		}
	}
}