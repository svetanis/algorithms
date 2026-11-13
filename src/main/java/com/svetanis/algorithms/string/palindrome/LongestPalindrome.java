package com.svetanis.algorithms.string.palindrome;

import java.util.HashMap;
import java.util.Map;

// 2131. Longest Palindrome by Concatenating Two Letter Words
//
// Input: words of exactly two lowercase letters, repeats allowed. On LeetCode:
// 1 <= words.length <= 10^5.
// Return: the length of the longest palindrome made by joining some of the words, each
// used at most once, in any order; 0 if there is none.
//
// The one idea: a two-letter word can only sit opposite its own reverse. "lc" on the
// left needs "cl" in the mirrored spot on the right, so min(count of lc, count of cl)
// such pairs fit, 4 letters each. A word whose two letters are equal ("gg") is its own
// reverse: its copies pair with each other, and a copy left without a partner can
// stand alone in the very middle -- but only one word in total can, so the middle adds
// 2 at most once.
//
// Siblings -- the same pairs-plus-one-middle count:
//   data-structures hashmap.LongestPalindrome409 -- single letters instead of two-letter words
//
// Time: O(n) -- one counting pass, one pass over the distinct words.
// Space: O(n) for the map.

public final class LongestPalindrome {

	public static int longestPalindrome(String[] words) {
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			map.merge(word, 1, Integer::sum);                              // COUNT each word
		}
		int len = 0;
		int center = 0;
		for (String word : map.keySet()) {
			String reversed = new StringBuilder(word).reverse().toString();
			int count = map.get(word);
			if (word.charAt(0) != word.charAt(1)) {
				len += 2 * Math.min(count, map.getOrDefault(reversed, 0)); // PAIR: 2 now, 2 more from the reverse
			} else {
				center += count % 2;                                       // MIDDLE candidate: no partner
				len += (count / 2) * 4;                                    // PAIR copies with each other
			}
		}
		len += center > 0 ? 2 : 0;                                         // MIDDLE: one word at most
		return len;
	}

	public static void main(String[] args) {
		String[] a1 = { "lc", "cl", "gg" };
		System.out.println(longestPalindrome(a1)); // 6
		String[] a2 = { "ab", "ty", "yt", "lc", "cl", "ab" };
		System.out.println(longestPalindrome(a2)); // 8
		String[] a3 = { "cc", "ll", "xx" };
		System.out.println(longestPalindrome(a3)); // 2
		String[] a4 = { "dd", "aa", "bb", "dd", "aa", "dd", "bb", "dd", "aa", "cc", "bb", "cc", "dd", "cc" };
		System.out.println(longestPalindrome(a4)); // 22
	}
}
