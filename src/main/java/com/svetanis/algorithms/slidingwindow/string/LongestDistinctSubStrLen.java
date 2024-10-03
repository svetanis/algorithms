package com.svetanis.algorithms.slidingwindow.string;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Given a string, find the length of the longest 
// substring, which has all distinct characters.

public final class LongestDistinctSubStrLen {
	// Time complexity: O(n)
	// Space complexity: O(1)

	public static int ldl(String s) {
		int left = 0;
		int max = MIN_VALUE;
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			map.put(c, map.getOrDefault(c, 0) + 1);
			while (map.get(c) > 1) {
				char first = s.charAt(left);
				map.put(first, map.get(first) - 1);
				left++;
			}
			// max length so far
			max = max(max, right - left + 1);
		}
		return max;
	}

	public static int ldlSimple(String s) {
		int left = 0;
		int max = MIN_VALUE;
		Set<Character> set = new HashSet<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			// if set already contains the char 'c'
			// slide the left pointer to the right
			// until the duplicate is removed
			while (set.contains(c)) {
				set.remove(s.charAt(left));
				left++;
			}
			// insert 'c' into the set
			set.add(c);
			// max length so far
			max = max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(ldl("abcadbef"));
		System.out.println(ldl("abac"));
		System.out.println(ldl("aaaaa"));
		System.out.println(ldl("abccdefgh"));
		System.out.println();
		System.out.println(ldl("aabccbb")); // 3 - abc
		System.out.println(ldl("abbbb")); // 2 - ab
		System.out.println(ldl("abccde")); // 3 - abc & cde
		System.out.println();
		System.out.println(ldl("abcabcbb")); // 3 - abc
		System.out.println(ldl("bbbbb")); // 1 - b
		System.out.println(ldl("pwwkew")); // 3 - wke
		System.out.println();
		System.out.println(ldl("abccabcabcc")); // 3
		System.out.println(ldl("aaaabaaa")); // 2
	}
}
