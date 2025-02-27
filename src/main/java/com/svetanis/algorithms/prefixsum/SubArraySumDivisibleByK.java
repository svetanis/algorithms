package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 974. Subarray Sums Divisible by K

public final class SubArraySumDivisibleByK {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int count(int[] a, int k) {
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			int remainder = sum % k;
			if (remainder < 0) {
				remainder += k;
			}
			count += map.getOrDefault(remainder, 0);
			map.put(remainder, map.getOrDefault(remainder, 0) + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 5, 0, -2, -3, 1 };
		System.out.println(count(a1, 5)); // 7

		int[] a2 = { 5 };
		System.out.println(count(a2, 9)); // 0
	}
}
