package com.svetanis.algorithms.bits.xor;

public class Bits {

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

}
