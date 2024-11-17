package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 2981. Find Longest Special Substring That Occurs Thrice I
// 2982. Find Longest Special Substring That Occurs Thrice II

public final class LongestSpecialSubStrOccursThrice {
	// Time complexity: O(n log n)

	public static int maxLen(String s) {
		int left = 0;
		int right = s.length();
		while (left < right) {
			int mid = (left + right + 1) / 2;
			if (valid(s, mid)) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		return left == 0 ? -1 : left;
	}

	private static boolean valid(String s, int target) {
		int n = s.length();
		Map<Character, Integer> map = new HashMap<>();
		for (int start = 0; start < n;) {
			char curr = s.charAt(start);
			int end = start + 1;
			while (end < n && s.charAt(end) == curr) {
				end++;
			}
			int freq = Math.max(0, end - start - target + 1);
			map.put(curr, map.getOrDefault(curr, 0) + freq);
			if (map.get(curr) >= 3) {
				return true;
			}
			start = end;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(maxLen("aaaa")); // 2 -> aa
		System.out.println(maxLen("abcdef")); // -1
		System.out.println(maxLen("abcaba")); // 1 -> a
	}
}
