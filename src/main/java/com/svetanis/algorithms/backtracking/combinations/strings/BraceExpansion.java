package com.svetanis.algorithms.backtracking.combinations.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1087. Brace Expansion
//
// Given a string where each position is either a letter or a group of options in braces,
// "{a,b}c{d,e}f", return every word it can spell, one option per group, sorted:
// [acdf, acef, bcdf, bcef]. No nested braces.
//
// Two steps. parse cuts the string into parts, each part a list of options: a brace group gives
// its options, a run of plain letters gives a single option. Then dfs is the one choice per
// position of BinaryStringsOfLengthN and LC 17, with the choices at each position read from the
// parts -- the same as LetterCombinationBacktrack, it carries the word as a list joined at the
// end. The words are sorted once, at the end.

public final class BraceExpansion {
	// Time Complexity: O(n + W * n * log W), n the length of s and W the number of words: parsing
	// is one pass, each of the W words is at most n letters, and sorting them compares words
	// Space Complexity: O(n) besides the output, the parts and the recursion depth

	private List<String[]> parts;
	private List<String> combinations;

	public String[] expand(String s) {
		this.parts = new ArrayList<>();
		parse(s);
		this.combinations = new ArrayList<>();
		dfs(0, new ArrayList<>());
		return combinations.stream().sorted().toArray(String[]::new); // the answer must be sorted
	}

	private void dfs(int index, List<String> list) {
		if (index == parts.size()) { // an option chosen for every part
			combinations.add(String.join("", list));
			return;
		}
		for (String s : parts.get(index)) { // the choices for this part
			list.add(s); // choose
			dfs(index + 1, list);
			list.remove(list.size() - 1); // undo
		}
	}

	private void parse(String s) {
		while (!s.equals("")) {
			if (s.charAt(0) == '{') { // a group: its options, split at the commas
				int end = s.indexOf("}");
				parts.add(s.substring(1, end).split(","));
				s = s.substring(end + 1);
			} else { // plain letters up to the next group: one option
				int end = s.indexOf("{");
				if (end != -1) {
					parts.add(new String[] { s.substring(0, end) });
					s = s.substring(end);
				} else {
					parts.add(new String[] { s });
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		BraceExpansion be = new BraceExpansion();
		System.out.println(Arrays.toString(be.expand("a{b,c}"))); // [ab, ac]
		System.out.println(Arrays.toString(be.expand("k{a,b}{n,m}"))); // [kam, kan, kbm, kbn]
		System.out.println(Arrays.toString(be.expand("{a,b}c{d,e}f"))); // [acdf, acef, bcdf, bcef]
	}
}
