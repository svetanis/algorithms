package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 523. Continuous Subarray Sum

public final class ContinuousSubArrSum {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static boolean sum(int[] a, int k) {
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		for(int i = 0; i < a.length; i++) {
			sum += a[i];
			int remainder = sum % k;
			if(map.containsKey(remainder) && i - map.get(remainder) >= 2) {
				return true;
			}
			map.putIfAbsent(remainder, i);
		}
		return false;
	}

	public static void main(String[] args) {
		int[] a1 = { 23, 2, 4, 6, 7 };
		System.out.println(sum(a1, 6)); // true

		int[] a2 = { 23, 2, 6, 4, 7 };
		System.out.println(sum(a2, 6)); // true

		int[] a3 = { 23, 2, 6, 4, 7 };
		System.out.println(sum(a3, 13)); // false

		int[] a4 = { 23, 2, 4, 6, 6 };
		System.out.println(sum(a4, 7)); // true
	}
}
