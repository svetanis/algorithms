package com.svetanis.algorithms.string;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import com.google.common.base.Optional;

// given a string s, find the first 
// non-repeating char in it and 
// return its index. 

public final class FirstUniqueChar {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static Optional<Integer> firstUnique(String s) {
		Map<Character, Integer> map = newHashMap();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.get(c) == 1) {
				return of(i);
			}
		}
		return absent();
	}

	public static void main(String[] args) {
		System.out.println(firstUnique("leetcode")); // 0
		System.out.println(firstUnique("loveleetcode")); // 2
		System.out.println(firstUnique("aabb")); // -1
	}
}
