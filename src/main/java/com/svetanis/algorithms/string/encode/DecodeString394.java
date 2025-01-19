package com.svetanis.algorithms.string.encode;

import java.util.ArrayDeque;
import java.util.Deque;

// 394. Decode String

public final class DecodeString394 {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String decode(String s) {
		Deque<Character> dq = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			if (c == ']') {
				String decoded = decoded(dq);
				dq.pollLast();
				int k = multiplier(dq);
				expand(decoded, k, dq);
			} else {
				dq.offerLast(c);
			}
		}
		return decode(dq);
	}

	private static String decode(Deque<Character> dq) {
		StringBuilder sb = new StringBuilder();
		for (char c : dq) {
			sb.append(c);
		}
		return sb.toString();
	}

	private static void expand(String s, int k, Deque<Character> dq) {
		for (int i = 0; i < k; i++) {
			for (char curr : s.toCharArray()) {
				dq.offerLast(curr);
			}
		}
	}

	private static String decoded(Deque<Character> dq) {
		StringBuilder sb = new StringBuilder();
		while (dq.peekLast() != '[') {
			sb.insert(0, dq.pollLast());
		}
		return sb.toString();
	}

	private static int multiplier(Deque<Character> dq) {
		int k = 0;
		int base = 1;
		while (!dq.isEmpty() && Character.isDigit(dq.peekLast())) {
			k = k + (dq.pollLast() - '0') * base;
			base *= 10;
		}
		return k;
	}

	public static void main(String[] args) {
		System.out.println(decode("3[a]2[bc]")); // aaabcbc
		System.out.println(decode("3[a2[c]]")); // accaccacc
		System.out.println(decode("2[abc]3[cd]ef")); // abcabccdcdcdef
		System.out.println(decode("100[leetcode]"));
	}
}
