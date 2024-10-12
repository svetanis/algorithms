package com.svetanis.algorithms.string;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import com.google.common.base.Optional;

// 389. Find the Difference
// given two strings s and t
// string t is generated by 
// random shuffling string s
// and then add one more letter.
// return the letter that was 
// added to t

public final class FindDifference {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static Optional<String> difference(String s, String t) {
		Map<Character, Integer> map = newHashMap();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			map.put(c, map.getOrDefault(c, 0) - 1);
			if (map.get(c) == -1) {
				return of(c + "");
			}
		}

		return absent();
	}

	public static void main(String[] args) {
		System.out.println(difference("abcd", "abcde")); // e
		System.out.println(difference("", "y")); // y
	}
}