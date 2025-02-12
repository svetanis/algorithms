package com.svetanis.algorithms.string;

// 482. License Key Formatting

public final class LicenseKeyFormatting {
	// Time Complexity: O(n)

	public static String lkf(String s, int k) {
		String flat = s.replace("-", "").toUpperCase();
		// initial size of first group length
		int len = flat.length() % k;
		len = len == 0 ? k : len;
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < flat.length(); i++) {
			if (count == len) {
				sb.append("-");
				count = 0;
				len = k;
			}
			sb.append(flat.charAt(i));
			count++;

		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(lkf("5F3Z-2e-9-w", 4)); // 5F3Z-2E9W
		System.out.println(lkf("2-5g-3-J", 2)); // 2-5G-3J
	}
}
