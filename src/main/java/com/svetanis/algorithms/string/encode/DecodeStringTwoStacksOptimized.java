package com.svetanis.algorithms.string.encode;

import java.util.ArrayDeque;
import java.util.Deque;

// 394. Decode String

// THE SAME TWO-STACK ALGORITHM AS
// DecodeStringTwoStacks.java, WITH THE ACCUMULATOR FIXED.
// that file keeps the partial text in a String and grows it
// with curr += c, so every literal character copies
// everything decoded so far. this one keeps a StringBuilder
// per bracket level and never calls toString() until the end.
// nothing is built twice.

// the stack holds StringBuilders, not Strings: on '[' the
// current builder is parked and a fresh one started, and on
// ']' the finished child is appended into the parked parent
// n times. the parent then becomes current again.

public final class DecodeStringTwoStacksOptimized {
	// Time Complexity: O(output length)
	// Space Complexity: O(output length)

	public static String decode(String s) {
		int k = 0;
		StringBuilder curr = new StringBuilder();
		Deque<StringBuilder> sdq = new ArrayDeque<>();
		Deque<Integer> ndq = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			if (c == '[') {
				sdq.push(curr);
				ndq.push(k);
				curr = new StringBuilder();
				k = 0;
			} else if (c == ']') {
				curr = combine(sdq.pop(), curr, ndq.pop());
			} else if (Character.isDigit(c)) {
				k = k * 10 + (c - '0');
			} else {
				curr.append(c);
			}
		}
		return curr.toString();
	}

	private static StringBuilder combine(StringBuilder prev, StringBuilder curr, int n) {
		for (int i = 0; i < n; i++) {
			prev.append(curr);
		}
		return prev;
	}

	public static void main(String[] args) {
		System.out.println(decode("3[a]2[bc]")); // aaabcbc
		System.out.println(decode("3[a2[c]]")); // accaccacc
		System.out.println(decode("2[abc]3[cd]ef")); // abcabccdcdcdef
		System.out.println(decode("100[leetcode]"));
	}
}
