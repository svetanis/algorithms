package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashSet;
import java.util.Set;

// Longest Substring Without Repeating Characters, returning the substring
// (LeetCode 3 asks only for its length)
//
// Input: a string.
// Return: the longest substring in which no character appears twice; when several
// have that length, the one that starts first. "" for an empty string.
//
// The one idea: the set holds exactly the characters of the window left..right.
// A new character can break the window only by repeating one already in it, and
// the window is repaired only by losing characters from the left until that
// earlier copy is gone. After that, left..right is the longest window without a
// repeat that ends at right. The best substring is replaced only when a window is
// strictly longer, so the first of the longest is the one kept.
//
// Siblings, all LeetCode 3:
//   LongestDistinctSubStrLen -- the same set window, returning the length.
//   LongestDistinctSubStrLenOptimized -- stores each character's last position
//     and jumps left past the repeat in one step; returns the length.
//
// Time: O(n + L*L) -- each character enters and leaves the set at most once; the
//   substring is copied only when the best length grows, at most L times of at
//   most L characters, L being the answer's length (no more than the number of
//   different characters).
// Space: O(L) -- the set and the best substring.

public final class LongestDistinctSubStr {

	public static String lds(String s) {
		int left = 0;
		String substr = "";
		Set<Character> set = new HashSet<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			while (set.contains(c)) { // SHRINK: while c's earlier copy is still in the window
				set.remove(s.charAt(left));
				left++;
			}
			set.add(c); // ADD
			if (right - left + 1 > substr.length()) { // MEASURE: strictly longer, so the first longest stays
				substr = s.substring(left, right + 1);
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
