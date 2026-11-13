package com.svetanis.algorithms.string;

// 65. Valid Number
//
// Input: a string s. A valid number is a decimal or an integer, optionally followed by an
// exponent. A decimal is an optional sign and one of: digits then '.', digits then '.' then
// digits, or '.' then digits. An integer is an optional sign and digits. An exponent is 'e' or 'E'
// and then an integer. Returns true when s is a valid number. On LeetCode: 1 <= s.length <= 20,
// s holds letters, digits, '+', '-' and '.'.
//
// The one idea: skip one optional sign, then scan once and keep two flags: a dot is allowed only
// before any 'e' and only once; an 'e' is allowed once, never first, never last, and may be
// followed by one sign (which then needs a digit after it). Every other character must be a digit.
// What the flags cannot see is a dot with no digit next to it, and that can only be a leading dot
// (anything after the sign that is not a dot or 'e' is a digit), so it is checked up front: a
// leading '.' needs a digit after it -- not the end of the string, not an 'e'.
//
// Time: O(n) -- one pass.
// Space: O(1) -- a few variables.

public final class ValidNumber {

	public static boolean validate(String s) {
		int n = s.length();
		int index = 0;
		char first = s.charAt(index);
		if (first == '+' || first == '-') { // SKIP: one optional sign
			index++;
		}
		if (index == n) { // a sign alone
			return false;
		}
		char second = s.charAt(index);
		int next = index + 1;
		char cc = next < n ? s.charAt(next) : ' ';
		if (second == '.' && (next == n || cc == 'e' || cc == 'E')) { // a leading dot with no digit after it
			return false;
		}
		int dots = 0;
		int ecnt = 0;
		for (int i = index; i < n; i++) { // WALK: every character after the sign
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
					if (++i == n - 1) { // SKIP the sign; it must not be the last character
						return false;
					}
				}
			} else if (c < '0' || c > '9') { // anything else must be a digit
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
