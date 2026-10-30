package com.svetanis.algorithms.search.heap;

// 263. Ugly Number
//
// An ugly number is a positive integer whose only prime factors are 2, 3 and 5 (1 counts, it has
// none). Given an int n, returns true if n is ugly.
//
// Divide out every 2, then every 3, then every 5. What is left has none of those factors, so n was
// ugly exactly when nothing is left but 1. Zero and negatives are never ugly, and the guard must
// come first: 0 % 2 == 0 forever, so the first loop would never end on 0.
//
// Time: O(log n) -- each division at least halves n
// Space: O(1)
//
// Siblings, the ugly numbers:
//   UglyNumber (264)      -- the n-th ugly number: three pointers, or a heap of the next candidates
//   SuperUglyNumber (313) -- the n-th number whose prime factors all come from a given list

public final class IsUglyNumber {

	public static boolean isUgly(int n) {
		if (n < 1) {
			return false;      // GUARD: 0 and negatives are not ugly, and 0 would loop forever
		}
		while (n % 2 == 0) {
			n /= 2;            // STRIP: every factor 2
		}
		while (n % 3 == 0) {
			n /= 3;            // STRIP: every factor 3
		}
		while (n % 5 == 0) {
			n /= 5;            // STRIP: every factor 5
		}
		return n == 1;         // REPORT: any other prime factor would still be here
	}

	public static void main(String[] args) {
		System.out.println(isUgly(6)); // true
		System.out.println(isUgly(1)); // true
		System.out.println(isUgly(14)); // false
	}
}