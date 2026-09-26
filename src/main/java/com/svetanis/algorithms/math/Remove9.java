package com.svetanis.algorithms.math;

// 660. Remove 9

// With 9 removed, the numbers left use only the digits 0..8 -- they are
// exactly the base-9 numerals, in order: 1, 2, ..., 8, 10, 11, ... So the
// n-th one is n written in base 9, read as if it were decimal.
//   9 in base 9 is "10", so the 9th number is 10

public final class Remove9 {
	// Time Complexity: O(log n), one base-9 digit per pass
	// Space Complexity: O(1)

	public static int newInteger(int n) {
		int base = 1;
		int result = 0;
		while (n > 0) {
			result += (n % 9) * base;
			base *= 10;
			n /= 9;
		}
		return result;
	}

	// the same base-9 digits, collected as a String
	public static int newIntegerByString(int n) {
		String s = "";
		while (n > 0) {
			s = (n % 9) + s;
			n /= 9;
		}
		return Integer.parseInt(s);
	}

	// the brute force: count up, skipping any number with a 9 in it.
	// O(answer) -- too slow for LeetCode at n up to 8 * 10^8
	public static int newIntegerCountingUp(int n) {
		int num = 0;
		int count = 0;
		while (count < n) {
			num++;
			if (!Integer.toString(num).contains("9")) {
				count++;
			}
		}
		return num;
	}

	public static void main(String[] args) {
		System.out.println(newInteger(9)); // 10
		System.out.println(newInteger(10)); // 11
		System.out.println(newIntegerByString(9)); // 10
		System.out.println(newIntegerCountingUp(9)); // 10
	}
}