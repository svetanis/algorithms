package com.svetanis.algorithms.math;

// 1295. Find Numbers with Even Number of Digits

public final class DigitCount {
	// Time Complexity: O(n * k)

	public static int digitCount(int[] a) {
		int count = 0;
		for (int num : a) {
			if (even(num)) {
				count++;
			}
		}
		return count;
	}

	private static boolean even(int num) {
		int count = 0;
		while (num > 0) {
			num /= 10;
			count++;
		}
		return count % 2 == 0;
	}

	public static int countEven(int[] a) {
		int count = 0;
		for (int num : a) {
			if (String.valueOf(num).length() % 2 == 0) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 12, 345, 2, 6, 7896 };
		System.out.println(digitCount(a1)); // 2
		int[] a2 = { 555, 901, 482, 1771 };
		System.out.println(digitCount(a2)); // 1
	}
}