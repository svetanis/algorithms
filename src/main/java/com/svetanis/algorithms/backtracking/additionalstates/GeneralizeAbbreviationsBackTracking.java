package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.ArrayList;
import java.util.List;

// 320. Generalized Abbreviation

// given a word, generate all of its
// unique generalized abbreviations

public final class GeneralizeAbbreviationsBackTracking {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static List<String> generate(String s) {
		List<String> abbreviation = new ArrayList<>();
		List<String> abbreviations = new ArrayList<>();
		dfs(s, abbreviation, abbreviations);
		return abbreviations;
	}

	private static void dfs(String s, List<String> abbreviation, List<String> abbreviations) {
		if ("".equals(s)) {
			abbreviations.add(String.join("", abbreviation));
			return;
		}
		for (int i = 1; i <= s.length(); i++) {
			abbreviation.add(Integer.toString(i));
			if (i < s.length()) {
				abbreviation.add(String.valueOf(s.charAt(i)));
				dfs(s.substring(i + 1), abbreviation, abbreviations);
				abbreviation.remove(abbreviation.size() - 1);
			} else {
				dfs(s.substring(i), abbreviation, abbreviations);
			}
			abbreviation.remove(abbreviation.size() - 1);
		}
		abbreviation.add(String.valueOf(s.charAt(0)));
		dfs(s.substring(1), abbreviation, abbreviations);
		abbreviation.remove(abbreviation.size() - 1);
	}

	public static void main(String[] args) {
		// [3, 2T, 1A1, 1AT, B2, B1T, BA1,BAT]
		System.out.println(generate("BAT"));
		// [4, 3e, 2d1, 2de, 1o2, 1o1e, 1od1, 1ode, c3, c2e, c1d1, c1de, co2, co1e,cod1, code]
		System.out.println(generate("code"));
	}
}