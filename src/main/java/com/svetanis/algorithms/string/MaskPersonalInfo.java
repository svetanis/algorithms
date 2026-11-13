package com.svetanis.algorithms.string;

// 831. Masking Personal Information
//
// Input: s -- either an email address (letters, then '@', then a domain with a dot) or a phone
// number (10 to 13 digits, mixed with the characters + - ( ) and spaces). Returns it masked.
// An email becomes its lowercase form with everything between the first and last letter of the
// name replaced by "*****". A phone number becomes "***-***-" and its last 4 digits, with
// "+" followed by one '*' per extra digit and a '-' in front when there are more than 10 digits.
// On LeetCode: 8 <= s.length <= 40.
//
// The one idea: the '@' tells the two kinds apart. For an email, the name ends just before the
// '@', so the result is the first letter, "*****", and the text from the last letter of the
// name on. For a phone number, drop every non-digit; the last 10 digits are the local number
// and any digits before them are the country code, whose length is digits - 10.
//
// Time: O(n) -- one pass to lowercase or to pick the digits.
// Space: O(n) -- the copy.

public final class MaskPersonalInfo {

	public static String maskPII(String s) {
		if (s.contains("@")) { // KIND: an email has an '@'
			return emailMask(s);
		}
		String phone = phone(s);
		return phoneMask(phone);
	}

	private static String phoneMask(String s) {
		int ccl = s.length() - 10; // ccl: country code length, the digits before the last 10
		String mask = "***-***-" + s.substring(s.length() - 4);
		if (ccl == 0) {
			return mask;
		} else {
			return "+" + "*".repeat(ccl) + "-" + mask; // COUNTRY: one '*' per digit
		}
	}

	private static String phone(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) { // KEEP: digits only
				sb.append(c);
			}
		}
		return sb.toString();
	}

	private static String emailMask(String s) {
		String lcs = s.toLowerCase();
		int index = s.indexOf('@'); // index: the '@'; the name ends at index - 1
		return lcs.charAt(0) + "*****" + lcs.substring(index - 1); // RETURN: first letter, stars, last letter on
	}

	public static void main(String[] args) {
		String s1 = "LeetCode@LeetCode.com";
		System.out.println(maskPII(s1)); // l*****e@leetcode.com

		String s2 = "AB@qq.com";
		System.out.println(maskPII(s2)); // a*****b@qq.com

		String s3 = "1(234)567-890";
		System.out.println(maskPII(s3)); // ***-***-7890
	}
}
