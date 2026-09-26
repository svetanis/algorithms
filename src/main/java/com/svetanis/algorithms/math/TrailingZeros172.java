package com.svetanis.algorithms.math;

// 172. Factorial Trailing Zeroes

// A trailing zero is a factor 10 = 2 * 5. Every second number holds a 2,
// only every fifth a 5, so the 5s run out first: count the 5s in n!.
// Multiples of 5 hold one 5, multiples of 25 a second, of 125 a third:
//   zeros = n/5 + n/25 + n/125 + ...
// 100! -> 20 + 4 = 24

public final class TrailingZeros172 {
	// Time Complexity: O(log5(n))
	// Space Complexity: O(1)

	// dividing n by 5 twice is dividing it by 25 once: 100 -> 20 -> 4,
	// so n itself walks through n/5, n/25, n/125, ... and stops at 0
	public static int trailingZeros(int n) {
		int count = 0;
		while (n > 0) {
			n /= 5;
			count += n;
		}
		return count;
	}

	// the same sum with the divisor spelled out: 5, 25, 125, ...
	public static int trailingZerosByDivisor(int n) {
		int count = 0;
		// long: for n near Integer.MAX_VALUE the next divisor,
		// 5^14 = 6103515625, does not fit in an int and wraps
		// to a smaller number that still passes the test below
		long divisor = 5;
		while (n / divisor >= 1) { // ">= 1": a quotient of exactly 1 still counts
			count += n / divisor;
			divisor *= 5;
		}
		return count;
	}

	public static int dfs(int n) {
		if (n == 0) {
			return 0;
		}
		return n / 5 + dfs(n / 5);
	}

	public static void main(String[] args) {
		System.out.println(trailingZeros(3)); // 0
		System.out.println(trailingZeros(5)); // 1
		System.out.println(trailingZeros(0)); // 0

		System.out.println(dfs(3)); // 0
		System.out.println(dfs(5)); // 1
		System.out.println(dfs(0)); // 0

		System.out.println(trailingZerosByDivisor(3)); // 0
		System.out.println(trailingZerosByDivisor(5)); // 1
		System.out.println(trailingZerosByDivisor(0)); // 0
		System.out.println(trailingZerosByDivisor(Integer.MAX_VALUE)); // 536870902
	}
}