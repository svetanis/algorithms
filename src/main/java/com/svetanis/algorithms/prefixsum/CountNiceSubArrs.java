package com.svetanis.algorithms.prefixsum;

// 1248. Count Number of Nice Subarrays

public final class CountNiceSubArrs {
	// Time complexity: O(n)

	public static int countSubArrs(int[] a, int k) {
		int[] prefix = new int[a.length + 1];
		prefix[0] = 1;
		int count = 0;
		int total = 0;
		for (int num : a) {
			count += num % 2 == 0 ? 0 : 1;
			if (count - k >= 0) {
				total += prefix[count - k];
			}
			prefix[count]++;
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 2, 1, 1 };
		System.out.println(countSubArrs(a1, 3)); // 2

		int[] a2 = { 2, 4, 6 };
		System.out.println(countSubArrs(a2, 1)); // 0

		int[] a3 = { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 };
		System.out.println(countSubArrs(a3, 2)); // 16
	}
}
