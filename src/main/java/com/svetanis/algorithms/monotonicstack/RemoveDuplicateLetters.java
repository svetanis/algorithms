package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// 316. Remove Duplicate Letters

public final class RemoveDuplicateLetters {
	// Time complexity: O(n)

	public static String remove(String s) {
		int[] a = indices(s);
		Set<Character> set = new HashSet<>();
		Deque<Character> dq = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (set.contains(c)) {
				continue;
			}
			while (!dq.isEmpty() && dq.peek() > c && a[dq.peek() - 'a'] > i) {
				set.remove(dq.pop());
			}
			dq.push(c);
			set.add(c);
		}
		return distinct(dq);
	}

	private static String distinct(Deque<Character> dq) {
		StringBuilder sb = new StringBuilder();
		for (char c : dq) {
			sb.append(c);
		}
		return sb.reverse().toString();
	}

	private static int[] indices(String s) {
		int[] a = new int[26];
		for (int i = 0; i < s.length(); i++) {
			a[s.charAt(i) - 'a'] = i;
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(remove("bcabc")); // abc
		System.out.println(remove("cbacdcbc")); // acdb
	}
}
