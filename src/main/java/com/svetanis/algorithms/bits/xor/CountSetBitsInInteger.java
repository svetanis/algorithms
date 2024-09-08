package com.svetanis.algorithms.bits.xor;

// given an positive integer,
// count number of set bits
// this is knows as Hamming weight

public final class CountSetBitsInInteger {

	public static int count(int x) {
		// Time Complexity: O(log n)

		int count = 0;
		while (x != 0) {
			count += (x & 1);
			x = x >> 1;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(count(11));
		System.out.println(count(128));
		System.out.println(count(2147483645));
	}
}
