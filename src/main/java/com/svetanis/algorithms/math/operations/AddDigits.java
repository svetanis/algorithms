package com.svetanis.algorithms.math.operations;

// 258. Add Digits

public final class AddDigits {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int addDigits(int n) {
		int sum = 0;
		while (n > 0) {
			sum += n % 10;
			n = n / 10;
			if (n == 0 && sum > 9) {
				n = sum;
				sum = 0;
			}
		}
		return sum;
	}

	public static int addDigits2(int n) {
		if (n == 0) {
			return 0;
		}
		if (n % 9 == 0) {
			return 9;
		}
		return n % 9;
	}

	public static int addDigits3(int n) {
		return (n - 1) % 9 + 1;
	}

	public static void main(String[] args) {
		System.out.println(addDigits(38)); // 2
		System.out.println(addDigits(0)); // 0
	}
}