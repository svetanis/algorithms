package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 1297. Maximum Number of Occurrences of a Substring

public final class MaxNumOfOccurrencesOfSubStr {
	// Time Complexity: O(n * minSize)
	// Space Complexity: O(n)

	public static int maxFrequency(String s, int unique, int min, int max) {
		int n = s.length();
		int mostFrequent = 0;
		Map<String, Integer> map = new HashMap<>();
		for (int index = 0; index <= n - min; index++) {
			String ss = s.substring(index, index + min);
			Set<Character> set = uniqueChars(ss);
			if (set.size() > unique) {
				continue;
			}
			if (ss.length() <= max) {
				int freq = map.getOrDefault(ss, 0) + 1;
				map.put(ss, freq);
				mostFrequent = Math.max(mostFrequent, freq);
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