package com.svetanis.algorithms.search.binary.math;

// given an integer, find its square root
// without using the built-in square root
// function. only return the integer part
// truncate the decimals

public final class SquareRootEstimation {
	// Time Complexity: O(log n)

	public static int sqrt(int n) {
		if (n == 0) {
			return 0;
		}
		int left = 1;
		int right = n;
		int sqrt = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			double sqr = mid * mid;
			if (sqr == n) {
				return mid;
			} else if (sqr > n) {
				sqrt = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return sqrt - 1;
	}

	public static void main(String[] args) {
		System.out.println(sqrt(16));
		System.out.println(sqrt(8));
	}
}