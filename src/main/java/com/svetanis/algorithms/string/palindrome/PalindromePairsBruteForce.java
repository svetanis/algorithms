package com.svetanis.algorithms.string.palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 336. Palindrome Pairs
//
// Input: an array of distinct strings of lowercase letters; the empty string is allowed.
// On LeetCode: 1 <= words.length <= 5000, 0 <= words[i].length <= 300.
// Return: every ordered pair of positions [i, j], i != j, where words[i] + words[j] is a
// palindrome. Order matters: [i, j] and [j, i] are two different pairs.
//
// The one idea: a pair works exactly when the joined string equals its reverse, so try
// every ordered pair and test the joined string directly. LeetCode asks for time
// linear in the total length of the words; this brute force is quadratic in the
// number of words and does not meet it.
//
// Siblings -- the same problem:
//   todo.string.PalindromePairsRollingHash -- prefix and suffix hashes of every word instead of joining pairs
//
// Time: O(n^2 * k) -- n^2 ordered pairs, each joined, reversed and compared in O(k),
// k the longest word.
// Space: O(k) for one joined string, besides the output.

public final class PalindromePairsBruteForce {

	public static List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (i == j) {
					continue;                                   // SKIP: a word cannot pair with itself
				}
				String concat = words[i] + words[j];            // JOIN in this order, i first
				String reversed = new StringBuilder(concat).reverse().toString();
				if (concat.equals(reversed)) {                  // COMPARE with its reverse
					list.add(Arrays.asList(i, j));              // RECORD the ordered pair
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String[] w1 = { "abcd", "dcba", "lls", "s", "sssll" };
		System.out.println(palindromePairs(w1)); // [[0, 1], [1, 0], [2, 4], [3, 2]]
		String[] w2 = { "bat", "tab", "cat" };
		System.out.println(palindromePairs(w2)); // [[0, 1], [1, 0]]
		String[] w3 = { "a", "" };
		System.out.println(palindromePairs(w3)); // [[0, 1], [1, 0]]
	}
}
