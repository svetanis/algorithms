package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 821. Shortest Distance to a Character
//
// Input: a string s and a character c that occurs in s at least once.
// Return: an array whose i-th number is the distance from index i to the nearest
// occurrence of c in s.
//
// The one idea: the nearest c is either the closest one on the LEFT or the closest one
// on the RIGHT. A pass left to right writes the distance to the last c seen; a pass
// right to left measures the distance to the next c and keeps the smaller of the two.
// Before the first c there is no c on the left, so prev starts at -INF, which makes that
// distance larger than any real one; INF = 2^30 keeps i - prev inside int.
//
// Time: O(n) -- two passes.
// Space: O(n) for the output; O(1) besides it.

public final class ShortestDistToChar {

	private static final int INF = 1 << 30;

	public static int[] shortestToChar(String s, char c) {
		int n = s.length();
		int[] a = new int[n];
		int prev = -INF;                                   // START: no c seen on the left yet
		for (int i = 0; i < n; i++) {                      // LEFT TO RIGHT
			if (s.charAt(i) == c) {
				a[i] = 0;                                  // FOUND: a c is at distance 0
				prev = i;                                  // RECORD the latest c
			} else {
				a[i] = Math.abs(i - prev);                 // WRITE the distance to the c on the left
			}
		}
		int next = INF;                                    // START: no c seen on the right yet
		for (int i = n - 1; i >= 0; i--) {                 // RIGHT TO LEFT
			if (a[i] == 0) {
				next = i;                                  // RECORD the latest c: only a c holds 0
			} else {
				a[i] = Math.min(a[i], Math.abs(next - i)); // KEEP the nearer of the two sides
			}
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(shortestToChar("loveleetcode", 'e'))); // [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
		System.out.println(Arrays.toString(shortestToChar("aaab", 'b')));         // [3, 2, 1, 0]
		System.out.println(Arrays.toString(shortestToChar("baab", 'b')));         // [0, 1, 1, 0]
	}
}
