package com.svetanis.algorithms.math;

// 367. given a positive integer n,
// return true if n is a perfect square
// or false otherwise

// the sum of the first n odd numbers is n^2.
// 1 = 1
// 4 = 1 + 3
// 9 = 1 + 3 + 5

// every perfect square is the sum of 
// a sequence of odd numbers starting from 1
// keep adding sequentially larger odd
// numbers to a sum

public final class IsPerfectSquare {
	// Time Complexity: O(log n)

	public static boolean isPerfectSquare(int n) {
		int i = 1;
		int sum = 0;
		while (sum < n) {
			sum += i;
			if (sum == n) {
				return true;
			}
			i += 2;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isPerfectSquare(35));
		System.out.println(isPerfectSquare(49));
		System.out.println(isPerfectSquare(16));
		System.out.println(isPerfectSquare(14));
	}
}