package com.svetanis.algorithms.string;

// 1662. Check If Two String Arrays are Equivalent
//
// Input: two arrays of strings, a1 and a2. Two arrays are equivalent if the strings of each,
// joined in order, give the same string. Returns true if a1 and a2 are equivalent. On LeetCode:
// 1 <= a1.length, a2.length <= 10^3, 1 <= a1[i].length, a2[i].length <= 10^3, the total length of
// each array is at most 10^3, lowercase letters.
//
// The one idea: join each array into one string and compare the two strings. The place where the
// pieces are cut does not matter, only the letters in order.
//
// Time: O(n) -- n is the total number of characters; joining and comparing each take one pass
// Space: O(n) -- the two joined strings

public final class EquivalentStrArrays {

	public static boolean areEqual(String[] a1, String[] a2) {
		String s1 = String.join("", a1); // JOIN: the pieces of a1, in order
		String s2 = String.join("", a2); // JOIN: the pieces of a2, in order
		return s1.equals(s2); // RETURN: same letters in the same order
	}

	public static void main(String[] args) {
		String[] w1 = { "ab", "c" };
		String[] w2 = { "a", "bc" };
		System.out.println(areEqual(w1, w2)); // true

		String[] w3 = { "a", "cb" };
		String[] w4 = { "ab", "c" };
		System.out.println(areEqual(w3, w4)); // false

		String[] w5 = { "abc", "d", "defg" };
		String[] w6 = { "abcddefg" };
		System.out.println(areEqual(w5, w6)); // true
	}
}
