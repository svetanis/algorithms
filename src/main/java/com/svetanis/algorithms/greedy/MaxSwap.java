package com.svetanis.algorithms.greedy;

import static java.lang.Integer.parseInt;

// 670. Maximum Swap
//
// Input: num -- a whole number. Two of its digits may be swapped, at most once. Returns the largest
// number that can be made. On LeetCode: 0 <= num <= 10^8. That cap is what keeps every swapped
// result inside an int; a number near Integer.MAX_VALUE can swap into one that does not fit, and a
// negative number is not allowed.
//
// The one idea: a digit further left is worth more than all the digits to its right put together,
// so the best swap is the one that makes the leftmost possible digit bigger. Walk the digits from
// the left. Every digit that has no bigger digit after it is already as large as it can be, so it
// stays. The first digit that does have a bigger digit after it is the one to change: swap it with
// the biggest digit after it. If that biggest digit appears more than once, take the copy furthest
// right, so the smaller digit sent back lands as far right as possible, where it costs the least.
// Only one swap is allowed, so the walk stops there and nothing is ever revisited.
//
// The table largest[i] answers "where is the biggest digit at or after position i" for every i at
// once. It is filled right to left and moves to a new position only on a strictly bigger digit, so
// among equal biggest digits it keeps the rightmost.
//
// maxSwap2 makes the same swap from a different table: last[d], the last index where each digit
// 0-9 appears. At each position it tries d from 9 down to one more than the digit there, and the
// first d with last[d] > i is the largest bigger digit after it -- already its rightmost copy. A
// digit that never appears keeps last[d] = 0, which is never > i, so it is never chosen.
//
// Time: O(d) -- d is the number of digits, at most 9 here: one pass fills the table, one finds the
// swap. maxSwap2 tries at most 9 values of d per position, so also O(d).
// Space: O(d) -- the digit array and the table; maxSwap2's table is ten ints.
//
// Related: MaxSwapAtMostK allows up to k swaps on a digit string and searches every helpful swap
// instead of choosing one by this rule.

public final class MaxSwap {

	public static int maxSwap(int num) {
		char[] digits = String.valueOf(num).toCharArray();
		int[] largest = largest(digits); // TABLE: pass 1, right to left
		for (int i = 0; i < digits.length; i++) { // WALK: pass 2, left to right
			int index = largest[i];
			if (digits[i] < digits[index]) { // FIRST digit with a bigger one after it
				swap(digits, i, index);
				break; // STOP: one swap only
			}
		}
		return parseInt(new String(digits)); // RETURN: the digits back as a number
	}

	public static int maxSwap2(int num) {
		char[] digits = Integer.toString(num).toCharArray(); // most significant digit first
		int[] last = new int[10]; // last[d]: last index of digit d (0 if absent -- never > i)
		for (int i = 0; i < digits.length; i++) {
			last[digits[i] - '0'] = i; // RECORD: pass 1
		}
		for (int i = 0; i < digits.length; i++) { // WALK: pass 2, left to right
			for (int d = 9; d >= digits[i] - '0' + 1; d--) { // FIND: largest first, only bigger than digits[i]
				if (last[d] > i) { // d appears to the right of i
					swap(digits, i, last[d]); // SWAP with its rightmost copy
					return parseInt(new String(digits)); // RETURN: the first swap found is the best
				}
			}
		}
		return num; // RETURN: no digit has a bigger one after it
	}

	public static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	// index of the largest digit at or after i; among equal digits, the rightmost
	private static int[] largest(char[] digits) {
		int n = digits.length;
		int[] a = new int[n];
		int max = n - 1;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] > digits[max]) { // MOVE only on strictly bigger -- keeps the rightmost
				max = i;
			}
			a[i] = max; // RECORD the answer for position i
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(maxSwap(2736)); // 7236
		System.out.println(maxSwap(9973)); // 9973
		System.out.println(maxSwap2(2736)); // 7236
		System.out.println(maxSwap2(1993)); // 9913 -- the rightmost 9
	}
}
