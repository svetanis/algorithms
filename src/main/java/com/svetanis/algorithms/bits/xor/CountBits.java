package com.svetanis.algorithms.bits.xor;

import static com.svetanis.java.base.utils.Print.print;

// 338. Counting Bits

public final class CountBits {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] count(int n) {
		int[] a = new int[n + 1];
		a[0] = 0;
		for (int i = 1; i <= n; i++) {
			int index = i & (i - 1);
			a[i] = a[index] + 1;
		}
		return a;
	}

	public static void main(String[] args) {
		print(count(2)); // [0,1,1]
		print(count(5)); // [0,1,1,2,1,2]
	}
}