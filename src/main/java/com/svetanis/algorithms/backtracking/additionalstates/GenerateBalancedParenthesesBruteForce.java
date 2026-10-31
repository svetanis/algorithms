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
// The one idea: do not think, build every string of 2n characters over "(" and ")" and keep the
// balanced ones. One queue entry is a string of the characters chosen so far; nothing is ever
// refused while building, and a string is tested only once it has all 2n characters. It is
// balanced when, reading left to right, the number of "(" never falls behind the number of ")"
// and the two end equal. This is the slow baseline to compare the other four with, which refuse a
// bracket the moment it cannot lead anywhere.
//
// Time: O(2^(2n) * n) -- every string of length 2n is built (2^(2n) of them), and each is
// scanned once to see whether it is balanced.
// Space: O(2^(2n) * n) -- the queue holds every string of length 2n at the same time.
//
// Siblings: GenerateBalancedParenthesesBacktracking, GenerateBalancedParenthesesGuarded,
// GenerateBalancedParenthesesIterative and GenerateBalancedParenthesesRecursive build only
// prefixes that can still be finished.

public final class GenerateBalancedParenthesesBruteForce {

	private static final char[] BOTH = { '(', ')' };

	public static List<String> generate(int n) {
		List<String> list = new ArrayList<>();
		Queue<String> queue = new LinkedList<>();
		queue.add("");
		while (!queue.isEmpty()) {
			String s = queue.poll();
			if (s.length() == 2 * n) { // the ONLY test, and only on a finished string
				if (isBalanced(s)) {
					list.add(s); // RECORD: a balanced string of length 2n
				}
				continue;
			}
			for (char c : BOTH) { // both always go back on - nothing is ever refused
				queue.add(s + c); // EXTEND: one more character
			}
		}
		return list;
	}

	private static boolean isBalanced(String s) {
		int open = 0; // OPEN: "(" not yet closed, so far
		for (int i = 0; i < s.length(); i++) {
			open += s.charAt(i) == '(' ? 1 : -1;
			if (open < 0) { // a ) with nothing open - dead whatever follows
				return false;
			}
		}
		return open == 0; // and nothing may be left unclosed
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
