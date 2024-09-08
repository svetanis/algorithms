package com.svetanis.algorithms.bits.xor;

import static com.svetanis.algorithms.bits.xor.Bits.isPowerOfTwo;

// given a number with only one '1' and
// all other '0's in its binary representation
// find position of the only set bit

public final class PositionOfTheOnlySetBit {

	public static int position(int x) {
		// Time Complexity: O(n)
		if (!isPowerOfTwo(x)) {
			return -1;
		}
		int i = 1;
		int count = 1;
		while ((i & x) == 0) {
			i = i << 1;
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(position(16));
		System.out.println(position(12));
		System.out.println(position(128));
	}
}
