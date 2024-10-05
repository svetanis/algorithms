package com.svetanis.algorithms.search.binary.math;

// 367. given a positive integer n,
// return true if n is a perfect square
// or false otherwise

public final class IsPerfectSquare {
	// Time Complexity: O(log n)

	public static boolean isPerfectSquare(int n) {
		int left = 1;
		int right = n;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (mid * mid >= n) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left * left == n;
	}

	public static void main(String[] args) {
		System.out.println(isPerfectSquare(35));
		System.out.println(isPerfectSquare(49));
		System.out.println(isPerfectSquare(16));
		System.out.println(isPerfectSquare(14));
	}
}