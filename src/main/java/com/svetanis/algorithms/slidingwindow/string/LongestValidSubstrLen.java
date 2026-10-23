package com.svetanis.algorithms.slidingwindow.string;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 2781. Length of the Longest Valid Substring

// given a string s and an array of strings forbidden
// a string is called valid if none of its substrings
// are present in forbidden
// return the length of the longest valid substring
// a substr is a contiguous sequence of chars in a string
// possibly empty
// The method only looks for forbidden words of at most 10 characters
// (the limit LeetCode states); a longer forbidden word is never found.

// The one idea: the window left..right is kept valid. A new character at
// right can only create forbidden words that END at right, so only the
// suffixes of the window ending there need checking -- at most 10 of them,
// none starting before left. Checking from the shortest suffix up, the
// first forbidden one found starts furthest right; every window that still
// holds its first character holds the whole word, so left jumps just past
// that character. Then left..right is the longest valid window ending at
// right.

// Siblings: none -- no other file in the repo solves 2781.
//   LongestDistinctSubStrLenOptimized (LeetCode 3) jumps left the same way.

// Time: O(n * L * L) with L = 10 -- at most L suffixes per character, each
//   copied and hashed in up to L steps; plus one pass over forbidden to build
//   the set.
// Space: O(F) -- the set of forbidden words, F being their total length.

public final class LongestValidSubstrLen {

	public static int longestValidSubstrLen(String s, List<String> forbidden) {
		int max = 0;
		int left = 0;
		Set<String> set = new HashSet<>(forbidden);
		for (int right = 0; right < s.length(); right++) {
			// suffixes index..right, shortest first: at most 10 characters, none before left
			for (int index = right; index > Math.max(right - 10, left - 1); index--) {
				String substr = s.substring(index, right + 1);
				if (set.contains(substr)) {
					left = index + 1; // JUMP: past the forbidden word's first character
					break; // STOP: a longer forbidden suffix starts earlier, so this jump already cuts it off
				}
			}
			max = Math.max(max, right - left + 1); // MEASURE: no forbidden word in left..right
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(longestValidSubstrLen("cbaaaabc", asList("aaa", "cb"))); // 4
		System.out.println(longestValidSubstrLen("leetcode", asList("de", "le", "e"))); // 4
	}
}
