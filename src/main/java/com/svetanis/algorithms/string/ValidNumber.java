package com.svetanis.algorithms.string;

// 65. Valid Number

public final class ValidNumber {
	// Time Complexity: O(n)

	public static boolean validate(String s) {
		int n = s.length();
		int index = 0;
		char first = s.charAt(index);
		if (first == '+' || first == '-') {
			index++;
		}
		if (index == n) {
			return false;
		}
		char second = s.charAt(index);
		int next = index + 1;
		char cc = next < n ? s.charAt(next) : ' ';
		if (second == '.' && (next == n || cc == 'e' || cc == 'E')) {
			return false;
		}
		int dots = 0;
		int ecnt = 0;
		for (int i = index; i < n; i++) {
			char c = s.charAt(i);
			if (c == '.') {
				// second dot or e/E before the dot
				if (dots > 0 || ecnt > 0) {
					return false;
				}
				dots++;
			} else if (c == 'e' || c == 'E') {
				// multiple e/E or e/E at start/end or after a sign
				if (ecnt > 0 || i == index || i == n - 1) {
					return false;
				}
				ecnt++;
				// sign immediately after e/E
				if (s.charAt(i + 1) == '+' || s.charAt(i + 1) == '-') {
					if (++i == n - 1) {
						return false;
					}
				}
			} else if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(validate("0")); // true
		System.out.println(validate("0089")); // true
		System.out.println(validate("-0.1")); // true
		System.out.println(validate("+3.14")); // true
		System.out.println(validate("4.")); // true
		System.out.println(validate("-.9")); // true
		System.out.println(validate("2e10")); // true
		System.out.println(validate("-90E3")); // true
		System.out.println(validate("3e+7")); // true
		System.out.println(validate("+6e-1")); // true
		System.out.println(validate("53.5e93")); // true
		System.out.println(validate("-123.456e789")); // true

		System.out.println(validate("e")); // false
		System.out.println(validate(".")); // false
		System.out.println(validate("abc")); // false
		System.out.println(validate("1a")); // false
		System.out.println(validate("1e")); // false
		System.out.println(validate("e3")); // false
		System.out.println(validate("99e2.5")); // false
		System.out.println(validate("--6")); // false
		System.out.println(validate("-+3")); // false
		System.out.println(validate("95a54e53")); // false
		System.out.println(validate("+.")); // false
	}
}
