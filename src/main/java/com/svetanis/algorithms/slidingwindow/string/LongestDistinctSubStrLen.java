package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashSet;
import java.util.Set;

// 3. Longest Substring Without Repeating Characters
//
// Input: a string.
// Return: the length of the longest substring in which no character appears
// twice; 0 for an empty string.
//
// The one idea: the set holds exactly the characters of the window left..right.
// A new character can break the window only by repeating one already in it, and
// the window is repaired only by losing characters from the left until that
// earlier copy is gone. After that, left..right is the longest window without a
// repeat that ends at right, so measuring it at every right finds the answer.
//
// Siblings:
//   LongestDistinctSubStrLenOptimized -- stores each character's last position
//     instead of a set, and jumps left past the repeat in one step.
//   LongestDistinctSubStr -- this same set window, returning the substring.
//
// Time: O(n) -- each character enters the set once and leaves at most once.
// Space: O(d) -- the set holds at most d characters, d being the number of
//   different characters in the string.

public final class LongestDistinctSubStrLen {

	public static int ldl(String s) {
		int max = 0;
		int left = 0;
		Set<Character> set = new HashSet<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			while (set.contains(c)) { // SHRINK: while c's earlier copy is still in the window
				set.remove(s.charAt(left));
				left++;
			}
			set.add(c); // ADD
			max = Math.max(max, right - left + 1); // MEASURE: no repeat in left..right
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
