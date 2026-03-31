package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

import com.svetanis.java.base.utils.Print;

// 821. Shortest Distance to a Character

public final class ShortestDistToChar {
	// Time complexity: O(n)
	// Space complexity: O(n)

	private static final int INF = 1 << 30;

	public static int[] shortestToChar(String s, char c) {
		int n = s.length();
		int[] a = new int[n];
		Arrays.fill(a, INF);
		int prev = -INF;
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == c) {
				a[i] = 0;
				prev = i;
			} else {
				a[i] = Math.abs(i - prev);
			}
		}
		int next = INF;
		for (int i = n - 1; i >= 0; i--) {
			if (a[i] == 0) {
				next = i;
			} else {
				a[i] = Math.min(a[i], Math.abs(next - i));
			}
		}
		return a;
	}

	public static void main(String[] args) {
		Print.print(shortestToChar("loveleetcode", 'e')); // [3,2,1,0,1,0,0,1,2,2,1,0]
		Print.print(shortestToChar("aaab", 'b')); // [3,2,1,0]
		Print.print(shortestToChar("baab", 'b')); // [0,1,1,0]

	}
}
