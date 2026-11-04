package com.svetanis.algorithms.bits.popcount;

// Count the 1s in a product (not a LeetCode problem)
//
// Input: two ints a and b, each in the range [0 .. 100,000,000]. Returns the number of 1s in the
// binary form of a * b. For example, a = 3 and b = 7 give 21, which is 10101 in binary, so 3.
//
// The one idea: a * b reaches 10^16, far past the largest int (about 2.1 * 10^9) but inside a long
// (about 9.2 * 10^18), so the product is worked out in long. Then count its 1s by removing them one
// at a time: n & (n - 1) is n without its lowest 1, so the number of removals that reach 0 is the
// number of 1s.
//
// Time: O(popcount of a * b) -- one pass per 1; at most 54, since a * b <= 10^16 < 2^54.
// Space: O(1) -- one long and a counter.
//
// Siblings: HammingWeight191.hammingWeight is the same removal loop on an int.

public final class CountBitsInProduct {
	// Time Complexity: O(popcount of a * b) -- at most 54 passes, a * b < 2^54

	public static int solution(int a, int b) {
		long n = (long) a * (long) b; // WIDEN: up to 10^16, past int, inside long
		int count = 0;
		while (n != 0) { // STOP: every 1 removed
			// clear the lowest 1: one pass per 1
			n = n & (n - 1); // REMOVE the lowest 1
			count++; // COUNT it
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(solution(3, 7)); // 3 -- 21 is 10101
		System.out.println(solution(100000000, 100000000)); // 20
		System.out.println(solution(0, 100000000)); // 0
		System.out.println(solution(0, 0)); // 0
	}
}