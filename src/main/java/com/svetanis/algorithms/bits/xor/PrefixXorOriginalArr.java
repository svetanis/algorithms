package com.svetanis.algorithms.bits.xor;

import java.util.Arrays;

// 2433. Find The Original Array of Prefix Xor
//
// Input: an int array prefix, where prefix[i] = arr[0] ^ arr[1] ^ ... ^ arr[i] for some unknown
// array arr. Returns arr (it is unique). On LeetCode: 1 <= pref.length <= 10^5,
// 0 <= pref[i] <= 10^6.
//
// The one idea: prefix[i] = prefix[i - 1] ^ arr[i]. ^ both sides with prefix[i - 1]: a number ^
// itself is 0, so prefix[i - 1] ^ prefix[i] = arr[i]. It is the prefix-sum rule
// arr[i] = prefix[i] - prefix[i - 1], with ^ standing for both + and -. arr[0] is prefix[0].
//
// Time: O(n) -- one pass.
// Space: O(n) -- the answer array.

public final class PrefixXorOriginalArr {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] prefixXor(int[] prefix) {
		int n = prefix.length;
		int[] a = new int[n];
		a[0] = prefix[0]; // BASE: the first prefix is the first number
		for (int i = 1; i < n; i++) { // WALK left to right
			a[i] = prefix[i - 1] ^ prefix[i]; // UNDO: the shared part cancels, a[i] is left
		}
		return a;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 2, 0, 3, 1 };
		System.out.println(Arrays.toString(prefixXor(a1))); // [5, 7, 2, 3, 2]
		int[] a2 = { 13 };
		System.out.println(Arrays.toString(prefixXor(a2))); // [13]
	}
}