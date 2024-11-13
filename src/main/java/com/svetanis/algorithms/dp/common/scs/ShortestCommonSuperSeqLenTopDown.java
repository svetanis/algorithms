package com.svetanis.algorithms.dp.common.scs;

import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Maps.checkedGet;
import static java.lang.Math.min;

import java.util.Map;

// 1092. Shortest Common Supersequence

// Given two sequences ‘s1’ and ‘s2’, 
// write a method to find the len of 
// the shortest sequence which has 
// ‘s1’ and ‘s2’ as subsequences.

public final class ShortestCommonSuperSeqLenTopDown {

	public static int scs(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		Map<String, Integer> map = newHashMap();
		return scs(map, s1, s2, n, m);
	}

	private static int scs(Map<String, Integer> map, String s1, String s2, int n, int m) {
		if (n == 0 || m == 0) {
			return n + m;
		}
		String key = n + "_" + m;
		if (!map.containsKey(key)) {
			if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
				int min = 1 + scs(map, s1, s2, n - 1, m - 1);
				map.put(key, min);
			} else {
				int first = scs(map, s1, s2, n - 1, m);
				int second = scs(map, s1, s2, n, m - 1);
				int min = 1 + min(first, second);
				map.put(key, min);
			}
		}
		return checkedGet(map, key);
	}

	public static void main(String[] args) {
		System.out.println(scs("geek", "eke"));
		System.out.println(scs("AGGTAB", "GXTXAYB"));
		System.out.println(scs("abac", "cab")); // 5
		System.out.println(scs("aaaaaaaa", "aaaaaaaa")); // 8
	}
}
