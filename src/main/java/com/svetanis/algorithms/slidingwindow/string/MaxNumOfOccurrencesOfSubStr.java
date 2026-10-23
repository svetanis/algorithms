package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 1297. Maximum Number of Occurrences of a Substring
//
// Input: a string s and three numbers: unique (the most different letters a
// substring may hold), min and max (the allowed substring lengths), with
// 1 <= min <= max.
// Return: the largest number of times any one allowed substring occurs in s,
// counting occurrences that overlap ("aaaa" holds "aaa" twice); 0 when no
// substring is allowed.
//
// The one idea: only substrings of length min need counting. Every occurrence of
// a longer allowed substring begins with an occurrence of its first min letters,
// and that shorter piece is allowed too -- it holds no more different letters --
// so it occurs at least as often. So slide a window of exactly min letters, skip
// the ones with too many different letters, and count the rest.
//
// Siblings: none -- no other file in the repo solves 1297.
//
// Time: O(n * min) -- n - min + 1 windows, each copied, scanned and hashed in
//   min steps.
// Space: O(n * min) -- the map can hold every window as a separate string.

public final class MaxNumOfOccurrencesOfSubStr {

	public static int maxFrequency(String s, int unique, int min, int max) {
		int n = s.length();
		int mostFrequent = 0;
		Map<String, Integer> map = new HashMap<>();
		for (int index = 0; index <= n - min; index++) {
			String ss = s.substring(index, index + min); // BUILD: the window of min letters at index
			Set<Character> set = uniqueChars(ss);
			if (set.size() > unique) {
				continue; // SKIP: too many different letters
			}
			if (ss.length() <= max) { // always true, since min <= max
				int freq = map.getOrDefault(ss, 0) + 1; // COUNT: one more occurrence of ss
				map.put(ss, freq);
				mostFrequent = Math.max(mostFrequent, freq); // RECORD
			}
		}
		return mostFrequent;
	}

	private static Set<Character> uniqueChars(String s) {
		Set<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			set.add(c);
		}
		return set;
	}

	public static void main(String args[]) {
		System.out.println(maxFrequency("aababcaab", 2, 3, 4)); // 2
		System.out.println(maxFrequency("aaaa", 1, 3, 3)); // 2
	}
}