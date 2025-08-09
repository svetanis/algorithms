package com.svetanis.algorithms.greedy;

// 1785. Minimum Elements to Add to Form a Given Sum

public final class MinElementToAddToGivenSum {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minElements(int[] a, int limit, int goal) {
		long sum = 0;
		for (int num : a) {
			sum += num;
		}
		long diff = Math.abs(goal - sum);
		return (int) ((diff + limit - 1) / limit);
	}

	public static void main(String[] args) {
		int[] a1 = { 1, -1, 1 };
		System.out.println(minElements(a1, 3, -4)); // 2

		int[] a2 = { 1, -10, 9, 1 };
		System.out.println(minElements(a2, 100, 0)); // 1
	}
}
