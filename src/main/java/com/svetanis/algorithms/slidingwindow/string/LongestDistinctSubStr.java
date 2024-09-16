package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

// Given a string, find the length of the longest 
// substring, which has all distinct characters.

public final class LongestDistinctSubStr {

	public static String lds(String str) {
		// Time complexity: O(n)
		// Space complexity: O(1)

		int left = 0;
		String substr = "";
		Set<Character> set = newHashSet();
		for (int right = 0; right < str.length(); right++) {
			char c = str.charAt(right);
			// if set already contains the char 'c'
			// slide the left pointer to the right
			// until the duplicate is removed
			while (set.contains(c)) {
				set.remove(str.charAt(left));
				left++;
			}
			// insert 'c' into the set
			set.add(c);
			// max length so far
			if (right - left + 1 > substr.length()) {
				substr = str.substring(left, right + 1).trim();
			}
		}
		return substr;
	}

	public static void main(String[] args) {
		System.out.println(lds("abcadbef"));
		System.out.println(lds("abac"));
		System.out.println(lds("aaaaa"));
		System.out.println(lds("abccdefgh"));
		System.out.println();
		System.out.println(lds("aabccbb")); // 3 - abc
		System.out.println(lds("abbbb")); // 2 - ab
		System.out.println(lds("abccde")); // 3 - abc & cde
		System.out.println();
		System.out.println(lds("abcabcbb")); // 3 - abc
		System.out.println(lds("bbbbb")); // 1 - b
		System.out.println(lds("pwwkew")); // 3 - wke
	}
}