package com.svetanis.algorithms.bits.xor;

// 191. Hamming Weight

public final class HammingWeight191 {
	// Time Complexity: O(log n)
	// Space Complexity: O(1)

	public static int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			n = n & n - 1;
			count++;
		}
		return count;
	}

	public static int hammingWeight2(int n) {
		int count = 0;
		while (n > 0) {
			// increment count if lcb is 1
			count += n & 1;
			// shift the next bit to the lsb position
			n >>= 1;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(hammingWeight(11)); // 3
		System.out.println(hammingWeight(128)); // 1
		System.out.println(hammingWeight(2147483645)); // 30
	}
}
