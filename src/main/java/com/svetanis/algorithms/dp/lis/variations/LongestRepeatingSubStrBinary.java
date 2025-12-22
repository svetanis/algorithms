package com.svetanis.algorithms.dp.lis.variations;

import java.util.HashMap;
import java.util.Map;

// 1062. Longest Repeating Substring

public final class LongestRepeatingSubStrBinary {

	public static int lrs(String s) {
		int low = 0;
		int high = s.length() - 1;
		while (low < high) {
			int mid = (low + high + 1) / 2;
			if (containsRepeatingSubstr(s, mid)) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	private static boolean containsRepeatingSubstr(String s, int len) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i <= s.length() - len; i++) {
			String ss = s.substring(i, i + len);
			int count = 1 + map.getOrDefault(ss, 0);
			if (count > 1) {
				return true;
			}
			map.put(ss, count);
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(lrs("abab")); // 2
		System.out.println(lrs("aabba")); // 1
	}
}
