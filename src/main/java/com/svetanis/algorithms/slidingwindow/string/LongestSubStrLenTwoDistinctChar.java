package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;

import java.util.Map;

// Given a string, find the length of the longest 
// substring that contains exactly two distinct char 

public final class LongestSubStrLenTwoDistinctChar {
	// Time complexity: O(n)

	public static int maxLen(String str) {
		int n = str.length();
		int max = 0; // max window size
		int left = 0; // current start
		Map<Character, Integer> map = newHashMap();
		for (int right = 0; right < n; right++) {
			char curr = str.charAt(right);
			map.put(curr, map.getOrDefault(curr, 0) + 1);
			// shrink the sliding window, until k
			// distinct chars left in frequency map
			while (map.size() > 2) {
				char front = str.charAt(left);
				map.put(front, map.get(front) - 1);
				if (map.get(front) == 0) {
					map.remove(front);
				}
				left++; // shrink the window
			}
			if (map.size() == 2) {
				max = max(max, right - left + 1);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxLen("ababababa"));
		System.out.println(maxLen("e"));
		System.out.println(maxLen("baabcbab"));
		System.out.println(maxLen("aabbcca"));
	}
}
