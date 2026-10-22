package com.svetanis.algorithms.slidingwindow.string.anagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 438. Find All Anagrams in a String
//
// A string s and a pattern p. Returns, in increasing order, the start index of every substring
// of s that is a rearrangement of p: the same letters, each as many times.
//
// Such a substring has exactly p.length() letters, so the window has a fixed size. The map says
// how many more of each pattern letter the window still needs, and goes negative when the
// window holds a spare. matched counts the distinct pattern letters whose need is 0 or less.
// When matched reaches map.size(), no pattern letter is missing; and since the window is no
// longer than the pattern, it cannot hold anything extra either -- it is a rearrangement.
//
// Siblings:
//   StringAnagramsSubmit (same problem) two int[26] count tables, the window seeded one letter
//                        short and compared with Arrays.equals at every step; lowercase only
//   ContainsAnagram      (LC 567) the same window, stops at the first match
//   CountAnagrams        the same window, counts the matches

public final class StringAnagrams {
	// Time complexity: O(n + m), each letter of s enters the window once and leaves once;
	// building the map reads p once
	// Space complexity: O(m), one map entry per distinct letter of p, plus the returned list

	public static List<Integer> anagrams(String s, String p) {
		int n = s.length();
		int left = 0;
		int matched = 0;
		List<Integer> list = new ArrayList<>();
		Map<Character, Integer> map = frequencies(p);
		for (int right = 0; right < n; right++) {
			char end = s.charAt(right);
			if (map.containsKey(end)) {
				map.put(end, map.get(end) - 1); // IN: one fewer of end still needed
				if (map.get(end) == 0) {
					matched++; // COUNT: end's need is met
				}
			}
			if (matched == map.size()) {
				list.add(left); // RECORD: the window's start, before it moves
			}
			if (right >= p.length() - 1) {
				char start = s.charAt(left++); // OUT: the window is full, its oldest letter leaves
				if (map.containsKey(start)) {
					if (map.get(start) == 0) {
						matched--; // start's need was exactly met, and now it is short by one
					}
					map.put(start, map.get(start) + 1);
				}
			}
		}
		return list;
	}

	private static Map<Character, Integer> frequencies(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		return map;
	}

	private static void print(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (int value : list) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		String[][] pairs = {
				{ "ppqp", "pq" }, // 1, 2
				{ "abbcabc", "abc" }, // 2, 3, 4
				{ "cbaebabacd", "abc" }, // 0, 6
				{ "abab", "ab" }, // 0, 1, 2
				{ "BACDGABCDA", "ABCD" }, // 0, 5, 6
				{ "AAABABAA", "AABA" } // 0, 1, 4
		};
		for (String[] pair : pairs) {
			print(anagrams(pair[0], pair[1]));
		}
	}

}
