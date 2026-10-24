package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1663. Smallest String With A Given Numeric Value
//
// Input: n and k. A letter is worth its place in the alphabet, 'a' = 1 up to 'z' = 26, and a
// string is worth the sum of its letters. Returns the alphabetically smallest string of n letters
// worth exactly k. On LeetCode: 1 <= n <= 10^5, n <= k <= 26 * n.
//
// The one idea: of two strings of the same length, the one with the smaller letter at the first
// place they differ comes first, so the answer wants 'a' at the front for as long as it can, and
// the value has to sit somewhere: push it to the back. Start from n 'a's, worth n, which leaves
// k - n to add. Turning an 'a' into a 'z' adds 25, so fill 'z's from the right while more than 25
// is left; the rest, 0 to 25, goes into the next position to the left, and everything before it
// stays 'a'. Why that is safe: read from the left, each position holds the smallest letter that
// still lets the positions after it make up the rest of k -- 'a' for as long as the positions
// after it, even as all 'z's, can carry what is left.
//
// Time: O(n) -- the fill, then at most n 'z's.
// Space: O(n) -- the letters.

public final class SmallestStrGivenNumVal {

	public static String smallestStr(int n, int k) {
		char[] chars = new char[n];
		Arrays.fill(chars, 'a'); // START: all 'a', worth n
		int remains = k - n; // EXTRA value still to place
		int index = n - 1; // FILL from the right
		while(remains > 25) { // Z: more is left than one letter can add
			chars[index--] = 'z'; // 'a' -> 'z' adds 25
			remains -= 25;
		}
		chars[index] = (char) ('a' + remains); // REST: 0 to 25 on top of an 'a'
		return new String(chars); // RETURN
	}

	public static void main(String[] args) {
		System.out.println(smallestStr(3, 27)); // aay
		System.out.println(smallestStr(5, 73)); // aaszz
	}
}
