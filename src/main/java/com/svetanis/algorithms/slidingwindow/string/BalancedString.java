package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 1234. Replace the Substring for Balanced String

public final class BalancedString {
	// Time Complexity: O(n)
	// Space Complexity: O(c)

	// QWER
	public static int balancedStr(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.merge(c, 1, Integer::sum);
		}
		int n = s.length();
		int target = n / 4;
		if (notExceed(map, target)) {
			return 0;
		}
		int min = n;
		int left = 0;
		for (int right = 0; right < n; right++) {
			char c = s.charAt(right);
			map.merge(c, -1, Integer::sum);
			while (left <= right && notExceed(map, target)) {
				min = Math.min(min, right - left + 1);
				map.merge(s.charAt(left), 1, Integer::sum);
				left++;
			}
		}
		return min;
	}

	private static boolean notExceed(Map<Character, Integer> map, int target) {
		for (char c : map.keySet()) {
			if (map.get(c) > target) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(balancedStr("QWER")); // 0
		System.out.println(balancedStr("QQWE")); // 1
		System.out.println(balancedStr("QQQW")); // 2
		System.out.println(balancedStr("QQQWWWEEEERRRR")); // 2
	}
}
