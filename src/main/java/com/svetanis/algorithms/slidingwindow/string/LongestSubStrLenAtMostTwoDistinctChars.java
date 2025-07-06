package com.svetanis.algorithms.slidingwindow.string;

import static java.lang.Math.max;

import java.util.HashMap;
import java.util.Map;

// 159. Longest Substring with At Most Two Distinct Characters

// Given a string, find the length of the longest 
// substring that contains at most two distinct char 

public final class LongestSubStrLenAtMostTwoDistinctChars {
	// Time complexity: O(n)

	public static int maxLen(String str) {
		int n = str.length();
		int left = 0; // current start
		int max = 0; // max window size
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			char next = str.charAt(right);
			int freq = map.getOrDefault(next, 0) + 1;
			map.put(next, freq);
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
			max = max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxLen("aabbcca")); // 4
	}
}
