package com.svetanis.algorithms.bits.xor;

// 1529. Minimum Suffix Flips

public final class MinSuffixFlips {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minFlips(String target) {
		int count = 0;
		for (int i = 0; i < target.length(); i++) {
			int bit = target.charAt(i) - '0';
			count += (count & 1) ^ bit;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(minFlips("10111")); // 3
		System.out.println(minFlips("101")); // 3
		System.out.println(minFlips("00000")); // 0
	}
}
