package com.svetanis.algorithms.math.conversion;

// 1017. Convert to Base -2

public final class ConvertToBaseNegative2 {
	// Time Complexity: O(log n)

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