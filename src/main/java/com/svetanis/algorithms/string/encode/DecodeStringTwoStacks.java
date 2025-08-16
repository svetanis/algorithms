package com.svetanis.algorithms.string.encode;

import java.util.ArrayDeque;
import java.util.Deque;

// 394. Decode String

public final class DecodeStringTwoStacks {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String decode(String s) {
		int k = 0;
		String curr = "";
		Deque<String> sdq = new ArrayDeque<>();
		Deque<Integer> ndq = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			if (c == '[') {
				sdq.push(curr);
				ndq.push(k);
				curr = "";
				k = 0;
			} else if (c == ']') {
				int n = ndq.pop();
				String prev = sdq.pop();
				curr = prev + curr.repeat(n);
			} else if (Character.isDigit(c)) {
				k = k * 10 + (c - '0');
			} else {
				curr += c;
			}
		}
		return curr;
	}

	public static void main(String[] args) {
		System.out.println(decode("3[a]2[bc]")); // aaabcbc
		System.out.println(decode("3[a2[c]]")); // accaccacc
		System.out.println(decode("2[abc]3[cd]ef")); // abcabccdcdcdef
		System.out.println(decode("100[leetcode]"));
	}
}
