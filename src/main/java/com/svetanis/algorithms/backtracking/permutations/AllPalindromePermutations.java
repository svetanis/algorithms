package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// 267. Palindrome Permutation II
//
// Input: a string s of lowercase letters. On LeetCode: 1 <= s.length() <= 16.
// Returns every distinct palindrome that can be made by rearranging the letters of s, in any
// order; an empty list when no rearrangement is a palindrome.
//
// The one idea: a palindrome is its middle with the same letter added to the left and to the
// right, again and again. count[i] holds how many times letter i is in s. A letter used an even
// number of times pairs up completely; at most one letter can have a copy left over, and that
// copy is the middle. Two letters with an odd count: no palindrome, return the empty list.
// Otherwise start from the middle (the odd letter, or the empty string) and grow outward: each
// step takes a letter that still has a pair left, spends the pair (count -= 2) and wraps it
// round the string built so far. When the string is as long as s, record it. Each step tries
// every letter once, in order, so one palindrome is never built twice.
//
// Time: O((n/2)! * n) -- one palindrome per order of the n/2 pairs (at most (n/2)! of them),
// each built by n/2 concatenations of up to n characters; the whole tree, unfinished strings
// included, has under three times as many nodes as there are palindromes.
// Space: O(n) beyond the answer -- recursion depth n/2 and the count array; the answer holds
// up to (n/2)! strings of length n.

public final class AllPalindromePermutations {

	public static List<String> generatePalindromes(String s) {
		String mid = "";
		int[] count = count(s);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			if (count[i] % 2 == 1) { // ODD COUNT: one copy is left over after pairing
				if (!mid.equals("")) {
					return list; // two odd letters: no palindrome
				}
				char c = (char) (i + 'a');
				mid = String.valueOf(c); // MIDDLE: the single left-over letter
			}
		}
		dfs(mid, s.length(), count, list);
		return list;
	}

	private static void dfs(String s, int n, int[] count, List<String> list) {
		if (s.length() == n) {
			list.add(s); // RECORD: every letter is used
			return;
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] > 1) { // TRY: letter i still has a pair
				char c = (char) (i + 'a');
				count[i] -= 2; // TAKE: spend the pair
				String concat = c + s + c; // WRAP: the pair goes round what is built
				dfs(concat, n, count, list);
				count[i] += 2; // backtrack: give the pair back
			}
		}
	}

	// count[c - 'a'] = how many times letter c is in s
	private static int[] count(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'a']++;
		}
		return chars;
	}

	public static void main(String[] args) {
		System.out.println(generatePalindromes("aabb")); // [baab, abba]
		System.out.println(generatePalindromes("abc")); // []
	}
}
