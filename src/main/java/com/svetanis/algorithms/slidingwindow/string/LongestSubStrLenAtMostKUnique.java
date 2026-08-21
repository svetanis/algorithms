package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 340. Longest Substring with At Most K Distinct Characters

// Given a string, find the length of the longest 
// substring with no more than K unique characters 

public final class LongestSubStrLenAtMostKUnique {
	// Time complexity: O(n)

	public static int kUniqueMaxLen(String s, int k) {
		int n = s.length();
		int left = 0; // current start
		int max = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			char curr = s.charAt(right);
			map.put(curr, map.getOrDefault(curr, 0) + 1);
			// shrink the sliding window, until k
			// distinct chars left in frequency map
			while (map.size() > k) {
				char front = s.charAt(left);
				map.put(front, map.get(front) - 1);
				if (map.get(front) == 0) {
					map.remove(front);
				}
				left++; // shrink the window
			}
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	// the same window with a count array instead of a map.
	// an array has no size(), and size() IS the validity test here,
	// so the distinct count has to be maintained by hand: a bucket
	// crossing 0 -> 1 is a new character, 1 -> 0 is one leaving.
	// 128 buckets, indexed by the char itself, so no assumption is
	// made about the alphabet.
	// Time complexity: O(n)
	// Aux Space: O(1) -- 128 ints regardless of input

	public static int kUniqueMaxLenCountArray(String s, int k) {
		int left = 0;
		int max = 0;
		int distinct = 0;
		int[] count = new int[128];
		for (int right = 0; right < s.length(); right++) {
			if (count[s.charAt(right)]++ == 0) {
				distinct++;
			}
			while (distinct > k) {
				if (--count[s.charAt(left)] == 0) {
					distinct--;
				}
				left++;
			}
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		String s1 = "aabbcc";
		System.out.println(kUniqueMaxLen(s1, 1));
		System.out.println(kUniqueMaxLen(s1, 2));
		System.out.println(kUniqueMaxLen(s1, 3));

		String s2 = "aaabbb";
		System.out.println(kUniqueMaxLen(s2, 3));

		String s3 = "aabacbebebe";
		System.out.println(kUniqueMaxLen(s3, 3));

		String s4 = "araaci";
		System.out.println(kUniqueMaxLen(s4, 2));

		String s5 = "araaci";
		System.out.println(kUniqueMaxLen(s5, 1));

		String s6 = "cbbebi";
		System.out.println(kUniqueMaxLen(s6, 3));

		String s7 = "aabcabb";
		System.out.println(kUniqueMaxLen(s7, 2)); // 3

	}
}
