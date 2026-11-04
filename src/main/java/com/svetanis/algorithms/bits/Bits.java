package com.svetanis.algorithms.bits;

// Bit primitives -- no problem of their own; PositionOfTheOnlySetBit imports isPowerOfTwo.
//
// Input: an int x, and for the one-slot methods a slot number i, 0 <= i <= 31. Returns x tested,
// or x with that one slot changed. Every method is right for every int, negatives included.
//
// The one idea: an int is 32 slots, numbered 0 to 31 from the right. Slot i is worth 2^i, except
// slot 31, the sign, which is worth -2^31. 1 << i is a mask -- a number with only slot i on. Then
// x & mask reads slot i, x | mask turns it on, x & ~mask turns it off, and x ^ mask flips it,
// leaving every other slot as it was. The lowest 1 of x has a trick of its own: x - 1 turns that
// 1 off and every 0 below it on, and leaves the slots above it alone. So x & (x - 1) is x without
// its lowest 1, and x & ~(x - 1) is that lowest 1 alone.
//
// Time: O(1) -- one or two operations per method; rightMostSetBitIterative tries at most 32 slots.
// Space: O(1) -- nothing is stored.

public class Bits {

	public static boolean isEven(int x) {
		return (x & 1) == 0; // TEST slot 0: every slot but 0 is worth an even amount
	}

	public static boolean isOdd(int x) {
		return (x & 1) == 1; // TEST slot 0: only slot 0 has an odd worth
	}

	public static boolean isBitSet(int x, int i) {
		int mask = 1 << i; // MASK: only slot i on
		return (x & mask) != 0; // TEST: x & mask is 0 or 2^i, never 1
	}

	// 2^i or 0: slot i of x, left in place; isBitSet answers yes or no
	public static int getBitMask(int x, int i) {
		int mask = 1 << i; // MASK: only slot i on
		return x & mask; // KEEP: slot i, every other slot cleared
	}

	public static int setBit(int x, int i) {
		int mask = 1 << i; // MASK: only slot i on
		return x | mask; // SET: | only adds 1s
	}

	public static int unsetBit(int x, int i) {
		int mask = 1 << i; // MASK: only slot i on
		return x & ~mask; // CLEAR: ~mask is 1 everywhere except slot i
	}

	public static int flipBit(int x, int i) {
		int mask = 1 << i; // MASK: only slot i on
		return x ^ mask; // FLIP: ^ with 1 flips, ^ with 0 copies
	}

	public static int rightMostSetBit(int x) {
		return x & ~(x - 1); // KEEP: only the lowest 1; the same number as x & -x
	}

	public static int rightMostSetBitIterative(int x) {
		// zero has no set bit for the loop below to find
		if (x == 0) { // GUARD: without it the mask runs off slot 31 and the loop never ends
			return 0;
		}
		int i = 1; // MASK: starts at slot 0
		while ((x & i) == 0) { // WALK up the slots until one of x's 1s is met
			i = i << 1;
		}
		return i; // RETURN: the first 1 met is the lowest
	}

	public static int turnOffRightMostSetBit(int x) {
		// x - 1 borrows from the lowest 1: above it x and x - 1 agree,
		// at it and below it they differ, and & keeps only the agreement
		return x & (x - 1); // REMOVE: the lowest 1
	}

	// a power of two has exactly one 1, so removing it leaves 0;
	// n > 0 turns away 0 and Integer.MIN_VALUE, whose single 1 is the sign bit
	public static boolean isPowerOfTwo(int n) {
		return n > 0 && (n & (n - 1)) == 0; // TEST: positive, and one 1 only
	}

}
