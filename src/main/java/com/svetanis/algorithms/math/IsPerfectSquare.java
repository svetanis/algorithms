package com.svetanis.algorithms.math;

// 367. Valid Perfect Square

// given a positive integer n,
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
	// Time Complexity: O(sqrt(n)) -- the k-th pass makes the sum k^2
	// Space Complexity: O(1)

	public static boolean isPerfectSquare(int n) {
		int i = 1;
		// long, not int: above 46340^2, the largest square an int holds, one
		// more odd number overflows the sum to a negative, and the loop
		// wraps around and around without landing on or above n
		long sum = 0;
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
		System.out.println(isPerfectSquare(35)); // false
		System.out.println(isPerfectSquare(49)); // true
		System.out.println(isPerfectSquare(16)); // true
		System.out.println(isPerfectSquare(14)); // false
		System.out.println(isPerfectSquare(Integer.MAX_VALUE)); // false
		System.out.println(isPerfectSquare(2147395600)); // true, 46340^2
	}
}