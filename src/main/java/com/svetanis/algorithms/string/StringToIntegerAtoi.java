package com.svetanis.algorithms.string;

// 8. String to Integer (atoi)

public final class StringToIntegerAtoi {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int atoi(String s) {
		if (s == null) {
			return 0;
		}
		int len = s.length();
		if (len == 0) {
			return 0;
		}
		int index = 0;
		while (index < len && s.charAt(index) == ' ') {
			index++;
		}
		if (index == len) {
			return 0;
		}
		int sign = 1;
		if (s.charAt(index) == '-') {
			sign = -1;
			index++;
		} else if (s.charAt(index) == '+') {
			index++;
		}

		int result = 0;
		int threshold = Integer.MAX_VALUE / 10;
		while (index < len) {
			char c = s.charAt(index);
			// skip non-digits
			if (c < '0' || c > '9') {
				break;
			}
			// check for overflow
			if (result > threshold || (result == threshold && c > '7')) {
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			// update result with new digit
			result = result * 10 + (c - '0');
			index++;
		}
		return sign * result;
	}

	public static void main(String[] args) {
		System.out.println(atoi("42")); // 42
		System.out.println(atoi("-042")); // -42
		System.out.println(atoi("1337c0d3")); // 1337
		System.out.println(atoi("0-1")); // 0
		System.out.println(atoi("words and 987")); // 0
	}
}
