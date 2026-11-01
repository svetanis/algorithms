package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// 784. Letter Case Permutation
//
// Input: a string s of lowercase letters, uppercase letters and digits. On LeetCode:
// 1 <= s.length() <= 12. Returns every string made from s by changing the case of any of its
// letters, in any order; digits stay as they are.
//
// The one idea: list holds every answer for the characters looked at so far, with the rest of
// the string still as it was in s. Looking at one more character: a digit changes nothing; a
// letter doubles the list -- for each string already there, add a copy with that letter in the
// other case. After the last character the list holds all 2^m answers (m = number of letters),
// each exactly once, because every letter splits each existing string into two different ones.
//
// Time: O(2^m * n) -- m letters; the list doubles m times, and each new string is built in O(n).
// Space: O(2^m * n) -- the list is the answer.
//
// Siblings: LetterCasePermutationBacktrack builds the same strings by recursion on one array;
// LetterCasePermutationRecursive lowers and raises each position and collects into a set.

public final class LetterCasePermutationIterative {

	public static List<String> permute(String s) {
		List<String> list = new ArrayList<>();
		list.add(s); // START: the string as given
		for (int i = 0; i < s.length(); i++) { // WALK: one character at a time
			if (Character.isLetter(s.charAt(i))) { // a digit adds nothing
				int size = list.size(); // only the strings that exist before this letter
				for (int j = 0; j < size; j++) {
					char[] chars = list.get(j).toCharArray();
					if (Character.isUpperCase(chars[i])) { // FLIP: copy with the other case
						chars[i] = Character.toLowerCase(chars[i]);
					} else {
						chars[i] = Character.toUpperCase(chars[i]);
					}
					list.add(String.valueOf(chars));
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(permute("ad52")); // [ad52, Ad52, aD52, AD52]
		System.out.println(permute("ab7c")); // [ab7c, Ab7c, aB7c, AB7c, ab7C, Ab7C, aB7C, AB7C]

		System.out.println(permute("a1b2")); // [a1b2, A1b2, a1B2, A1B2]
		System.out.println(permute("3z4")); // [3z4, 3Z4]
	}
}
