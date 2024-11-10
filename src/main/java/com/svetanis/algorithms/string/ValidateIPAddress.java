package com.svetanis.algorithms.string;

// 468. Validate IP Address

public final class ValidateIPAddress {

	public static String validate(String s) {
		if (s.contains(".") && isV4(s)) {
			return "IPv4";
		} else if (s.contains(":") && isV6(s)) {
			return "IPv6";
		}
		return "Neither";
	}

	private static boolean isV6(String s) {
		String[] tokens = s.split(":");
		if (tokens.length != 8) {
			return false;
		}
		if (s.endsWith(":")) {
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
		if (len == 0 || len > 4) {
			return false;
		}
		for (char c : s.toCharArray()) {
			if (!Character.toString(c).matches("[0-9a-fA-F]")) {
				// if (!Character.isLetterOrDigit(c)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isV4(String s) {
		String[] tokens = s.split("\\.");
		if (tokens.length != 4) {
			return false;
		}
		if (s.endsWith(".")) {
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
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		if (s.length() > 1 && s.startsWith("0")) {
			return false;
		}
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		int val = Integer.parseInt(s);
		return val >= 0 && val <= 255;
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
