package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 2268. Minimum Number of Keypresses
//
// Input: s -- a string of lowercase English letters. A keypad has 9 buttons; each of the 26 letters
// is put on exactly one button, a button holds at most 3 letters, and the j-th letter on a button
// takes j presses to type. The letters may be placed freely. Returns the fewest presses that type
// s. On LeetCode: 1 <= s.length <= 10^5, s holds lowercase English letters only.
//
// The one idea: the placement makes 26 slots -- 9 that cost 1 press (the first letter on each
// button), 9 that cost 2 and 8 that cost 3. A letter's total is how often it appears times the
// cost of its slot. Give the cheapest slots to the most frequent letters: if a more frequent letter
// sat in a dearer slot than a less frequent one, swapping the two could not raise the total. So
// rank the letters by count, largest first; the first 9 cost 1 press each, the next 9 cost 2, the
// last 8 cost 3. Sorting the 26 counts and reading them from the end gives that ranking, and the
// press count goes up by 1 after every 9th letter.
//
// Time: O(n) -- one pass to count; sorting 26 counts is constant work.
// Space: O(1) -- 26 counts.
//
// Siblings: MinNumberOfKeypressesMap counts in a HashMap, sorts its entries by count with a stream,
// and walks only the letters that appear; the cost rule is the same.

public final class MinNumberOfKeypresses {

	public static int minNumKeypresses(String s) {
		int[] frequency = frequency(s);
		Arrays.sort(frequency); // SORT: most frequent at the end
		int total = 0;
		int keypress = 1;
		for (int i = 1; i <= 26; i++) { // RANK i: the i-th most frequent letter
			total += keypress * frequency[26 - i]; // COST: its count times its presses
			if (i % 9 == 0) { // NEXT slot on every button costs one more press
				keypress++;
			}
		}
		return total;
	}

	private static int[] frequency(String s) {
		int[] a = new int[26];
		for (char c : s.toCharArray()) {
			a[c - 'a']++; // COUNT each letter
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(minNumKeypresses("hello")); // 5
	}
}
