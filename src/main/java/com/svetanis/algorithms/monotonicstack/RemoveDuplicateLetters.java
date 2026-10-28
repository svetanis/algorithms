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
//
// LC 1081 Smallest Subsequence of Distinct Characters is the same problem. lastIndex is an
// int[26] indexed by c - 'a', so it relies on "lowercase letters": an uppercase letter
// throws ArrayIndexOutOfBoundsException. For any char, use a Map or an int[128] indexed by c.
//
// Two methods, the same walk: remove keeps the row in a Deque and reverses it at the end;
// removeWithBuilder keeps it in a StringBuilder, which is already the answer in order.

public final class RemoveDuplicateLetters {
	// Time Complexity: O(n), every position is pushed at most once and popped at most once
	// Space Complexity: O(1), the stack and the set hold at most 26 letters

	public static String remove(String s) {
		int[] lastIndex = lastIndices(s); // LAST: last position of each letter
		Set<Character> onStack = new HashSet<>(); // the letters on the stack, for O(1) lookup
		Deque<Character> stack = new ArrayDeque<>(); // the kept letters; the top is the last kept
		for (int i = 0; i < s.length(); i++) { // WALK: s[i] arrives
			char c = s.charAt(i);
			if (onStack.contains(c)) {
				continue; // SKIP: already placed, and placed better than here
			}
			while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) { // DROP: empty test first
				onStack.remove(stack.pop()); // larger, and comes back later: take it then
			}
			stack.push(c); // KEEP: c is now the last kept letter
			onStack.add(c);
		}
		return bottomToTop(stack); // READ: bottom to top is left to right
	}

	// The same walk with the StringBuilder as the stack. Its last character is the top:
	//   peek -> kept.charAt(kept.length() - 1)
	//   pop  -> kept.deleteCharAt(kept.length() - 1)
	//   push -> kept.append(c)
	// The row is read left to right, so there is nothing to reverse.
	public static String removeWithBuilder(String s) {
		int[] lastIndex = lastIndices(s); // LAST: last position of each letter
		Set<Character> inRow = new HashSet<>(); // the letters in the row, for O(1) lookup
		StringBuilder kept = new StringBuilder(); // the kept row, left to right; its last char is the top
		for (int i = 0; i < s.length(); i++) { // WALK: s[i] arrives
			char c = s.charAt(i);
			if (inRow.contains(c)) {
				continue; // SKIP: already kept, one copy only
			}
			while (kept.length() > 0 // DROP: the row is not empty,
					&& kept.charAt(kept.length() - 1) > c // the last kept letter is bigger,
					&& lastIndex[kept.charAt(kept.length() - 1) - 'a'] > i) { // and it comes back after i
				inRow.remove(kept.charAt(kept.length() - 1));
				kept.deleteCharAt(kept.length() - 1); // pop
			}
			kept.append(c); // KEEP: push, c is now the last kept letter
			inRow.add(c);
		}
		return kept.toString(); // READ: already left to right
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
			lastIndex[s.charAt(i) - 'a'] = i; // LAST: the final write wins, so the last index
		}
		return lastIndex;
	}

	public static void main(String[] args) {
		System.out.println(remove("bcabc")); // abc
		System.out.println(remove("cbacdcbc")); // acdb
		System.out.println(removeWithBuilder("bcabc")); // abc
		System.out.println(removeWithBuilder("cbacdcbc")); // acdb
	}
}
