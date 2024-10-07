package com.svetanis.algorithms.string;

import static com.svetanis.java.base.utils.Maps.freqMap;

import java.util.Map;

// 383. Ransom Note

// given two strings ransomNote and magazine, 
// return true if ransomNote can be constructed
// by using the letters from magazine and false otherwise

public final class RansomNote {

	public static boolean canConstruct(String s, String p) {
		Map<Character, Integer> map = freqMap(p);
		for (char c : s.toCharArray()) {
			if (!map.containsKey(c) || map.get(c) <= 0) {
				return false;
			}
			map.put(c, map.get(c) - 1);
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(canConstruct("a", "b")); // false
		System.out.println(canConstruct("aa", "ab")); // false
		System.out.println(canConstruct("aa", "aab")); // true
	}
}
