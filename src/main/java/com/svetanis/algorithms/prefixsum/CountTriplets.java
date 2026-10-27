package com.svetanis.algorithms.prefixsum;

// 1442. Count Triplets That Can Form Two Arrays of Equal XOR
//
// Input: an array a. A triplet is i < j <= k; its left part is a[i] ^ ... ^ a[j - 1] and its right
// part a[j] ^ ... ^ a[k], where ^ is XOR (bitwise exclusive or). Returns the number of triplets
// whose two parts are equal. On LeetCode: 1 <= a.length <= 300, 1 <= a[i] <= 10^8.
//
// The one idea: XOR works like a sum that can be taken back out, because x ^ x = 0. prefix[i]
// holds the XOR of the first i numbers, a[0] ^ ... ^ a[i - 1], with prefix[0] = 0. The XOR of
// a[i .. j - 1] is then prefix[i] ^ prefix[j]: the shared part a[0 .. i - 1] appears in both and
// cancels. So each part of a triplet costs one XOR of two prefix cells, and the method tries every
// triplet with those two lookups.
//
// Time: O(n^3) -- three nested loops over i < j <= k, about n^3 / 6 triplets, each O(1)
// Space: O(n) -- the n + 1 prefix cells
//
// Not used here: the two parts are equal exactly when a[i] ^ ... ^ a[k] is 0, so every pair i < k
// with prefix[i] == prefix[k + 1] gives k - i triplets, one per j; that counts in O(n^2) or O(n).

public final class CountTriplets {

	public static int countTriplets(int[] a) {
		int count = 0;
		int[] prefix = prefix(a);
		int n = a.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int x = prefix[i] ^ prefix[j]; // LEFT: a[i] ^ ... ^ a[j - 1]
				for (int k = j; k < n; k++) {
					int y = prefix[j] ^ prefix[k + 1]; // RIGHT: a[j] ^ ... ^ a[k]
					if (x == y) {
						count++; // RECORD: equal parts
					}
				}
			}
		}
		return count;
	}

	private static int[] prefix(int[] a) {
		int[] prefix = new int[a.length + 1];
		prefix[0] = 0; // BASE: the XOR of no numbers
		for (int i = 0; i < a.length; i++) {
			prefix[i + 1] = prefix[i] ^ a[i]; // PREFIX: XOR of a[0 .. i]
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 1, 6, 7 };
		System.out.println(countTriplets(a1)); // 4

		int[] a2 = { 1, 1, 1, 1, 1 };
		System.out.println(countTriplets(a2)); // 10
	}
}
