package com.svetanis.algorithms.bits.popcount;

// Count set bits in an integer -- the Hamming weight (GeeksforGeeks; LeetCode's version is 191)
//
// Input: any int x. Returns the number of 1s among its 32 slots; a negative is counted as its
// 32-bit pattern, so -1 gives 32.
//
// The one idea: read slot 0 with x & 1 and add it, then move every slot down one place with >>>,
// so the next slot is at slot 0. >>> fills slot 31 with 0, so after at most 32 moves x is 0 and the
// loop ends -- a negative too. (>> would copy the sign slot back in, and a negative would never
// reach 0.)
//
// Time: O(log x) for a positive x -- one pass per binary digit up to the highest 1, at most 31; a
// negative has its highest 1 in slot 31, so it takes all 32.
// Space: O(1) -- a counter.
//
// Siblings: HammingWeight191 is 191 -- hammingWeight removes the lowest 1 each pass, one pass per
// 1; hammingWeight2 is this loop with >> and n > 0, so it returns 0 for every negative.

public final class CountSetBitsInInteger {

	public static int count(int x) {
		// Time Complexity: O(log x) -- one pass per binary digit up to the highest 1;
		// a negative has its highest 1 in slot 31, so it takes all 32

		int count = 0;
		while (x != 0) { // STOP: no 1 is left
			count += (x & 1); // ADD slot 0: 0 or 1
			// >>> fills slot 31 with 0, so a negative reaches 0 as well
			x = x >>> 1; // NEXT: every slot moves down one
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(count(11)); // 3
		System.out.println(count(128)); // 1
		System.out.println(count(2147483645)); // 30
		System.out.println(count(-1)); // 32
	}
}
