package com.svetanis.algorithms.string;

// 468. Validate IP Address
//
// Input: a string queryIP. An IPv4 address is four numbers 0..255 separated by '.', each written
// in digits with no leading zero (a single "0" is fine). An IPv6 address is eight groups of 1 to 4
// hexadecimal digits (either case) separated by ':'; leading zeros are allowed. Returns "IPv4",
// "IPv6" or "Neither". On LeetCode: 1 <= queryIP.length <= 100, queryIP has letters, digits, '.'
// and ':'.
//
// The one idea: the separator tells which kind to test -- a '.' means try IPv4, a ':' means try
// IPv6 -- and then split on the separator and test each piece on its own. A piece is one number
// (IPv4) or one group (IPv6). split drops empty pieces at the end, so a trailing separator, which
// would make an empty last piece, is rejected by its own check; empty pieces inside the string
// show up as empty strings and fail the piece test.
//
// Time: O(n) -- every character is looked at a constant number of times.
// Space: O(n) -- the pieces from split.

public final class ValidateIPAddress {

	public static String validate(String s) {
		if (s.contains(".") && isV4(s)) { // TRY: four numbers between dots
			return "IPv4";
		} else if (s.contains(":") && isV6(s)) { // TRY: eight hex groups between colons
			return "IPv6";
		}
		return "Neither";
	}

	private static boolean isV6(String s) {
		String[] tokens = s.split(":");
		if (tokens.length != 8) { // REJECT: not exactly eight groups
			return false;
		}
		if (s.endsWith(":")) { // REJECT: split dropped an empty last group
			return false;
		}
		for (String token : tokens) {
			if (!validV6(token)) {
				return false;
			}
		}
		return true;
	}

	private static boolean validV6(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}
		int len = s.length();
		if (len == 0 || len > 4) { // REJECT: a group is 1 to 4 digits
			return false;
		}
		for (char c : s.toCharArray()) { // CHECK: every character is a hexadecimal digit
			if (!Character.toString(c).matches("[0-9a-fA-F]")) {
				return false;
			}
		}
		return true;
	}

	private static boolean isV4(String s) {
		String[] tokens = s.split("\\.");
		if (tokens.length != 4) { // REJECT: not exactly four numbers
			return false;
		}
		if (s.endsWith(".")) { // REJECT: split dropped an empty last number
			return false;
		}
		for (String token : tokens) {
			if (!validV4(token)) {
				return false;
			}
		}
		return true;
	}

	private static boolean validV4(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}
		for (char c : s.toCharArray()) { // CHECK: digits only
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		if (s.length() > 1 && s.startsWith("0")) { // REJECT: a leading zero
			return false;
		}
		try { // REJECT below: a very long run of digits does not fit an int, and is too big anyway
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		int val = Integer.parseInt(s);
		return val >= 0 && val <= 255; // RANGE: 0 to 255
	}

	public static void main(String[] args) {
		System.out.println(validate("172.16.254.1")); // IPv4
		System.out.println(validate("2001:0db8:85a3:0:0:8A2E:0370:7334")); // IPv6
		System.out.println(validate("256.256.256.256")); // Neither
		System.out.println(validate("2001:0db8:85a3:0:0:8A2E:0370:7334:")); // Neither
		System.out.println(validate("1.1.1.1.")); // Neither
		System.out.println(validate("12..33.4")); // Neither
		System.out.println(validate("20EE:FGb8:85a3:0:0:8A2E:0370:7334")); // Neither
	}
}
