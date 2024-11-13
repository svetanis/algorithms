package com.svetanis.algorithms.dp.common;

import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Maps.checkedGet;
import static com.svetanis.java.base.collect.Maps.checkedPut;

import java.util.Map;

// 115. Distinct Subsequences

// Given a string and a pattern, 
// write a method to count the number of times 
// the pattern appears in the string as a subsequence.

public final class SubSeqPatternMatchingTopDown {
	// Time Complexity: O(n * m)

	public static int count(String str, String pat) {
		int n = str.length();
		int m = pat.length();
		Map<String, Integer> map = newHashMap();
		return count(map, str, pat, n, m);
	}

	private static int count(Map<String, Integer> map, String str, String pat, int n, int m) {
		if ((n == 0 && m == 0) || m == 0) {
			return 1;
		}
		if (n == 0) {
			return 0;
		}
		String key = n + "_" + m;
		if (!map.containsKey(key)) {
			int incl = 0;
			if (str.charAt(n - 1) == pat.charAt(m - 1)) {
				incl = count(map, str, pat, n - 1, m - 1);
			}
			int excl = count(map, str, pat, n - 1, m);
			checkedPut(map, key, incl + excl);
		}
		return checkedGet(map, key);
	}

	public static void main(String[] args) {
		System.out.println(count("baxmx", "ax"));
		System.out.println(count("tomorrow", "tor"));
		System.out.println(count("rabbbit", "rabbit")); // 3
		System.out.println(count("babgbag", "bag")); // 5
	}
}
