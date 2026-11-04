package com.svetanis.algorithms.bits;

// 190. Reverse Bits
//
// Input: an int n, read as 32 bits with no sign -- Java has no unsigned int, so LeetCode passes the
// pattern in an int. Returns the pattern with slot i moved to slot 31 - i: slot 0 goes to the top,
// slot 31 to the bottom. Both methods take any 32-bit pattern, a negative int included.
//
// The one idea: read n's slots from slot 0 upwards and write each one into the answer from slot 31
// downwards. reverse puts the bit read on pass i straight into slot 31 - i, and stops early once n
// has no 1 left, since the slots still unread are all 0. reverse2 shifts the answer left once
// before each bit is added at slot 0, so the first bit read is shifted the most: after 32 passes it
// sits in slot 31.
//
// In Java, >>> is the unsigned right shift: it moves every slot down and fills slot 31 with 0,
// whatever the sign. >> copies the sign slot back into slot 31 instead, so a negative stays
// negative. After >>> by 1 or more the result is never negative.
//
// Time: O(1) -- at most 32 passes.
// Space: O(1) -- two ints.
//
// Siblings: the two methods differ in where the bit is placed -- reverse at its final slot at once,
// reverse2 at slot 0, carried up by the later shifts.

public final class ReverseBits190 {

	public static int reverse(int n) {
		int reversed = 0;
		for (int i = 0; i < 32 && n != 0; i++) { // WALK: stop once no 1 is left to move
			// 1. n & 1 isolates the lsb
			// 2. << (31 - i) moves the bit to its reversed position
			// 3. |= assigns the bit to the correct position in result
			reversed |= ((n & 1) << (31 - i)); // PLACE: slot i of the input lands in slot 31 - i
			// unsigned right shift the num by one
			// to process the next bit
			n >>>= 1; // NEXT: >>> fills slot 31 with 0, so a negative n reaches 0 too
		}
		return reversed;
	}

	public static int reverse2(int n) {
		int reversed = 0;
		for (int i = 0; i < 32; i++) { // WALK: all 32 slots
			// 1. left shift result to make space for next bit
			reversed <<= 1; // MAKE ROOM: every bit placed so far moves up one slot
			// 2. check if lsb of n is 1
			if ((n & 1) == 1) {
				// 3. set the corresponding bit in result
				reversed |= 1; // PLACE: at slot 0, carried up by the later shifts
			}
			// 4. right shift n to process next bit -- >> is safe here: there
			// are exactly 32 passes, and the copies of the sign bit only reach
			// slot 0 after every original slot has been read
			n >>= 1; // NEXT
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
