package com.svetanis.algorithms.math.operations;

// 258. Add Digits

public final class AddDigits {

	// Add the digits, and when one pass is done and the sum still has two
	// digits, start again on the sum: 38 -> 3 + 8 = 11 -> 1 + 1 = 2
	// Time Complexity: O(log n), one step per digit
	// Space Complexity: O(1)
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

	// Why 9: 10 leaves remainder 1 when divided by 9, so 10, 100, 1000, ...
	// all do, and 38 = 3 * 10 + 8 leaves the same remainder as 3 + 8. Adding
	// digits never changes the remainder after dividing by 9 -- so the final digit
	// is that remainder, except that a multiple of 9 ends at 9, not 0.
	// Time Complexity: O(1)
	// Space Complexity: O(1)
	public static int addDigitsByNine(int n) {
		if (n == 0) {
			return 0;
		}
		if (n % 9 == 0) {
			return 9;
		}
		return n % 9;
	}

	// the same rule in one line: shifting down by 1 before % 9 and back up
	// after turns remainder 0 into 9, and n = 0 gives -1 % 9 + 1 = 0
	// Time Complexity: O(1)
	// Space Complexity: O(1)
	public static int addDigitsOneLine(int n) {
		return (n - 1) % 9 + 1;
	}

	public static void main(String[] args) {
		System.out.println(addDigits(38)); // 2
		System.out.println(addDigits(0)); // 0
		System.out.println(addDigitsByNine(38)); // 2
		System.out.println(addDigitsByNine(18)); // 9
		System.out.println(addDigitsOneLine(38)); // 2
		System.out.println(addDigitsOneLine(0)); // 0
	}
}
