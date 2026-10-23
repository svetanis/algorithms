package com.svetanis.algorithms.slidingwindow.string;

import static java.lang.Math.max;

import java.util.HashMap;
import java.util.Map;

// 159. Longest Substring with At Most Two Distinct Characters

// Given a string, find the length of the longest
// substring that contains at most two distinct char

// The one idea: adding a character can break the window (a third
// different character) and never repair it, so a broken window loses
// characters from the left until only two different ones remain. Then
// left..right is the longest allowed window ending at right.

// Siblings:
//   LongestSubStrLenAtMostKUnique (340) -- this loop for any k.
//   MaxFruitIntoBaskets, MaxFruitCountOfTwoTypes (904) -- this same loop,
//     worded as fruit and baskets, over an int[] and a char[].
//   LongestSubStrLenTwoDistinctChar -- exactly two, measured only when the
//     window holds two.

// Time: O(n) -- each character enters the window once and leaves at most once.
// Space: O(1) -- the map holds at most three characters.

public final class LongestSubStrLenAtMostTwoDistinctChars {

	public static int maxLen(String str) {
		int n = str.length();
		int left = 0; // current start
		int max = 0; // max window size
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			char next = str.charAt(right);
			int freq = map.getOrDefault(next, 0) + 1;
			map.put(next, freq); // ADD
			while (map.size() > 2) { // SHRINK: while three different characters
				char front = str.charAt(left);
				map.put(front, map.get(front) - 1);
				if (map.get(front) == 0) {
					map.remove(front); // REMOVE: count 0, so map.size() stops counting it
				}
				left++;
			}
			max = max(max, right - left + 1); // MEASURE: allowed again
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxLen("aabbcca")); // 4
	}
}
