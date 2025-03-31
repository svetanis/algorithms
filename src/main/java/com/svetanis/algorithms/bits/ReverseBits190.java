package com.svetanis.algorithms.bits;

// 190. Reverse Bits

// In Java, the >>> operator is the unsigned right 
// shift operator. It shifts the bits of a number 
// to the right by a specified number of positions, 
// filling the leftmost bits with zeros.

// Unsigned:
// Unlike the signed right shift operator (>>), 
// the >>> operator does not preserve the sign bit. 
// This means that the result is always positive.

// Zero Fill:
// The leftmost bits are filled with zeros, 
// regardless of the sign of the original number.

public final class ReverseBits190 {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static int reverse(int num) {
		int reversed = 0;
		for (int i = 0; i < 32 && num != 0; i++) {
			// 1. num & 1 isolates the lsb
			// 2. << (31 - i) moves the bit to its reversed position
			// 3. |= assigns the bit to the correct position in result
			reversed |= ((num & 1) << (31 - i));
			// unsigned right shift the num by one
			// to process the next bit
			num >>>= 1;
		}
		return reversed;
	}

	public static void main(String args[]) {
		System.out.println(reverse(23));// 00010111
		System.out.println(reverse(43261596));// 964176192
		// System.out.println(reverse(4294967293));// 3221225471
	}
}
