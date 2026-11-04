package com.svetanis.algorithms.bits;

import static com.svetanis.algorithms.bits.Bits.isPowerOfTwo;

// Position of the only set bit (GeeksforGeeks; no LeetCode problem)
//
// Input: an int x. Returns the position of x's only 1, counting slot 0 as position 1, or -1 when
// x has no 1 or more than one. The problem keeps x non-negative; Integer.MIN_VALUE, whose only 1
// is the sign slot, returns -1, because isPowerOfTwo turns away every negative.
//
// The one idea: a number with exactly one 1 is a power of two, so Bits.isPowerOfTwo answers the
// "only one" part. Then slide a mask with one 1 up from slot 0, counting, until it lands on x's 1;
// the count is the position.
//
// Time: O(1) -- one shift per slot below x's 1; at most 30, since slot 30 is the highest a
// positive power of two can use.
// Space: O(1) -- two ints.

public final class PositionOfTheOnlySetBit {

	public static int position(int x) {
		if (!isPowerOfTwo(x)) { // GUARD: no single 1, no single position
			return -1;
		}
		int i = 1; // MASK: slot 0
		int count = 1; // POSITION of the mask, counted from 1
		while ((i & x) == 0) { // WALK: slide a one-slot mask up until it meets x's 1
			i = i << 1;
			count++;
		}
		return count; // RETURN: positions counted from 1
	}

	public static void main(String[] args) {
		System.out.println(position(16)); // 5
		System.out.println(position(12)); // -1
		System.out.println(position(128)); // 8
	}
}
