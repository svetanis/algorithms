package com.svetanis.algorithms.math;

// 2455. Average Value of Even Numbers That Are Divisible by Three

public final class AverageValueOfEvenNums {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int averageValue(int[] a) {
		int sum = 0;
		int count = 0;
		for (int num : a) {
			if (num % 6 == 0) {
				sum += num;
				count += 1;
			}
		}
		return count == 0 ? 0 : sum / count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 6, 10, 12, 15 };
		System.out.println(averageValue(a1)); // 9

		int[] a2 = { 1, 2, 4, 7, 10 };
		System.out.println(averageValue(a2)); // 0
	}
}