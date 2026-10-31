package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.List;

// 320. Generalized Abbreviation
//
// Input: a word. Replace any number of non-overlapping, non-adjacent blocks of its letters with
// the length of the block; "word" becomes "w2" or "1o1d" or "4" and so on. Returns every
// abbreviation, each once, in any order. On LeetCode: 1 <= word.length <= 15, lowercase letters.
//
// The one idea: every letter is either kept or abbreviated, and two neighbouring abbreviated
// letters must read as one number, so the abbreviations are exactly the 2^n choices of which
// letters to keep. This version writes the answer as a list of pieces and picks the FIRST piece of
// what is left: either one kept letter, or a number i (the first i letters, abbreviated) followed
// by the kept letter after them -- a number is always followed by a letter, so two numbers can
// never touch. The rest of the word, s.substring(...), is solved the same way. If the whole rest
// is abbreviated, there is no letter to follow the number and it ends the answer.
//
// Time: O(n * 2^n) -- 2^n answers, and each is joined from up to n pieces; every call copies the
// rest of the word, O(n).
// Space: O(n * 2^n) for the answers; besides them O(n^2) -- the recursion is n deep and each level
// holds a copy of the rest of the word.
//
// Siblings: GeneralizeAbbreviationsRecursive makes the same 2^n answers by deciding one letter at
// a time -- keep it, or add it to a running count; GeneralizedAbbreviationsIterative is that
// version with a queue. This version picks a whole block at a time, so its answers come out in a
// different order.

public final class GeneralizeAbbreviationsBackTracking {

	public static List<String> generate(String s) {
		List<String> abbreviation = new ArrayList<>();
		List<String> abbreviations = new ArrayList<>();
		dfs(s, abbreviation, abbreviations);
		return abbreviations;
	}

	private static void dfs(String s, List<String> abbreviation, List<String> abbreviations) {
		if ("".equals(s)) { // BASE: nothing left, the pieces are one answer
			abbreviations.add(String.join("", abbreviation));
			return;
		}
		for (int i = 1; i <= s.length(); i++) { // ABBREVIATE: the first i letters become the number i
			abbreviation.add(Integer.toString(i));
			if (i < s.length()) {
				abbreviation.add(String.valueOf(s.charAt(i))); // KEEP: the letter after the number
				dfs(s.substring(i + 1), abbreviation, abbreviations);
				abbreviation.remove(abbreviation.size() - 1); // UNDO the kept letter
			} else {
				dfs(s.substring(i), abbreviation, abbreviations); // the whole rest was abbreviated
			}
			abbreviation.remove(abbreviation.size() - 1); // UNDO the number
		}
		abbreviation.add(String.valueOf(s.charAt(0))); // KEEP: the first letter, no number before it
		dfs(s.substring(1), abbreviation, abbreviations);
		abbreviation.remove(abbreviation.size() - 1); // UNDO the kept letter
	}

	public static void main(String[] args) {
		System.out.println(generate("BAT")); // [1A1, 1AT, 2T, 3, B1T, B2, BA1, BAT]
		System.out.println(generate("code")); // [1o1e, 1o2, 1od1, 1ode, 2d1, 2de, 3e, 4, c1d1, c1de, c2e, c3, co1e, co2, cod1, code]
	}
}