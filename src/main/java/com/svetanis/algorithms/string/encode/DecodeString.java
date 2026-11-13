package com.svetanis.algorithms.string.encode;

import java.util.ArrayDeque;
import java.util.Deque;

// 394. Decode String
//
// k[text] means text repeated k times, and brackets nest: 3[a2[c]] is accaccacc. Return the
// fully expanded string.
//
// Each character is one of four kinds, and only the brackets touch the stack:
//   digit   builds the count being read: 12 is 1 * 10 + 2
//   letter  is added to the text of the bracket we are inside
//   '['     interrupts that text: park it with the count on the stack, and start both fresh
//   ']'     takes the pair back: the text before the bracket, then the inside repeated count times
// The bracket opened last is closed first, so the parked pairs form a stack.
//
// DecodeString394 is a version that keeps every character on one deque, and why that is slow.

public final class DecodeString {
	// Time Complexity: O(output length * nesting depth); a closed bracket copies what it produces,
	// so a character is copied again at every bracket around it. With one level it is linear.
	// Space Complexity: O(output length)

	public static String decode(String s) {
		Deque<Frame> stack = new ArrayDeque<>(); // one frame per '[' still open
		StringBuilder text = new StringBuilder(); // the letters of the bracket we are inside
		int count = 0; // the number being read right now
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				count = count * 10 + (c - '0'); // READ: one more digit; counts go up to 300
			} else if (c == '[') {
				stack.push(new Frame(text.toString(), count)); // PARK: what this bracket interrupts
				text = new StringBuilder(); // OPEN: the inside starts fresh
				count = 0; // and so does the next number
			} else if (c == ']') {
				text = closeBracket(stack.pop(), text); // CLOSE: the parked text, then the inside repeated
			} else {
				text.append(c); // LETTER: added to the bracket we are inside
			}
		}
		return text.toString();
	}

	// what a closed bracket becomes: the text before it, then its inside repeated count times
	private static StringBuilder closeBracket(Frame frame, StringBuilder inside) {
		StringBuilder result = new StringBuilder(frame.before());
		result.append(inside.toString().repeat(frame.count())); // REPEAT: the inside, count times
		return result;
	}

	// what one '[' sets aside: the text read before it, and how many times its inside repeats
	private record Frame(String before, int count) {
	}

	public static void main(String[] args) {
		System.out.println(decode("3[a]2[bc]")); // aaabcbc
		System.out.println(decode("3[a2[c]]")); // accaccacc, the inner bracket closes first
		System.out.println(decode("2[abc]3[cd]ef")); // abcabccdcdcdef
		System.out.println(decode("12[a]")); // aaaaaaaaaaaa, a count of two digits
	}
}
