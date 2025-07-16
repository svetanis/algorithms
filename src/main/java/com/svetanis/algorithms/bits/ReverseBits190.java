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

	public static int reverse(int n) {
		int reversed = 0;
		for (int i = 0; i < 32 && n != 0; i++) {
			// 1. n & 1 isolates the lsb
			// 2. << (31 - i) moves the bit to its reversed position
			// 3. |= assigns the bit to the correct position in result
			reversed |= ((n & 1) << (31 - i));
			// unsigned right shift the num by one
			// to process the next bit
			n >>>= 1;
		}
		return reversed;
	}

	public static int reverse2(int n) {
		int reversed = 0;
		for (int i = 0; i < 32; i++) {
			// 1. left shift result to make space for next bit
			reversed <<= 1;
			// 2. check if lsb of n is 1
			if ((n & 1) == 1) {
				// 3. set the corresponding bit in result
				reversed |= 1;
			}
			// 4. right shift n to process next bit
			n >>= 1;
		}
		return reversed;
	}

	public static void main(String args[]) {
		System.out.println(reverse(23));// 00010111
		System.out.println(reverse(43261596));// 964176192
		// System.out.println(reverse(4294967293));// 3221225471

		System.out.println(reverse(23));// 00010111

	}
}
