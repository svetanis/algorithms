package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 784. Letter Case Permutation
//
// Input: a string s of lowercase letters, uppercase letters and digits. On LeetCode:
// 1 <= s.length() <= 12. Returns every string made from s by changing the case of any of its
// letters, in any order; digits stay as they are.
//
// The one idea: at each position, set the character to lower case and recurse, then set it to
// upper case and recurse. A digit has no case, so both settings leave it alone and the same
// strings are found twice; the set keeps one copy of each. Every string is added when the array
// holds it, not only at the last position, so the set is complete once the recursion ends.
// The order of the printed strings is the order of a HashSet, not the order they were found.
//
// Time: O(2^n * n) -- n is the length of s, digits included: the recursion branches twice at
// every position, 2^n calls, each adding strings of length n to the set.
// Space: O(2^m * n) -- the set holds the 2^m answers (m = number of letters), each of length n.
//
// Siblings: LetterCasePermutationBacktrack branches only at letters, so it makes 2^m answers
// without duplicates; LetterCasePermutationIterative grows the answer list one position at a time.

public final class LetterCasePermutationRecursive {

	public static List<String> permute(String s) {
		Set<String> set = new HashSet<>(); // a set: the digits make every string repeat
		permute(s.toCharArray(), 0, set);
		return new ArrayList<>(set);
	}

	private static void permute(char[] chars, int index, Set<String> set) {
		if (index == chars.length) {
			return;
		}
		chars[index] = Character.toLowerCase(chars[index]); // LOWER: this position in lower case
		set.add(new String(chars)); // RECORD
		permute(chars, index + 1, set);

		chars[index] = Character.toUpperCase(chars[index]); // UPPER: this position in upper case
		set.add(new String(chars)); // RECORD
		permute(chars, index + 1, set);
	}

	public static void main(String[] args) {
		System.out.println(permute("ad52")); // [ad52, aD52, AD52, Ad52]
		System.out.println(permute("ab7c")); // [ab7c, ab7C, aB7C, aB7c, Ab7C, Ab7c, AB7C, AB7c]

		System.out.println(permute("a1b2")); // [a1b2, a1B2, A1B2, A1b2]
		System.out.println(permute("3z4")); // [3z4, 3Z4]
	}
}
