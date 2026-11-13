package com.svetanis.algorithms.string;

// 482. License Key Formatting
//
// Input: a license key s of letters, digits and dashes, and a group size k. Returns the key in
// uppercase with the dashes removed and then put back so that every group has exactly k
// characters except the first, which has between 1 and k characters. An all-dash key gives "".
// On LeetCode: 1 <= s.length <= 10^5, 1 <= k <= 10^4.
//
// The one idea: after removing the dashes, the groups of k are counted from the right, so only
// the first group is short. Its size is the remainder length % k (or k itself when the remainder
// is 0). Then walk the characters left to right: count how many are in the current group, and
// when the count reaches the group size, write a dash and start a group of k.
//
// Time: O(n) -- the string is flattened once and written once.
// Space: O(n) -- the flattened copy and the result.

public final class LicenseKeyFormatting {

	public static String lkf(String s, int k) {
		String flat = s.replace("-", "").toUpperCase();
		int len = flat.length() % k; // FIRST GROUP: the short one, size = the remainder
		len = len == 0 ? k : len; // no remainder: the first group is full too
		int count = 0; // count: characters written in the current group
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < flat.length(); i++) {
			if (count == len) { // CLOSE: the group is full
				sb.append("-");
				count = 0;
				len = k; // every later group has k
			}
			sb.append(flat.charAt(i)); // TAKE: one character
			count++;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(lkf("5F3Z-2e-9-w", 4)); // 5F3Z-2E9W
		System.out.println(lkf("2-5g-3-J", 2)); // 2-5G-3J
	}
}
