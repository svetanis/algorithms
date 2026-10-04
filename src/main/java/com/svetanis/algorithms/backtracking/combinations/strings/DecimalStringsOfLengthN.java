package com.svetanis.algorithms.backtracking.combinations.strings;

import java.util.ArrayList;
import java.util.List;

// Every decimal string of length n
//
// Given n, return every string of n decimal digits, in order: n = 2 gives the 100 strings
// 00, 01, ..., 99.
//
// One choice per position, made independently: position 0 is any digit 0..9, and for each,
// position 1 is any digit, and so on -- 10^n strings. BinaryStringsOfLengthN is the same with two
// digits, where the loop is written as two calls.
//
// The three methods make the same strings in the same order; they differ only in how the string
// built so far is carried from one call to the next:
//   withNewString       a new String each call (prefix + d): the caller's prefix is never
//                       changed, so there is nothing to undo
//   withBuilderAndUndo  one StringBuilder shared by every call: append, recurse, then delete the
//                       digit again, or the next digit would be added after it
//   withArrayOverwrite  one char[] of n slots: position i is written, and the next digit at i
//                       simply overwrites it, so there is nothing to undo either

public final class DecimalStringsOfLengthN {
	// Time Complexity: O(n * 10^n), 10^n strings, each n characters long
	// Space Complexity: O(n) besides the output, the recursion depth and the string being built
	// (withNewString also keeps one prefix per level, O(n^2))

	public static List<String> withNewString(int n) {
		List<String> strings = new ArrayList<>();
		withNewString(n, "", strings);
		return strings;
	}

	private static void withNewString(int n, String prefix, List<String> strings) {
		if (prefix.length() == n) { // every position chosen
			strings.add(prefix);
			return;
		}
		for (char d = '0'; d <= '9'; d++) {
			withNewString(n, prefix + d, strings); // a new String: prefix itself is unchanged
		}
	}

	public static List<String> withBuilderAndUndo(int n) {
		List<String> strings = new ArrayList<>();
		withBuilderAndUndo(n, new StringBuilder(), strings);
		return strings;
	}

	private static void withBuilderAndUndo(int n, StringBuilder sb, List<String> strings) {
		if (sb.length() == n) { // every position chosen
			strings.add(sb.toString()); // a copy: sb keeps changing
			return;
		}
		for (char d = '0'; d <= '9'; d++) {
			sb.append(d); // choose
			withBuilderAndUndo(n, sb, strings);
			sb.deleteCharAt(sb.length() - 1); // undo, or the next digit lands after this one
		}
	}

	public static List<String> withArrayOverwrite(int n) {
		List<String> strings = new ArrayList<>();
		withArrayOverwrite(new char[n], 0, strings);
		return strings;
	}

	private static void withArrayOverwrite(char[] slots, int i, List<String> strings) {
		if (i == slots.length) { // every position chosen
			strings.add(new String(slots)); // a copy: slots keeps changing
			return;
		}
		for (char d = '0'; d <= '9'; d++) {
			slots[i] = d; // overwrites the previous digit at i: nothing to undo
			withArrayOverwrite(slots, i + 1, strings);
		}
	}

	public static void main(String[] args) {
		System.out.println(withNewString(1)); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
		System.out.println(withBuilderAndUndo(2).size()); // 100
		System.out.println(withArrayOverwrite(3).size()); // 1000
	}
}
