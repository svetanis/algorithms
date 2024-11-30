package com.svetanis.algorithms.monotonicstack;

// 402. Remove K Digits

public final class RemoveKDigits {
	// Time Complexity: O(n)

	public static String removeKDigits(String num, int k) {
		StringBuilder sb = new StringBuilder();
		for (char c : num.toCharArray()) {
			while (sb.length() > 0 && k > 0 && sb.charAt(sb.length() - 1) > c) {
				sb.deleteCharAt(sb.length() - 1);
				k--;
			}
			sb.append(c);
		}
		while (k > 0 && sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
			k--;
		}
		// remove leading zeros
		int index = 0;
		while (index < sb.length() && sb.charAt(index) == '0') {
			index++;
		}
		String s = sb.substring(index);
		return s.isEmpty() ? "0" : s;
	}

	public static void main(String[] args) {
		System.out.println(removeKDigits("1432219", 3)); // 1219
		System.out.println(removeKDigits("10200", 1)); // 200
		System.out.println(removeKDigits("10", 2)); // 0
	}
}
