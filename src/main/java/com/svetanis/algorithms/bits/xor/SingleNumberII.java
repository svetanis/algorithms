package com.svetanis.algorithms.bits.xor;

// 137. Single Number II

public final class SingleNumberII {
	// Time Complexity: O(n)

	public static int single(int[] a) {
		int single = 0;
		for (int bit = 0; bit < 32; bit++) {
			int bitCount = 0;
			for (int num : a) {
				bitCount += (num >> bit) & 1;
			}
			bitCount %= 3;
			single |= (bitCount << bit);
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
