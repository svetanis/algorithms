package com.svetanis.algorithms.bits;

// 190. Reverse Bits

// In Java, the >>> operator is the unsigned right 
// shift operator. It shifts the bits of a number 
// to the right by a specified number of positions, 
// filling the leftmost bits with zeros.

// Unsigned:
// Unlike the signed right shift operator (>>), 
// the >>> operator does not preserve the sign bit. 
// This means that the result is never negative
// (for a shift of 1 or more).

// Zero Fill:
// The leftmost bits are filled with zeros, 
// regardless of the sign of the original number.

public final class ReverseBits190 {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static int reverse(int n) {
		int reversed = 0;
		for (int i = 0; i < 32 && n != 0; i++) { // stop once no 1 is left to move
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
			// 4. right shift n to process next bit -- >> is safe here: there
			// are exactly 32 passes, and the copies of the sign bit only reach
			// slot 0 after every original slot has been read
			n >>= 1;
		}
		return reversed;
	}

	public static void main(String args[]) {
		// 23 is 00010111: reversed, those bits land in the top byte
		System.out.println(reverse(23)); // -402653184
		System.out.println(reverse(43261596)); // 964176192
		// 4294967293 does not fit an int; as a 32-bit pattern it is -3
		System.out.println(reverse(-3)); // -1073741825, which is 3221225471 unsigned
		System.out.println(reverse2(43261596)); // 964176192

	}
}
