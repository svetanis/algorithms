package com.svetanis.algorithms.bits;

import static com.svetanis.algorithms.bits.Bits.isPowerOfTwo;

// given a number with only one '1' and
// all other '0's in its binary representation
// find position of the only set bit

public final class PositionOfTheOnlySetBit {

	public static int position(int x) {
		// Time Complexity: O(1) -- at most 32 shifts
		if (!isPowerOfTwo(x)) { // no single 1, no single position
			return -1;
		}
		int i = 1;
		int count = 1;
		while ((i & x) == 0) { // slide a one-slot mask up until it meets x's 1
			i = i << 1;
			count++;
		}
		return count; // positions counted from 1
	}

	public static void main(String[] args) {
		System.out.println(position(16));
		System.out.println(position(12));
		System.out.println(position(128));
	}
}
