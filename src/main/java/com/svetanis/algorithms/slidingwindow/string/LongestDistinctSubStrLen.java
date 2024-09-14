package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Math.max;

import java.util.Set;

// Given a string, find the length of the longest 
// substring, which has all distinct characters.

public final class LongestDistinctSubStrLen {

	public static int ldl(String str) {
		// Time complexity: O(n)
		// Space complexity: O(1)

		int max = 0;
		int left = 0;
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
	}
}
