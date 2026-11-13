package com.svetanis.algorithms.string;

// 8. String to Integer (atoi)
//
// Input: a string s. Read it as LeetCode specifies: skip leading spaces, take an optional '+' or
// '-', then read digits until the first non-digit; no digits means 0. Clamp the result to the
// 32-bit signed range. Returns that integer. On LeetCode: 0 <= s.length <= 200, s holds letters,
// digits, ' ', '+', '-' and '.'.
//
// The one idea: build the number digit by digit as result = result * 10 + digit, but never let it
// overflow an int. The largest int is 2147483647, so before appending a digit, result is checked
// against threshold = MAX / 10 = 214748364: above it, the next digit overflows for sure; equal
// to it, the digit overflows if it is above 7 (the last digit of MAX). For a negative number, a
// last digit of 8 gives exactly MIN, and the clamp to MIN is the same value, so one test
// serves both signs.
//
// Time: O(n) -- one pass.
// Space: O(1) -- a few variables.

public final class StringToIntegerAtoi {

	public static int atoi(String s) {
		if (s == null) {
			return 0;
		}
		int len = s.length();
		if (len == 0) {
			return 0;
		}
		int index = 0;
		while (index < len && s.charAt(index) == ' ') { // SKIP: leading spaces
			index++;
		}
		if (index == len) {
			return 0;
		}
		int sign = 1;
		if (s.charAt(index) == '-') { // SIGN: one optional sign
			sign = -1;
			index++;
		} else if (s.charAt(index) == '+') {
			index++;
		}

		int result = 0;
		int threshold = Integer.MAX_VALUE / 10;
		while (index < len) { // WALK: the digits
			char c = s.charAt(index);
			// skip non-digits
			if (c < '0' || c > '9') { // stop at the first non-digit
				break;
			}
			// check for overflow
			if (result > threshold || (result == threshold && c > '7')) { // CLAMP: the next digit overflows
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			// update result with new digit
			result = result * 10 + (c - '0'); // TAKE this digit
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
		System.out.println(atoi("-91283472332")); // -2147483648
		System.out.println(atoi("2147483647")); // 2147483647
		System.out.println(atoi("-2147483648")); // -2147483648
	}
}
