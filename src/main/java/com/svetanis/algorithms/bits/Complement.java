package com.svetanis.algorithms.bits;

// 476. Number Complement
// (also 1009, Complement of Base 10 Integer, where 0 gives 1)

// for a given positive number N
// in base 10, find the complement
// of its binary representation 
// as a base 10 integer

// flip every slot up to the highest 1 and none above it:
// x ^ mask, where mask is 1 from the highest 1 down to slot 0

public final class Complement {

	// Time Complexity: O(1) -- five shifts copy the highest 1 into every slot below it
	public static int complement2(int num) {
		if (num == 0) {
			return 1;
		}
		int mask = num;
		mask |= (mask >> 1); // the highest 1 copied into the slot below it
		mask |= (mask >> 2);
		mask |= (mask >> 4);
		mask |= (mask >> 8);
		mask |= (mask >> 16); // now every slot from the highest 1 down is 1
		return num ^ mask; // flips exactly the slots inside the number's width
	}

	public static int complement(int n) {
		// Time Complexity: O(bits)
		if (n == 0) {
			return 1;
		}
		int bits = countBits(n);
		// 2^bits - 1 is bits 1s; worked out in long, because for
		// bits == 31, 2^31 does not fit an int
		int allBitsSet = (int) ((1L << bits) - 1);
		return n ^ allBitsSet;
	}

	private static int countBits(int x) {
		int count = 0;
		while (x > 0) {
			count++;
			x = x >> 1;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(complement(8));
		System.out.println(complement(10));
		System.out.println(complement2(2147483647));
	}
}
