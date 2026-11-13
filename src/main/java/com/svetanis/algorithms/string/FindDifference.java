package com.svetanis.algorithms.string;

import java.util.HashMap;
import java.util.Map;

// 389. Find the Difference
//
// Input: strings s and t. t is s with its letters shuffled and one more letter added at a random
// place. Returns the letter that was added. On LeetCode: 0 <= s.length <= 1000,
// t.length == s.length + 1, lowercase letters.
//
// The one idea: t holds every letter of s plus one more, so anything that "cancels" equal letters
// leaves only the extra one. Four ways to cancel, one method each:
// differenceXor -- XOR every character of s and t: a letter that appears twice XORs to 0, so only
// the added letter is left.
// differenceSum -- the sum of the characters of t minus the sum of those of s is the added letter.
// differenceSimple -- count the letters of s in 26 slots, subtract the letters of t; the slot that
// goes below 0 is the added letter.
// difference -- the same count in a HashMap.
//
// Time: O(n) for all four -- one pass over each string
// Space: O(1) for all four -- a few ints, or a table of at most 26 letters

public final class FindDifference {

	public static char differenceXor(String s, String t) {
		int xor = 0; // XOR: matching letters cancel to 0
		for (char c : s.toCharArray()) {
			xor ^= c;
		}
		for (char c : t.toCharArray()) {
			xor ^= c;
		}
		return (char) xor; // RETURN: what is left is the added letter
	}

	public static char differenceSum(String s, String t) {
		int ssum = 0;
		int tsum = 0;
		int index = 0;
		while (index < s.length()) {
			ssum += s.charAt(index); // SUM: characters of s
			tsum += t.charAt(index); // SUM: the same positions of t
			index += 1;
		}
		tsum += t.charAt(index); // the one position t has beyond s
		return (char) (tsum - ssum); // RETURN: the difference of the sums is the added letter
	}

	public static char differenceSimple(String s, String t) {
		int[] chars = new int[26]; // COUNT: copies of each letter of s not yet matched by t
		for (char c : s.toCharArray()) {
			chars[c - 'a']++;
		}
		for (char c : t.toCharArray()) {
			chars[c - 'a']--; // MATCH: t uses one copy
			if (chars[c - 'a'] == -1) {
				return c; // RETURN: t used a copy s did not have
			}
		}
		return ' '; // t is always one longer than s, so the loop returns before here
	}

	public static char difference(String s, String t) {
		Map<Character, Integer> map = new HashMap<>(); // COUNT: copies of each letter of s not yet matched by t
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			map.put(c, map.getOrDefault(c, 0) - 1); // MATCH: t uses one copy
			if (map.get(c) == -1) {
				return c; // RETURN: t used a copy s did not have
			}
		}
		return ' '; // t is always one longer than s, so the loop returns before here
	}

	public static void main(String[] args) {
		System.out.println(difference("abcd", "abcde")); // e
		System.out.println(difference("", "y")); // y
		System.out.println(differenceXor("abcd", "abcde")); // e
		System.out.println(differenceSum("abcd", "abecd")); // e
		System.out.println(differenceSimple("aabb", "ababc")); // c
	}
}
