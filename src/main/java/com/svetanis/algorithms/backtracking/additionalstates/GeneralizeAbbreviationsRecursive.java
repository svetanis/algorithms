package com.svetanis.algorithms.backtracking.additionalstates;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given a word, generate all of its
// unique generalized abbreviations

public final class GeneralizeAbbreviationsRecursive {
	// Time Complexity: O(n * 2^n)
	// Space Complexity: O(n * 2^n)

	public static ImmutableList<String> generate(String s) {
		List<String> list = newArrayList();
		dfs(s, new StringBuilder(), 0, 0, list);
		return newList(list);
	}

	private static void dfs(String s, StringBuilder sb, int start, int count, List<String> list) {
		if (start == s.length()) {
			if (count != 0) {
				sb.append(count);
			}
			list.add(sb.toString());
			return;
		}

		// continue abbreviation
		StringBuilder sbc = new StringBuilder(sb);
		dfs(s, sbc, start + 1, count + 1, list);
		// restart abbreviation
		if (count != 0) {
			sb.append(count);
		}
		StringBuilder sbr = new StringBuilder(sb);
		sbr.append(s.charAt(start));
		dfs(s, sbr, start + 1, 0, list);
	}

	public static void main(String[] args) {
		System.out.println(generate("BAT"));
		System.out.println(generate("code"));
	}
}