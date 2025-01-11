package com.svetanis.algorithms.search.heap;

// 263. Ugly Number

public final class IsUglyNumber {
	// Time Complexity: O(log n)

	public static boolean isUgly(int n) {
		if (n < 1) {
			return false;
		}
		while (n % 2 == 0) {
			n /= 2;
		}
		while (n % 3 == 0) {
			n /= 3;
		}
		while (n % 5 == 0) {
			n /= 5;
		}
		return n == 1;
	}

	public static void main(String[] args) {
		System.out.println(isUgly(6)); // true
		System.out.println(isUgly(1)); // true
		System.out.println(isUgly(14)); // false
	}
}