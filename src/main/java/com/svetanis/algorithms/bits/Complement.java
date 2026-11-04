package com.svetanis.algorithms.bits;

// 476. Number Complement
// (also 1009. Complement of Base 10 Integer, where 0 gives 1)
//
// Input: an int num. Returns the number whose binary digits are num's with every 0 turned into 1
// and every 1 into 0 -- only the digits from num's highest 1 down; the 0s above it are not digits.
// On LeetCode: 476 has 1 <= num < 2^31; 1009 has 0 <= n < 10^9, and 0 (one digit, "0") gives 1.
//
// The one idea: ^ with 1 flips a slot and ^ with 0 leaves it alone. So build a mask that is 1 in
// every slot from num's highest 1 down to slot 0 and 0 above it, and return num ^ mask.
// complement counts num's binary digits -- bits -- and the mask is 2^bits - 1, which is bits 1s in
// a row; it is worked out in long because 2^31 does not fit an int. complement2 copies the highest
// 1 into the slot below it, then the two slots below those, then four, eight and sixteen: after
// five shifts every slot from the highest 1 down is on, and that is the mask.
//
// Time: complement O(log num) -- countBits makes one pass per binary digit, at most 31;
// complement2 O(1) -- five shifts.
// Space: O(1) -- a few ints.
//
// Siblings: the two methods differ only in how they build the mask -- by counting the digits, or
// by copying the highest 1 downwards.

public final class Complement {

	// Time Complexity: O(1) -- five shifts copy the highest 1 into every slot below it
	public static int complement2(int num) {
		if (num == 0) { // BASE: "0" is one digit, and it flips to 1
			return 1;
		}
		int mask = num; // MASK: grows down from the highest 1
		mask |= (mask >> 1); // COPY: the highest 1 into the slot below it
		mask |= (mask >> 2);
		mask |= (mask >> 4);
		mask |= (mask >> 8);
		mask |= (mask >> 16); // now every slot from the highest 1 down is 1
		return num ^ mask; // FLIP: exactly the slots inside the number's width
	}

	public static int complement(int n) {
		// Time Complexity: O(log n) -- one pass per binary digit
		if (n == 0) { // BASE: "0" is one digit, and it flips to 1
			return 1;
		}
		int bits = countBits(n); // COUNT: binary digits up to the highest 1
		// 2^bits - 1 is bits 1s; worked out in long, because for
		// bits == 31, 2^31 does not fit an int
		int allBitsSet = (int) ((1L << bits) - 1); // MASK: bits 1s in a row
		return n ^ allBitsSet; // FLIP: every digit, none above
	}

	private static int countBits(int x) {
		int count = 0;
		while (x > 0) { // WALK: one pass per binary digit
			count++;
			x = x >> 1; // DROP: the lowest digit
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(complement(8)); // 7
		System.out.println(complement(10)); // 5
		System.out.println(complement2(2147483647)); // 0

		System.out.println(complement(5)); // 2
		System.out.println(complement(1)); // 0
		System.out.println(complement2(5)); // 2
		System.out.println(complement2(7)); // 0
		System.out.println(complement2(10)); // 5
		System.out.println(complement2(0)); // 1
	}
}
