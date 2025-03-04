package com.svetanis.algorithms.slidingwindow.string;

import static java.lang.Math.max;

import java.util.HashMap;
import java.util.Map;

// 340. Longest Substring with At Most K Distinct Characters

// Given a string, find the length of the longest 
// substring with no more than K unique characters 

public final class LongestSubStrLenKUnique {
	// Time complexity: O(n)

	private static final int MAX = 256;

	public static int kUniqueMaxLen(String s, int k) {
		int n = s.length();
		if (countUnique(s) < k) {
			return 0;
		}

		int left = 0; // current start
		int max = 0; // max window size
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
			// max length so far
			if (map.size() == k) {
				max = max(max, right - left + 1);
			}
		}
		return max;
	}

	private static int countUnique(String s) {
		int k = 0;
		int[] count = new int[MAX];
		// count num of unique chars
		for (char c : s.toCharArray()) {
			if (count[c] == 0) {
				k++;
			}
			count[c]++;
		}
		return k;
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
