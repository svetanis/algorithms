package com.svetanis.algorithms.string.palindrome;

import java.util.HashMap;
import java.util.Map;

// 2131. Longest Palindrome by Concatenating Two Letter Words

public final class LongestPalindrome {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int longestPalindrome(String[] words) {
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			map.merge(word, 1, Integer::sum);
		}
		int len = 0;
		int center = 0;
		for (String word : map.keySet()) {
			String reversed = new StringBuilder(word).reverse().toString();
			int count = map.get(word);
			if (word.charAt(0) != word.charAt(1)) {
				len += 2 * Math.min(count, map.getOrDefault(reversed, 0));
			} else {
				center += count % 2;
				len += (count / 2) * 4;
			}
		}
		len += center > 0 ? 2 : 0;
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
