package com.svetanis.algorithms.bits;

// the one-slot primitives: an int is 32 slots, slot i is worth 2^i,
// and 1 << i is a mask with only slot i on

public class Bits {

	public static boolean isEven(int x) {
		return (x & 1) == 0; // every slot but 0 is worth an even amount
	}

	public static boolean isOdd(int x) {
		return (x & 1) == 1; // only slot 0 has an odd worth
	}

	public static boolean isBitSet(int x, int i) {
		int mask = 1 << i;
		return (x & mask) != 0; // x & mask is 0 or 2^i, never 1
	}

	// 2^i or 0: slot i of x, left in place; isBitSet answers yes or no
	public static int getBitMask(int x, int i) {
		int mask = 1 << i;
		return x & mask;
	}

	public static int setBit(int x, int i) {
		int mask = 1 << i;
		return x | mask; // | only adds 1s
	}

	public static int unsetBit(int x, int i) {
		int mask = 1 << i;
		return x & ~mask; // ~mask is 1 everywhere except slot i
	}

	public static int flipBit(int x, int i) {
		int mask = 1 << i;
		return x ^ mask; // ^ with 1 flips, ^ with 0 copies
	}

	public static int rightMostSetBit(int x) {
		return x & ~(x - 1); // only the lowest 1; the same number as x & -x
	}

	public static int rightMostSetBitIterative(int x) {
		// zero has no set bit for the loop below to find
		if (x == 0) {
			return 0;
		}
		int i = 1;
		while ((x & i) == 0) {
			i = i << 1;
		}
		return i;
	}

	public static int turnOffRightMostSetBit(int x) {
		// x - 1 borrows from the lowest 1: above it x and x - 1 agree,
		// at it and below it they differ, and & keeps only the agreement
		return x & (x - 1);
	}

	// a power of two has exactly one 1, so removing it leaves 0;
	// n > 0 turns away 0 and Integer.MIN_VALUE, whose single 1 is the sign bit
	public static boolean isPowerOfTwo(int n) {
		return n > 0 && (n & (n - 1)) == 0;
	}

}
