package com.svetanis.algorithms.prefixsum;

// 1442. Count Triplets That Can Form Two Arrays of Equal XOR

public final class CountTriplets {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int countTriplets(int[] a) {
		int count = 0;
		int[] prefix = prefix(a);
		int n = a.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int x = prefix[i] ^ prefix[j];
				for (int k = j; k < n; k++) {
					int y = prefix[j] ^ prefix[k + 1];
					if (x == y) {
						count++;
					}
				}
			}
		}
		return count;
	}

	private static int[] prefix(int[] a) {
		int[] prefix = new int[a.length + 1];
		prefix[0] = 0;
		for (int i = 0; i < a.length; i++) {
			prefix[i + 1] = prefix[i] ^ a[i];
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
