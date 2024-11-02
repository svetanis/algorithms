package com.svetanis.algorithms.bits.xor;

public final class AdditionHalfAdder {

	public static int add(int x, int y) {
		// iterate till there is no carry
		while (y != 0) {
			// carry now contains
			// common set bits of x and y
			int carry = x & y;
			// sum of bits of x and y where
			// at least one of the bits is not set
			x = x ^ y;
			// carry is shifted by one so that
			// adding it to x gives the required sum
			y = carry << 1;
		}
		return x;
	}

	public static void main(String[] args) {
		System.out.println(add(5, 11));
	}
}
