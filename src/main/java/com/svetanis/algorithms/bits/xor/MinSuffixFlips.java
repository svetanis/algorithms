package com.svetanis.algorithms.bits.xor;

// 1529. Minimum Suffix Flips
//
// Input: a string target of '0's and '1's. A string s of the same length starts as all '0's; one
// operation picks an index i and flips every character of s from i to the end. Returns the fewest
// operations that turn s into target. On LeetCode: 1 <= target.length <= 10^5.
//
// The one idea: walk target from left to right. Every flip started so far began at or before the
// current position and runs to the end, so it covers this position: after count flips, the
// position reads count & 1 -- 1 if count is odd, 0 if even. If that differs from target here, a flip
// must start exactly here: a later flip never reaches this position, and a flip starting here
// leaves every position to the left, already right, alone. So count goes up by one exactly where
// the current value differs from target.
//
// Time: O(n) -- one pass.
// Space: O(1) -- a counter.

public final class MinSuffixFlips {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minFlips(String target) {
		int count = 0;
		for (int i = 0; i < target.length(); i++) { // WALK left to right
			int bit = target.charAt(i) - '0';
			count += (count & 1) ^ bit; // FLIP HERE: 1 when the current value differs from the target
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(minFlips("10111")); // 3
		System.out.println(minFlips("101")); // 3
		System.out.println(minFlips("00000")); // 0
	}
}
