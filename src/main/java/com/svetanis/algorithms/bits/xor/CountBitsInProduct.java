package com.svetanis.algorithms.bits.xor;

// given two non-neg integers, return the number of bits
// set to 1 in the binary representation of the number
// a*b
// for example given 3 and 7 the function should return 3, because the 
// binary representation of a*b=21 is 10101 and it contains three bits set to 1

// assume that a and b are integers within the range [0.. 100,000,000]

public final class CountBitsInProduct {

	public static int solution(int a, int b) {
		long n = (long) a * (long) b;
		int count = 0;
		while (n != 0) {
			// clear the least significant bit
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(solution(3, 7)); // 21 is 10101 -> 3
		System.out.println(solution(100000000, 100000000));
		System.out.println(solution(0, 100000000));
		System.out.println(solution(0, 0));
	}
}