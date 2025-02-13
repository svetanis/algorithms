package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashSet;
import java.util.Set;

// 3. Longest Substring Without Repeating Characters

// Given a string, find the length of the longest 
// substring, which has all distinct characters.

public final class LongestDistinctSubStrLen {
	// Time complexity: O(n)

	public static int ldl(String s) {
		int max = 0;
		int left = 0;
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
			max = Math.max(max, right - left + 1);
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
