package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// 316. Remove Duplicate Letters
//
// s holds lowercase letters. Keep exactly one copy of each letter, so that the result is the
// smallest in dictionary order among all possible results.
//
// Remove K Digits with a different permission to pop: a larger letter before a smaller one
// should go, but only if it appears again later, so that it can still be taken then.

public final class RemoveDuplicateLetters {
	// Time Complexity: O(n), every position is pushed at most once and popped at most once
	// Space Complexity: O(1), the stack and the set hold at most 26 letters

	public static String remove(String s) {
		int[] lastIndex = lastIndices(s); // last position of each letter
		Set<Character> onStack = new HashSet<>();
		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (onStack.contains(c)) {
				continue; // already placed, and placed better than here
			}
			while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
				onStack.remove(stack.pop()); // larger, and comes back later: take it then
			}
			stack.push(c);
			onStack.add(c);
		}
		return bottomToTop(stack);
	}

	// push() adds at the head, so iterating yields the newest first; reversed, the oldest first
	private static String bottomToTop(Deque<Character> stack) {
		StringBuilder sb = new StringBuilder();
		for (char c : stack) {
			sb.append(c);
		}
		return sb.reverse().toString();
	}

	private static int[] lastIndices(String s) {
		int[] lastIndex = new int[26];
		for (int i = 0; i < s.length(); i++) {
			lastIndex[s.charAt(i) - 'a'] = i;
		}
		return lastIndex;
	}

	public static void main(String[] args) {
		System.out.println(remove("bcabc")); // abc
		System.out.println(remove("cbacdcbc")); // acdb
	}
}
