package com.svetanis.algorithms.bits.xor;

// 137. Single Number II

// every value appears three times except one. XOR keeps only odd or
// even, and three copies are odd, so count instead: in each slot a
// tripled value adds 3 or 0 to the count, so count % 3 is the
// single's digit there

public final class SingleNumberII {
	// Time Complexity: O(n)

	public static int single(int[] a) {
		int single = 0;
		for (int bit = 0; bit < 32; bit++) { // all 32: slot 31 rebuilds a negative single
			int bitCount = 0;
			for (int num : a) {
				bitCount += (num >> bit) & 1;
			}
			bitCount %= 3; // the triples drop out
			single |= (bitCount << bit); // switch the slot on when it is 1
		}
		return single;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 2, 3, 2 };
		System.out.println(single(a1)); // 3

		int[] a2 = { 0, 1, 0, 1, 0, 1, 99 };
		System.out.println(single(a2)); // 99
	}
}
