package com.svetanis.algorithms.string;

import java.util.HashSet;
import java.util.Set;

// 771. Jewels and Stones
//
// Input: jewels -- the letters that count as jewels; stones -- the stones you have, one letter
// each. Returns how many of your stones are jewels. Letters are case sensitive. On LeetCode:
// 1 <= jewels.length, stones.length <= 50, English letters only, all letters of jewels distinct.
//
// The one idea: a stone is a jewel exactly when its letter is among the jewels, so remember the
// jewel letters once, then look each stone up. count remembers them in a set; countSimple in an
// array of 128 flags indexed by the letter's character code (valid for ASCII letters).
//
// Time: O(n + m) -- n = jewels.length() to remember them, m = stones.length() lookups of O(1).
// Space: O(1) -- at most 52 distinct letters (the set), or the fixed array of 128 (countSimple).
//
// Siblings: count uses a Set<Character>; countSimple uses the flag array and adds the flag
// itself, so it has no branch.

public final class JewelsAndStones {

	public static int countSimple(String jewels, String stones) {
		int[] ascii = new int[128]; // FLAGS: ascii[c] is 1 if letter c is a jewel
		for (char c : jewels.toCharArray()) {
			ascii[c] = 1; // MARK: a jewel letter
		}
		int count = 0;
		for (char c : stones.toCharArray()) {
			count += ascii[c]; // TAKE: adds 1 for a jewel, 0 for a plain stone
		}
		return count;
	}

	public static int count(String jewels, String stones) {
		Set<Character> set = toSet(jewels);
		int count = 0;
		for (char c : stones.toCharArray()) {
			if (set.contains(c)) { // TAKE: this stone is a jewel
				count++;
			}
		}
		return count;
	}

	private static Set<Character> toSet(String s) {
		Set<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			set.add(c); // MARK: a jewel letter
		}
		return set;
	}

	public static void main(String[] args) {
		System.out.println(countSimple("aA", "aAAbbbb")); // 3
		System.out.println(countSimple("z", "ZZ")); // 0
		System.out.println(count("aA", "aAAbbbb")); // 3
		System.out.println(count("z", "ZZ")); // 0
	}
}
