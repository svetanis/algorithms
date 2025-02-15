package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 1876. Substrings of Size Three with Distinct Characters

public final class DistinctSubstrSize3 {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int count(String s) {
		int left = 0;
		int count = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			if (map.containsKey(c)) {
				left = Math.max(left, map.get(c) + 1);
			}
			if (right - left + 1 == 3) {
				count++;
				left++;
			}
			map.put(c, right);
		}
		return count;
	}

	public static void main(String[] args) {
		String s1 = "xyzzaz";
		System.out.println(count(s1)); // 1

		String s2 = "aababcabc";
		System.out.println(count(s2)); // 4

		String s3 = "owuxoelszb";
		System.out.println(count(s3)); // 8

	}
}
