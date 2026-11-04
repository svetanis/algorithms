package com.svetanis.algorithms.bits.popcount;

// 191. Number of 1 Bits (the Hamming weight)
//
// Input: an int n. Returns the number of 1s in its binary form. On LeetCode: 1 <= n <= 2^31 - 1.
//
// The one idea: hammingWeight removes n's lowest 1 each pass and counts the passes until n is 0.
// n - 1 turns the lowest 1 off and every 0 below it on, and leaves the slots above it alone; & keeps
// only the slots where n and n - 1 agree, which is n without its lowest 1. There is no shift, so the
// sign never enters: it is right for every int, and -1 takes 32 passes. hammingWeight2 reads slot 0
// with n & 1 and moves every slot down one with >>. Its test n > 0 is enough only because 191
// promises n >= 1: a negative n fails the test at once and returns 0.
//
// Time: hammingWeight O(popcount) -- one pass per 1, at most 32; hammingWeight2 O(log n) -- one pass
// per binary digit up to the highest 1, at most 31.
// Space: O(1) -- a counter.
//
// Siblings: CountSetBitsInInteger is the shift loop with >>> and != 0, so it counts a negative's 32
// slots too; CountBitsInProduct runs hammingWeight's loop on a long.

public final class HammingWeight191 {
	// Space Complexity: O(1)

	// Time Complexity: O(popcount) -- one pass per SET bit, so <= 32
	// and usually far fewer. That is the advantage over the shift loop.
	// Right for every int: there is no shift, so the sign never enters.
	public static int hammingWeight(int n) {
		int count = 0;
		while (n != 0) { // STOP: every 1 removed
			n = n & (n - 1); // REMOVE exactly one 1, the lowest
			count++; // COUNT it
		}
		return count;
	}

	// Time Complexity: O(log n) -- one pass per binary digit
	// n > 0 is enough only because 191 promises n >= 1: a negative n
	// fails the test at once and returns 0
	public static int hammingWeight2(int n) {
		int count = 0;
		while (n > 0) { // STOP: no 1 left -- for n >= 1 only
			// increment count if lsb is 1
			count += n & 1; // ADD slot 0: 0 or 1
			// shift the next bit to the lsb position
			n >>= 1; // NEXT: every slot moves down one
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(hammingWeight(11)); // 3
		System.out.println(hammingWeight(128)); // 1
		System.out.println(hammingWeight(2147483645)); // 30
		System.out.println(hammingWeight(-1)); // 32
		System.out.println(hammingWeight2(2147483645)); // 30
		System.out.println(hammingWeight2(11)); // 3
	}
}
