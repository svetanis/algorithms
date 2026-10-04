package com.svetanis.algorithms.backtracking.combinations.strings;

import java.util.ArrayList;
import java.util.List;

// Every binary string of length n
//
// Given n, return every string of n bits, in order: n = 2 gives [00, 01, 10, 11].
//
// One choice per position, made independently: position 0 is 0 or 1, and for each, position 1 is
// 0 or 1, and so on -- 2^n strings. Each method makes exactly two calls per position, one with 0
// and one with 1.
//
// Every file in this folder is this one idea, one choice per position, varied:
//   LetterCombinationBacktrack       this with 'a' and 'b' in place of '0' and '1', the word
//                                    carried as a list of letters joined at the end
//   DecimalStringsOfLengthN          this with ten digits, where the two calls become a loop
//   AllBinaryStringsFromGivenPattern this where only the '?' positions branch
//   LetterCombinations*, BraceExpansion  this where each position has its own set of choices
//
// The three methods make the same strings in the same order; they differ only in how the string
// built so far is carried from one call to the next:
//   withNewString       a new String each call (prefix + "0"): the caller's prefix is never
//                       changed, so there is nothing to undo
//   withBuilderAndUndo  one StringBuilder shared by every call: append, recurse, then delete the
//                       character again, or the 1 would be added after the 0
//   withArrayOverwrite  one char[] of n slots: position i is written with 0, then overwritten
//                       with 1, so there is nothing to undo either
// withNewStringLoop is withNewString with its two calls written as a loop over '0' and '1'.

public final class BinaryStringsOfLengthN {
	// Time Complexity: O(n * 2^n), 2^n strings, each n characters long
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
		withNewString(n, prefix + "0", strings); // a new String: prefix itself is unchanged
		withNewString(n, prefix + "1", strings);
	}

	// withNewString with its two calls written as a loop over the characters '0' and '1' -- the
	// form DecimalStringsOfLengthN uses for ten digits
	public static List<String> withNewStringLoop(int n) {
		List<String> strings = new ArrayList<>();
		withNewStringLoop(n, "", strings);
		return strings;
	}

	private static void withNewStringLoop(int n, String prefix, List<String> strings) {
		if (prefix.length() == n) { // every position chosen
			strings.add(prefix);
			return;
		}
		for (char c = '0'; c <= '1'; c++) { // c = '0', then c = '1': the same two calls as above
			withNewStringLoop(n, prefix + c, strings);
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
		sb.append('0'); // choose 0
		withBuilderAndUndo(n, sb, strings);
		sb.deleteCharAt(sb.length() - 1); // undo, or the 1 lands after the 0
		sb.append('1'); // choose 1
		withBuilderAndUndo(n, sb, strings);
		sb.deleteCharAt(sb.length() - 1); // undo, for the caller's next choice
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
		slots[i] = '0';
		withArrayOverwrite(slots, i + 1, strings);
		slots[i] = '1'; // overwrites the 0: nothing to undo
		withArrayOverwrite(slots, i + 1, strings);
	}

	public static void main(String[] args) {
		System.out.println(withNewString(2)); // [00, 01, 10, 11]
		System.out.println(withNewStringLoop(2)); // [00, 01, 10, 11]
		System.out.println(withBuilderAndUndo(2)); // [00, 01, 10, 11]
		System.out.println(withArrayOverwrite(3)); // [000, 001, 010, 011, 100, 101, 110, 111]
	}
}
