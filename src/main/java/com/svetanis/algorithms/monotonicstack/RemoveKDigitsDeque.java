package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

// 402. Remove K Digits
//
// Remove k digits from the number num (a string) so that what remains is the smallest
// possible number. Leading zeros are dropped; nothing left means "0".
//
// Deleting a digit slides its right neighbour into its place, so the digit to delete is the
// leftmost one bigger than its immediate right neighbour. The kept digits never go down, so
// they live on a stack and a pop is a deletion. The stack is read back from the other end:
//
//     bottom (tail)                   top (head)
//     front of the number             back of the number
//     pollLast() / peekLast()         pop() / peek() / push()
//
// RemoveKDigits uses a StringBuilder as the stack, so the answer needs no second read.

public final class RemoveKDigitsDeque {
	// Time Complexity: O(n), every digit is pushed once and popped at most once
	// Space Complexity: O(n) for the stack and the result

	public static String removeKDigits(String num, int k) {
		Deque<Integer> stack = new ArrayDeque<>(); // the kept digits; bottom = front of the number
		for (int i = 0; i < num.length(); i++) {
			int digit = num.charAt(i) - '0';
			while (!stack.isEmpty() && k > 0 && digit < stack.peek()) { // a deletion is owed and it helps
				stack.pop(); // pop = delete: the new digit slides into its place
				k--; // only a real deletion spends k
			}
			stack.push(digit);
		}
		while (!stack.isEmpty() && k > 0) { // still owed: the kept digits never fall,
			stack.pop(); // so the largest are on top, the end of the number
			k--;
		}
		return frontToBack(stack);
	}

	// reads the stack from the bottom, the front of the number, dropping leading zeros
	private static String frontToBack(Deque<Integer> stack) {
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			int digit = stack.pollLast(); // the tail is the bottom: the front of the number
			if (sb.length() == 0 && digit == 0) {
				continue; // a leading zero: taken off the stack, not written
			}
			sb.append(digit);
		}
		return sb.length() == 0 ? "0" : sb.toString(); // everything deleted is the number 0
	}

	public static void main(String[] args) {
		System.out.println(removeKDigits("1432219", 3)); // 1219
		System.out.println(removeKDigits("10200", 1)); // 200, the leading zero dropped
		System.out.println(removeKDigits("10", 2)); // 0, everything deleted
		System.out.println(removeKDigits("12345", 2)); // 123, the owed deletions come off the end
	}
}
