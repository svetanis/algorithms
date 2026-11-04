package com.svetanis.algorithms.bits.gray;

import java.util.ArrayList;
import java.util.List;

// 89. Gray Code
//
// Input: an int n. Returns 2^n numbers, every number from 0 to 2^n - 1 exactly once, starting at 0,
// where each number differs from the next in exactly one binary digit, and the last from the first.
// On LeetCode: 1 <= n <= 16.
//
// The one idea: the code at step i is i ^ (i >> 1). Slot k of the code compares slot k of i with
// slot k + 1 of i: 1 when they differ, 0 when they agree. Going from i to i + 1 flips a run of low
// slots of i -- its lowest 0 and every 1 below it. Inside that run each slot and the slot above it
// both flip, so their comparison does not change; only the top slot of the run flips while the
// slot above it stays, so exactly one slot of the code changes. The last step, 2^n - 1, has code
// 2^(n - 1), a single 1, which is one digit away from the first code, 0.
// grayCodeBinary returns the same codes written as n-character strings, highest slot first.
//
// Time: grayCode O(2^n) -- one code per step; grayCodeBinary O(n^2 * 2^n) -- each code is built
// by n string appends, and each append copies the string so far.
// Space: O(2^n) -- the list; the strings hold n characters each, O(n * 2^n) in all.

public final class GrayCode {
	// Time Complexity: O(2^n), and O(n^2 * 2^n) for the strings
	// Space Complexity: O(2^n)

	public static List<Integer> grayCode(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 1 << n; i++) { // WALK: steps 0 .. 2^n - 1
			int gray = i ^ (i >> 1); // CODE: one slot from the previous code
			list.add(gray);
		}
		return list;
	}

	public static List<String> grayCodeBinary(int n) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 1 << n; i++) { // WALK: steps 0 .. 2^n - 1
			int gray = i ^ (i >> 1); // CODE: as in grayCode
			list.add(binaryCode(n, gray));
		}
		return list;
	}

	private static String binaryCode(int n, int gray) {
		String code = "";
		for (int j = n - 1; j >= 0; j--) { // WALK: highest slot first
			boolean isSet = (gray & (1 << j)) != 0; // TEST: slot j
			code += isSet ? '1' : '0'; // APPEND: one digit
		}
		return code;
	}

	public static void main(String[] args) {
		System.out.println(grayCode(2)); // [0, 1, 3, 2]
		System.out.println(grayCode(1)); // [0, 1]

		System.out.println(grayCodeBinary(2)); // [00, 01, 11, 10]
		System.out.println(grayCodeBinary(1)); // [0, 1]
	}
}