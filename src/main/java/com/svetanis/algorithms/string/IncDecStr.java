package com.svetanis.algorithms.string;

// 1370. Increasing Decreasing String
//
// Input: a string s of lowercase letters. Build a new string by repeating rounds until every
// letter of s is used: first take the smallest letter left, then the smallest letter left that is
// larger than the one just taken, and so on up; then take the largest letter left, then the
// largest left that is smaller than the one just taken, and so on down. A letter is taken once
// per sweep, however many copies remain. Returns the new string. On LeetCode:
// 1 <= s.length <= 500.
//
// The one idea: only how many copies of each letter remain matters, not where they sat in s.
// counts[i] holds the copies left of the i-th letter of the alphabet. An upward sweep visits
// 'a' to 'z' and takes one copy of each letter that has any left; a downward sweep does the same
// from 'z' to 'a'. Visiting letters in alphabet order is exactly "the next larger letter left".
//
// Time: O(n) -- each round takes at least one letter, so at most n rounds, and a round is two
// sweeps of 26 letters: 52 * n steps, a constant times n.
// Space: O(n) -- the result; the counts are 26 numbers.

public final class IncDecStr {

	public static String sortStr(String s) {
		int[] counts = counts(s);
		StringBuilder sb = new StringBuilder();
		while (sb.length() < s.length()) { // ROUND: until every letter is used
			for (int i = 0; i < 26; i++) { // UP: 'a' to 'z'
				char c = (char) (i + 'a');
				if (counts[i] > 0) {
					sb.append(c); // TAKE: one copy
					counts[i]--;
				}
			}
			for (int i = 25; i >= 0; i--) { // DOWN: 'z' to 'a'
				char c = (char) (i + 'a');
				if (counts[i] > 0) {
					sb.append(c); // TAKE: one copy
					counts[i]--;
				}
			}
		}
		return sb.toString();
	}

	private static int[] counts(String s) {
		int[] counts = new int[26];
		for (char c : s.toCharArray()) {
			counts[c - 'a']++; // COUNT: copies of each letter
		}
		return counts;
	}

	public static void main(String[] args) {
		System.out.println(sortStr("aaaabbbbcccc")); // abccbaabccba
		System.out.println(sortStr("rat")); // art
	}
}
