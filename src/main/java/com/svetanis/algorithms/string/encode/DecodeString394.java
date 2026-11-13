package com.svetanis.algorithms.string.encode;

import java.util.ArrayDeque;
import java.util.Deque;

// 394. Decode String -- a correct answer written the slow way, kept to read against DecodeString.
//
// Every character goes onto one deque: letters, digits and brackets alike. At each ']' it walks
// back to the matching '[', rebuilds the text inside, reads the count backwards, and pushes the
// expanded text back onto the deque one character at a time. Three costs follow from that:
//   - decoded() puts each character at the FRONT of a StringBuilder with insert(0, c), which
//     shifts everything already there: a bracket whose text is L long costs about L^2;
//   - the expansion is pushed back character by character, so an inner bracket's output is
//     popped and pushed again by every bracket around it;
//   - the walk back stops on a '[' the input is promised to contain; on "a]" it runs off the
//     empty deque and throws NullPointerException.
// DecodeString keeps only the brackets' context on the stack, and builds text in a StringBuilder.

public final class DecodeString394 {
	// Time Complexity: O(n^2) -- sb.insert(0, c) shifts the whole buffer
	// once per character, and nested brackets re-copy per level.
	// Space Complexity: O(n)

	public static String decode(String s) {
		Deque<Character> dq = new ArrayDeque<>(); // everything, bottom = front of the text
		for (char c : s.toCharArray()) {
			if (c == ']') {
				String decoded = decoded(dq); // POP: the text inside, walked back to '['
				dq.pollLast(); // DROP: the '[' itself
				int k = multiplier(dq); // READ: the count, from its last digit backwards
				expand(decoded, k, dq); // PUSH: the inside k times, one character at a time
			} else {
				dq.offerLast(c); // PUSH: a letter, a digit or a '['
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
		while (dq.peekLast() != '[') { // no isEmpty() check: relies on a '[' being there
			sb.insert(0, dq.pollLast()); // the slow line: shifts the whole buffer every time
		}
		return sb.toString();
	}

	private static int multiplier(Deque<Character> dq) {
		int k = 0;
		int base = 1;
		while (!dq.isEmpty() && Character.isDigit(dq.peekLast())) {
			k = k + (dq.pollLast() - '0') * base; // last digit first: 300 is 0 + 0 * 10 + 3 * 100
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
