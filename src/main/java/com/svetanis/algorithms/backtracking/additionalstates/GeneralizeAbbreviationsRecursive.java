package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.List;

// 320. Generalized Abbreviation
//
// Input: a word. Replace any number of non-overlapping, non-adjacent blocks of its letters with
// the length of the block; "word" becomes "w2" or "1o1d" or "4" and so on. Returns every
// abbreviation, each once, in any order. On LeetCode: 1 <= word.length <= 15, lowercase letters.
//
// The one idea: every letter is either kept or abbreviated, so the abbreviations are exactly the
// 2^n choices of which letters to keep. One call decides the letter at start. count is the
// number of abbreviated letters waiting just before it, not yet written. If the letter is
// abbreviated, only count grows by one. If it is kept, the waiting count is written first (when it
// is not 0) and then the letter, and count starts again from 0. Waiting counts are written only
// when a letter is kept or the word ends, so two numbers can never touch.
//
// Time: O(n * 2^n) -- 2^n answers, and every call copies the StringBuilder, O(n).
// Space: O(n * 2^n) for the answers; besides them O(n^2) -- the recursion is n deep and each level
// holds its own StringBuilder.
//
// Siblings: GeneralizedAbbreviationsIterative is this version with a queue instead of recursion;
// GeneralizeAbbreviationsBackTracking picks a whole block at a time and so makes the answers in a
// different order.

public final class GeneralizeAbbreviationsRecursive {

	public static List<String> generate(String s) {
		List<String> list = new ArrayList<>();
		dfs(s, new StringBuilder(), 0, 0, list);
		return list;
	}

	private static void dfs(String s, StringBuilder sb, int start, int count, List<String> list) {
		if (start == s.length()) { // BASE: every letter is decided
			if (count != 0) {
				sb.append(count); // WRITE: the number still waiting at the end
			}
			list.add(sb.toString());
			return;
		}

		// continue abbreviation
		StringBuilder sbc = new StringBuilder(sb);
		dfs(s, sbc, start + 1, count + 1, list); // ABBREVIATE: this letter joins the waiting count
		// restart abbreviation
		if (count != 0) {
			sb.append(count); // WRITE: the waiting count comes out before a kept letter
		}
		StringBuilder sbr = new StringBuilder(sb);
		sbr.append(s.charAt(start)); // KEEP: this letter; the count starts again from 0
		dfs(s, sbr, start + 1, 0, list);
	}

	public static void main(String[] args) {
		System.out.println(generate("BAT")); // [3, 2T, 1A1, 1AT, B2, B1T, BA1, BAT]
		System.out.println(generate("code")); // [4, 3e, 2d1, 2de, 1o2, 1o1e, 1od1, 1ode, c3, c2e, c1d1, c1de, co2, co1e, cod1, code]
	}
}