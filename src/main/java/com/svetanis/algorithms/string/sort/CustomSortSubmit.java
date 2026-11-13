package com.svetanis.algorithms.string.sort;

// 791. Custom Sort String
//
// Input: strings order and s. order has no repeated letter and gives a ranking of letters. Returns
// any permutation of s in which, if x comes before y in order, every x comes before every y;
// letters that are not in order may sit anywhere. This file puts them last, in alphabetical
// order. On LeetCode: 1 <= order.length <= 26, 1 <= s.length <= 200, both are lowercase English
// letters, and order has no repeated letter.
//
// The one idea: frequency[k] holds how many times the k-th letter of the alphabet occurs in s.
// Walk order from its first letter to its last, and for each letter write it out frequency times
// and set its count to 0. What is still above 0 belongs to a letter that order does not mention;
// write those at the end, a to z. Each letter of s is written exactly once, in the order's ranking.
//
// Time: O(n + m) for n = s.length and m = order.length -- one count pass over s, one walk over
// order, one pass over the 26 counters; the writes total n.
// Space: O(n) -- the 26 counters and the output.
//
// Siblings: CustomSortHashMap does the same with a HashMap of counts.

public final class CustomSortSubmit {

	public static String customSort(String order, String s) {
		int[] frequency = frequency(s); // COUNT: frequency[c - 'a'] = times c occurs in s
		StringBuilder sb = new StringBuilder();
		for (char c : order.toCharArray()) { // WALK: the order's ranking
			while (frequency[c - 'a'] > 0) {
				sb.append(c); // WRITE: all copies of c
				frequency[c - 'a']--; // USED: down to 0
			}
		}
		for (int i = 0; i < 26; i++) { // LEFTOVER: letters order does not mention, a to z
			while (frequency[i] > 0) {
				char c = (char) (i + 'a');
				sb.append(c);
				frequency[i]--;
			}
		}
		return sb.toString();
	}

	private static int[] frequency(String s) {
		int[] frequency = new int[26];
		for (int i = 0; i < s.length(); i++) {
			frequency[s.charAt(i) - 'a']++;
		}
		return frequency;
	}

	public static void main(String[] args) {
		System.out.println(customSort("cba", "abcd")); // cbad
		System.out.println(customSort("bcafg", "abcd")); // bcad
	}
}
