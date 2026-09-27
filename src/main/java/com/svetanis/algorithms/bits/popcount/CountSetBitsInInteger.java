package com.svetanis.algorithms.bits.popcount;

// given an integer, count its set bits,
// known as the Hamming weight --
// negatives included, because of >>>

public final class CountSetBitsInInteger {

	public static int count(int x) {
		// Time Complexity: O(log n) -- one pass per binary digit up to the highest 1;
		// a negative has its highest 1 in slot 31, so it takes all 32

		int count = 0;
		while (x != 0) {
			count += (x & 1); // slot 0: 0 or 1
			// >>> fills slot 31 with 0, so a negative reaches 0 as well
			x = x >>> 1;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(count(11));
		System.out.println(count(128));
		System.out.println(count(2147483645));
	}
}
