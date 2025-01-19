package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 3. Longest Substring Without Repeating Characters

// Given a string, find the length of the longest 
// substring, which has all distinct characters.

public final class LongestDistinctSubStrLenOptimized {
	// Time complexity: O(n)

	public static int ldl(String str) {
		int max = 0;
		int left = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < str.length(); right++) {
			char c = str.charAt(right);
			// if map already contains the char 'c'
			// shrink the window from the beginning
			// so only one occurrence of 'c' remains
			if (map.containsKey(c)) {
				left = Math.max(left, map.get(c) + 1);
			}
			// insert 'c' into the map
			map.put(c, right);
			// max length so far
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(ldl("abcadbef"));
		System.out.println(ldl("abac"));
		System.out.println(ldl("aaaaa"));
		System.out.println(ldl("abccdefgh"));
		System.out.println();
		System.out.println(ldl("aabccbb")); // 3 - abc
		System.out.println(ldl("abbbb")); // 2 - ab
		System.out.println(ldl("abccde")); // 3 - abc & cde
		System.out.println();
		System.out.println(ldl("abcabcbb")); // 3 - abc
		System.out.println(ldl("bbbbb")); // 1 - b
		System.out.println(ldl("pwwkew")); // 3 - wke
	}
}
