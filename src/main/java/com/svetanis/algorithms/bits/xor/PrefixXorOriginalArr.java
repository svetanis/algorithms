package com.svetanis.algorithms.bits.xor;

import static com.svetanis.java.base.utils.Print.print;

// 2433. Find The Original Array of Prefix Xor

public final class PrefixXorOriginalArr {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] prefixXor(int[] prefix) {
		int n = prefix.length;
		int[] a = new int[n];
		a[0] = prefix[0];
		for (int i = 1; i < n; i++) {
			a[i] = prefix[i - 1] ^ prefix[i];
		}
		return a;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 2, 0, 3, 1 };
		print(prefixXor(a1)); // [5,7,2,3,2]
		int[] a2 = { 13 };
		print(prefixXor(a2)); // [13]
	}
}