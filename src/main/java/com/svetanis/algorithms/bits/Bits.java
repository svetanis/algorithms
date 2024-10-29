package com.svetanis.algorithms.bits;

public class Bits {

	public static boolean isEven(int x) {
		return (x & 1) == 0;
	}

	public static boolean isOdd(int x) {
		return (x & 1) == 1;
	}

	public static boolean isBitSet(int x, int i) {
		int mask = 1 << i;
		return (x & mask) != 0;
	}

	public static int getBit(int x, int i) {
		int mask = 1 << i;
		return x & mask;
	}

	public static int setBit(int x, int i) {
		int mask = 1 << i;
		return x | mask;
	}

	public static int unsetBit(int x, int i) {
		int mask = 1 << i;
		return x & ~mask;
	}

	public static int flipBit(int x, int i) {
		int mask = 1 << i;
		return x ^ mask;
	}

	public static int rightMostSetBit(int x) {
		return x & ~(x - 1);
	}

	public static int rightMostSetBitIterative(int x) {
		int i = 1;
		while ((x & i) == 0) {
			i = i << 1;
		}
		return i;
	}

	public static int turnOffRightMostSetBit(int x) {
		return x & (x - 1);
	}

	public static boolean isPowerOfTwo(int n) {
		return n != 0 && (n & (n - 1)) == 0;
	}

}
