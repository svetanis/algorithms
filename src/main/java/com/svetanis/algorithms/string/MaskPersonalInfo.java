package com.svetanis.algorithms.string;

// 831. Masking Personal Information

public final class MaskPersonalInfo {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String maskPII(String s) {
		if (s.contains("@")) {
			return emailMask(s);
		}
		String phone = phone(s);
		return phoneMask(phone);
	}

	private static String phoneMask(String s) {
		int ccl = s.length() - 10;
		String mask = "***-***-" + s.substring(s.length() - 4);
		if (ccl == 0) {
			return mask;
		} else {
			return "+" + "*".repeat(ccl) + "-" + mask;
		}
	}

	private static String phone(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	private static String emailMask(String s) {
		String lcs = s.toLowerCase();
		int index = s.indexOf('@');
		return lcs.charAt(0) + "*****" + lcs.substring(index - 1);
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
