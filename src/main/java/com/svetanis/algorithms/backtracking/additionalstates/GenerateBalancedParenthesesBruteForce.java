package com.svetanis.algorithms.backtracking.additionalstates;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// 22. Generate Parentheses

// For a given number ‘N’, write a function to generate
// all combination of ‘N’ pairs of balanced parentheses.

// Every string of length 2n over the two characters is built,
// and nothing is rejected while it is being built - a string is
// tested only once it is complete. Compare the other four here,
// which refuse a bracket the moment it cannot lead anywhere.

// Time Complexity: O(2^(2n) * n) - every string of length 2n is
// built, and each is scanned once to see whether it is balanced
// Space Complexity: O(2^(2n) * n) - the queue holds every string
// of length 2n at the same time

public final class GenerateBalancedParenthesesBruteForce {

	private static final char[] BOTH = { '(', ')' };

	public static ImmutableList<String> generate(int n) {
		List<String> list = newArrayList();
		Queue<String> queue = newLinkedList();
		queue.add("");
		while (!queue.isEmpty()) {
			String s = queue.poll();
			if (s.length() == 2 * n) { // the ONLY test, and only on a finished string
				if (isBalanced(s)) {
					list.add(s);
				}
				continue;
			}
			for (char c : BOTH) { // both always go back on - nothing is ever refused
				queue.add(s + c);
			}
		}
		return newList(list);
	}

	private static boolean isBalanced(String s) {
		int open = 0;
		for (int i = 0; i < s.length(); i++) {
			open += s.charAt(i) == '(' ? 1 : -1;
			if (open < 0) { // a ) with nothing open - dead whatever follows
				return false;
			}
		}
		return open == 0; // and nothing may be left unclosed
	}

	public static void main(String[] args) {
		printLines(generate(2));
		System.out.println();
		printLines(generate(3));
	}
}
