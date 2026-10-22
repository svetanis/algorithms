package com.svetanis.algorithms.slidingwindow.string.anagram;

import java.util.HashMap;
import java.util.Map;

// Count occurrences of anagrams (GeeksforGeeks; no LeetCode number)
//
// A string str and a pattern pat. Returns how many substrings of str are rearrangements of pat:
// the same letters, each as many times. Overlapping substrings each count.
//
// Such a substring has exactly pat.length() letters, so the window has a fixed size. The map
// says how many more of each pattern letter the window still needs, and goes negative when the
// window holds a spare. matched counts the distinct pattern letters whose need is 0 or less.
// When matched reaches map.size(), no pattern letter is missing; and since the window is no
// longer than the pattern, it cannot hold anything extra either -- it is a rearrangement.
//
// Siblings, the same window:
//   ContainsAnagram      (LC 567) stops at the first such substring and returns true
//   StringAnagrams       (LC 438) lists the start of every such substring; its size is this count
//   StringAnagramsSubmit (LC 438) two int[26] count tables compared with Arrays.equals

public final class CountAnagrams {

	public static int count(String str, String pat) {
		// Time complexity: O(n + m), each letter of str enters the window once and leaves once;
		// building the map reads pat once
		// Space complexity: O(m), one map entry per distinct letter of pat

		int n = str.length();
		int left = 0;
		int count = 0;
		int matched = 0;
		Map<Character, Integer> map = freqMap(pat.toCharArray());

		for (int right = 0; right < n; right++) {
			char end = str.charAt(right);
			if (map.containsKey(end)) {
				map.put(end, map.get(end) - 1); // IN: one fewer of end still needed
				if (map.get(end) == 0) {
					matched++; // COUNT: end's need is met
				}
			}

			if (matched == map.size()) {
				count++; // RECORD: every pattern letter met in a window of pat's length
			}

			if (right >= pat.length() - 1) {
				char start = str.charAt(left++); // OUT: the window is full, its oldest letter leaves
				if (map.containsKey(start)) {
					if (map.get(start) == 0) {
						matched--; // start's need was exactly met, and now it is short by one
					}
					map.put(start, map.get(start) + 1);
				}
			}
		}
		return count;
	}

	private static Map<Character, Integer> freqMap(char[] chars) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : chars) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		String[][] pairs = {
				{ "forxxorfxdorf", "for" }, // 3
				{ "aabaabaa", "aaba" } // 4
		};
		for (String[] pair : pairs) {
			System.out.println(count(pair[0], pair[1]));
		}
	}
}
