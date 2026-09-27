package com.svetanis.algorithms.bits.popcount;

// 191. Number of 1 Bits (the Hamming weight)

// to unset the rightmost set bit
// subtract 1 from the number and
// do bitwise & with itself
// n & (n - 1)

public final class HammingWeight191 {
	// Space Complexity: O(1)

	// Time Complexity: O(popcount) -- one pass per SET bit, so <= 32
	// and usually far fewer. That is the advantage over the shift loop.
	// Right for every int: there is no shift, so the sign never enters.
	public static int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1); // remove exactly one 1
			count++;
		}
		return count;
	}

	// Time Complexity: O(log n) -- one pass per binary digit
	// n > 0 is enough only because 191 promises n >= 1: a negative n
	// fails the test at once and returns 0
	public static int hammingWeight2(int n) {
		int count = 0;
		while (n > 0) {
			// increment count if lsb is 1
			count += n & 1;
			// shift the next bit to the lsb position
			n >>= 1;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(hammingWeight(11)); // 3
		System.out.println(hammingWeight(128)); // 1
		System.out.println(hammingWeight(2147483645)); // 30
		System.out.println(hammingWeight(-1)); // 32
		System.out.println(hammingWeight2(2147483645)); // 30
	}
}
