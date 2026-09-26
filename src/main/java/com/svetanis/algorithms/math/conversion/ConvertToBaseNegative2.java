package com.svetanis.algorithms.math.conversion;

// 1017. Convert to Base -2

// The places of base -2 are worth 1, -2, 4, -8, 16, ...: the same sizes as
// base 2, with every other one negative. So read the digits off the right
// end as in base 2, but keep track of the sign of the current place. When
// n is odd the digit is 1, and that 1 is worth +1 or -1 of the current
// place -- take exactly that off n (n -= sign) so what is left is even and
// halves cleanly for the next place.
//
//   n = 2   place  1: 2 is even -> digit 0                    2 / 2 = 1
//           place -2: 1 is odd  -> digit 1, n - (-1) = 2       2 / 2 = 1
//           place  4: 1 is odd  -> digit 1, n - 1 = 0          done
//   digits read from the left: 110, and 4 - 2 + 0 = 2

public final class ConvertToBaseNegative2 {
	// Time Complexity: O(log n)
	// Space Complexity: O(log n) for the digits

	public static String base2(int n) {
		if (n == 0) {
			return "0";
		}
		int sign = 1;
		StringBuilder sb = new StringBuilder();
		while (n != 0) {
			if (n % 2 != 0) {
				sb.append(1);
				n -= sign;
			} else {
				sb.append(0);
			}
			sign *= -1;
			n /= 2;
		}
		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		System.out.println(base2(2)); // 110
		System.out.println(base2(3)); // 111
		System.out.println(base2(4)); // 100
	}
}