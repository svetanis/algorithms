package com.svetanis.algorithms.backtracking.additionalstates;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// For a given number ‘N’, write a function to generate 
// all combination of ‘N’ pairs of balanced parentheses.

public final class GenerateBalancedParenthesesIterative {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static ImmutableList<String> generate(int n) {
		List<String> list = newArrayList();
		Queue<Parentheses> queue = newLinkedList();
		queue.add(new Parentheses("", 0, 0));
		while (!queue.isEmpty()) {
			Parentheses p = queue.poll();
			if (p.open == n && p.close == n) {
				list.add(p.str);
			} else {
				if (p.open < n) {
					String s = p.str + "(";
					int open = p.open + 1;
					queue.add(new Parentheses(s, open, p.close));
				}
				if (p.open > p.close) {
					String s = p.str + ")";
					int close = p.close + 1;
					queue.add(new Parentheses(s, p.open, close));
				}
			}
		}
		return newList(list);
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