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

// The same search as GenerateBalancedParenthesesBacktracking with
// the pending work moved off the call stack and into a queue, so
// a whole level of the search is alive at the same time rather
// than a single path.

// Time Complexity: exponential - only valid prefixes are built,
// but the number of balanced strings is itself exponential in n
// (1430 at n = 8) and every one of them is produced
// Space Complexity: exponential - the queue holds one entry per
// answer at its widest, not one path

public final class GenerateBalancedParenthesesIterative {

	public static ImmutableList<String> generate(int n) {
		List<String> list = newArrayList();
		Queue<Parentheses> queue = newLinkedList();
		queue.add(new Parentheses("", 0, 0)); // the state a recursive call would have carried
		while (!queue.isEmpty()) {
			Parentheses p = queue.poll();
			if (p.open == n && p.close == n) {
				list.add(p.str);
			} else {
				if (p.open < n) { // same test as the Guarded version's open > 0
					String s = p.str + "(";
					int open = p.open + 1;
					queue.add(new Parentheses(s, open, p.close));
				}
				if (p.open > p.close) { // same test as its close > open
					String s = p.str + ")";
					int close = p.close + 1;
					queue.add(new Parentheses(s, p.open, close));
				}
			}
		}
		return newList(list); // no undo anywhere - each queued state owns its own string
	}

	public static void main(String[] args) {
		printLines(generate(3));
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