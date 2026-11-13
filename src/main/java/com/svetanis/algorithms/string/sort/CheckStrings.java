package com.svetanis.algorithms.string.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 2840. Check if Strings Can be Made Equal With Operations II
//
// Input: two strings s1 and s2 of the same length n. One operation picks indices i < j with
// j - i even and swaps s1[i] and s1[j]. Returns true if s1 can be made equal to s2. On LeetCode:
// n == s1.length == s2.length, 1 <= n <= 10^5, both are lowercase English letters.
//
// The one idea: an operation swaps two positions of the same parity, both even or both odd, so a
// letter never leaves its parity class. Within one class any two neighbours (indices i and i + 2)
// can swap, and neighbour swaps can bring the letters of that class into any order. So s1 can
// become s2 exactly when the even-index letters of s1 are the same letters, with the same counts,
// as the even-index letters of s2, and likewise for the odd indices. checkStringsSimple counts
// letters for each parity class: add 1 for s1's letter and subtract 1 for s2's, and every count
// must end at 0. checkStrings sorts each class instead and compares the sorted lists.
//
// Time: checkStringsSimple -- O(n + 26), one pass and one pass over the counters. checkStrings --
// O(n log n), the four sorts.
// Space: checkStringsSimple -- O(1), two rows of 26 counters. checkStrings -- O(n), four lists.

public final class CheckStrings {

	public static boolean checkStringsSimple(String s1, String s2) {
		int[][] fdiff = new int[2][26]; // COUNTS: row 0 for even indices, row 1 for odd; s1 adds, s2 subtracts
		for (int i = 0; i < s1.length(); i++) {
			int parityIndex = i & 1;
			int index1 = s1.charAt(i) - 'a';
			int index2 = s2.charAt(i) - 'a';
			fdiff[parityIndex][index1]++;
			fdiff[parityIndex][index2]--;
		}
		for (int index = 0; index < 26; index++) {
			if (fdiff[0][index] != 0) {
				return false; // MISMATCH: an even-index letter is in one string more often than in the other
			}
			if (fdiff[1][index] != 0) {
				return false; // MISMATCH: the same for an odd-index letter
			}
		}
		return true;
	}

	public static boolean checkStrings(String s1, String s2) {
		List<Character> even1 = chars(s1, 0);
		List<Character> even2 = chars(s2, 0);
		List<Character> odd1 = chars(s1, 1);
		List<Character> odd2 = chars(s2, 1);
		// unbox to char before comparing: != on two Characters compares
		// references, which only works inside the 0..127 cache
		for (int i = 0; i < even1.size(); i++) {
			char c1 = even1.get(i);
			char c2 = even2.get(i);
			if (c1 != c2) {
				return false; // MISMATCH: the sorted even-index letters differ
			}
		}
		for (int i = 0; i < odd1.size(); i++) {
			char c1 = odd1.get(i);
			char c2 = odd2.get(i);
			if (c1 != c2) {
				return false; // MISMATCH: the sorted odd-index letters differ
			}
		}
		return true;
	}

	private static List<Character> chars(String s, int start) {
		List<Character> list = new ArrayList<>();
		for (int i = start; i < s.length(); i += 2) { // TAKE: every other letter, from start
			list.add(s.charAt(i));
		}
		Collections.sort(list); // SORT: order inside a parity class does not matter
		return list;
	}

	public static void main(String[] args) {
		System.out.println(checkStringsSimple("abcdba", "cabdab")); // true
		System.out.println(checkStringsSimple("abe", "bea")); // false
		System.out.println(checkStrings("abcdba", "cabdab")); // true
		System.out.println(checkStrings("abe", "bea")); // false
	}
}
