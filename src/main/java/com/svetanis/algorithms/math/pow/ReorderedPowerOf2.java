package com.svetanis.algorithms.math.pow;

// 869. Reordered Power of 2

// Reordering the digits keeps how many of each digit there are, so n can
// be reordered into a power of two exactly when some power of two has the
// same digit counts. There are only 30 powers of two to try.
//
// 46 -> one 4, one 6 -> matches 64 = 2^6

public final class ReorderedPowerOf2 {
	// Time Complexity: O(30 * d), d the number of digits (at most 10)
	// Space Complexity: O(1)

	public static boolean reorderedPowerOf2(int n) {
		String count = frequency(n);
		// n <= 10^9, so at most 10 digits -- and the only 10-digit n is
		// 10^9 itself, which no power of two reorders into. 2^29 is the
		// last power of two with 9 digits; 2^30 = 1,073,741,824 stops the loop
		for (int i = 1; i <= 1_000_000_000; i <<= 1) {
			String f = frequency(i);
			if (count.equals(f)) {
				return true;
			}
		}
		return false;
	}

	// a[d] is how many times the digit d appears; the chars are used as
	// small counters, and the String lets two counts be compared with equals
	private static String frequency(int n) {
		char[] a = new char[10];
		while (n > 0) {
			a[n % 10]++;
			n /= 10;
		}
		return new String(a);
	}

	public static void main(String[] args) {
		System.out.println(reorderedPowerOf2(1)); // true
		System.out.println(reorderedPowerOf2(10)); // false
		System.out.println(reorderedPowerOf2(46)); // true
	}
}
