package com.svetanis.algorithms.string;

// 2259. Remove Digit From Number to Maximize Result

public final class RemoveDigitToMaximizeResult {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static String removeDigit(String num, char digit) {
		int index = -1;
		int len = num.length();
		for (int i = 0; i < len; i++) {
			char c = num.charAt(i);
			if (c == digit) {
				index = i;
				if (i + 1 < len && c < num.charAt(i + 1)) {
					break;
				}
			}

		}
		return num.substring(0, index) + num.substring(index + 1);
	}

	public static void main(String[] args) {
		System.out.println(removeDigit("123", '3')); // 12
		System.out.println(removeDigit("1231", '1')); // 231
		System.out.println(removeDigit("551", '5')); // 51
	}
}
