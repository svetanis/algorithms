package com.svetanis.algorithms.bits.xor;

import static java.lang.Math.pow;

// for a given positive number N
// in base 10, find the complement
// of its binary representation 
// as a base 10 integer

public final class Complement {

	public static int complement2(int num) {
		if (num == 0) {
			return 1;
		}
		int mask = num;
		mask |= (mask >> 1);
		mask |= (mask >> 2);
		mask |= (mask >> 4);
		mask |= (mask >> 8);
		mask |= (mask >> 16);
		return num ^ mask;
	}

	public static int complement(int n) {
		// Time Complexity: O(bits)
		if (n == 0) {
			return 1;
		}
		int bits = countBits(n);
		int powerOfTwo = new Double(pow(2, bits)).intValue();
		int allBitsSet = powerOfTwo - 1;
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
