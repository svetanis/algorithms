package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// 784. Letter Case Permutation
//
// Input: a string s of lowercase letters, uppercase letters and digits. On LeetCode:
// 1 <= s.length() <= 12. Returns every string made from s by changing the case of any of its
// letters, in any order; digits stay as they are.
//
// The one idea: go left to right, and at each position the string so far is already settled.
// At a digit there is one choice -- leave it. At a letter there are two: leave it as it is,
// or flip its case (an ASCII upper and lower case letter differ in one bit, the one worth 32,
// the bit of ' ', so chars[index] ^= ' ' flips it). After the last position the whole array is
// one answer. The array is edited in place and the flip is undone after the call, so the
// second branch at a letter starts from the same array as the first.
//
// Time: O(2^m * n) -- m letters give 2^m answers, each copied out in O(n).
// Space: O(n) beyond the answer -- recursion depth n.
//
// Siblings: LetterCasePermutationIterative grows the answer list one position at a time;
// LetterCasePermutationRecursive lowers and raises each position and collects into a set.

public final class LetterCasePermutationBacktrack {

	public static List<String> permute(String s) {
		List<String> list = new ArrayList<>();
		permute(s.toCharArray(), 0, list);
		return list;
	}

	private static void permute(char[] chars, int index, List<String> list) {
		if (index >= chars.length) {
			list.add(new String(chars)); // RECORD: every position decided
			return;
		}
		permute(chars, index + 1, list); // KEEP: leave this position as it is
		if (Character.isLetter(chars[index])) {
			// toggle the case
			chars[index] ^= ' '; // FLIP: the other case
			permute(chars, index + 1, list);
			// backtrack
			chars[index] ^= ' '; // UNDO: flip back
		}
	}

	public static void main(String[] args) {
		System.out.println(permute("ad52")); // [ad52, aD52, Ad52, AD52]
		System.out.println(permute("ab7c")); // [ab7c, ab7C, aB7c, aB7C, Ab7c, Ab7C, AB7c, AB7C]

		System.out.println(permute("a1b2")); // [a1b2, a1B2, A1b2, A1B2]
		System.out.println(permute("3z4")); // [3z4, 3Z4]
	}
}
