package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 187. Repeated DNA Sequences
//
// Input: a DNA string s of the letters A, C, G, T. Returns every 10-letter substring that occurs
// more than once in s, each listed once, in the order they reach their second occurrence.
// On LeetCode: 1 <= s.length <= 10^5.
//
// The one idea: there are only n - 9 substrings of length 10, so cut each one out and count it
// in a map from substring to number of times seen. A substring is added to the answer at the
// moment its count reaches exactly 2, so a third or fourth occurrence adds nothing.
//
// Time: O(n) -- n - 9 windows, each a 10-letter copy and a hash of 10 letters: 10 * n.
// Space: O(n) -- up to n - 9 distinct windows of 10 letters in the map.

public final class RepeatedDNASequence {

	public static List<String> dna(String s) {
		List<String> list = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>(); // map: window -> how many times seen
		for (int i = 0; i <= s.length() - 10; i++) { // WALK: every window of 10 letters
			String ss = s.substring(i, i + 10);
			map.put(ss, map.getOrDefault(ss, 0) + 1); // COUNT: one more sighting
			if (map.get(ss) == 2) { // RECORD: exactly the second sighting, so each window once
				list.add(ss);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(dna("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")); // [AAAAACCCCC, CCCCCAAAAA]
		System.out.println(dna("AAAAAAAAAAAAA")); // [AAAAAAAAAA]
	}
}
