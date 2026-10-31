package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 291. Word Pattern II
//
// Input: a pattern p of letters and a string s. Returns whether s can be cut into non-empty
// pieces, one per letter of p in order, so that the same letter always has the same piece and
// different letters have different pieces. On LeetCode: 1 <= p.length, s.length <= 20, both made
// of lowercase letters.
//
// The one idea: the only free choice is where each new letter's piece ends. Walk the pattern from
// the left with a position in s. One call is a letter of p (index) and the place in s where its
// piece starts. If the letter already has a piece, that piece must be exactly what s shows here;
// if it has none, try every length for its piece, except a piece another letter already owns
// (the second half of "different letters, different pieces"). map holds letter -> piece, set the
// pieces handed out; both are undone when a try fails. The cut is also dropped as soon as the
// letters left need more characters than s has left, one character each at least.
//
// Time: O(n^m) paths, each node O(n^2) -- a letter that is new tries up to n lengths, so at most
// n^m paths; every node scans up to n ends and builds a piece of up to n characters at each.
// Space: O(m + n) -- the recursion is m deep; map and set hold at most m pieces, which are
// disjoint parts of s, so n characters in all.

public final class WordPatternBacktracking {

	public static boolean wordPattern(String p, String s) {
		Set<String> set = new HashSet<>();
		Map<Character, String> map = new HashMap<>();
		return dfs(p, 0, s, 0, set, map);
	}

	private static boolean dfs(String p, int index, String s, int start,
			Set<String> set, Map<Character, String> map) {
		int m = p.length();
		int n = s.length();
		if (index == m && start == n) { // BASE: pattern and string used up together
			return true;
		}
		if (start == n || index == m || n - start < m - index) { // CUT: one side ran out, or too few characters left
			return false;
		}
		char c = p.charAt(index);
		for (int end = start + 1; end <= n; end++) { // TRY: the piece for c is s[start, end)
			String word = s.substring(start, end);
			if (map.getOrDefault(c, "").equals(word)) { // REUSE: c already owns exactly this piece
				if (dfs(p, index + 1, s, end, set, map)) {
					return true;
				}
			} else if (!map.containsKey(c) && !set.contains(word)) { // NEW: c has no piece, and nobody owns this one
				map.put(c, word); // ASSIGN
				set.add(word);
				if (dfs(p, index + 1, s, end, set, map)) {
					return true;
				}
				map.remove(c); // UNDO
				set.remove(word);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(wordPattern("aba", "catdogcat")); // true

		System.out.println(wordPattern("abab", "redblueredblue")); // true
		System.out.println(wordPattern("aabb", "xyzabcxzyabc")); // false
	}
}
