package com.svetanis.algorithms.bits.xor;

// given an positive integer,
// count number of set bits
// this is knows as Hamming weight

// to unset the rightmost set bit
// substract a number by 1
// do bitwise & with itself
// n & (n - 1)

public final class CountSetBitsInIntegerKernighan {

	public static int count(int x) {
		// Time Complexity: O(log n)

		int count = 0;
		while (x != 0) {
			x = x & (x - 1);
			count++;
		}
		return count;
	}

	public static int rightMostSetBitIterative(int x) {
		int i = 1;
		while ((x & i) == 0) {
			i = i << 1;
		}
		return i;
	}

	public static void main(String[] args) {
		System.out.println(count(11));
		System.out.println(count(128));
		System.out.println(count(2147483645));
	}
}
