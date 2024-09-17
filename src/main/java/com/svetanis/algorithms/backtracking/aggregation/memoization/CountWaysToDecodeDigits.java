package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static java.lang.Integer.parseInt;

// given a message consisting
// of digits 0-9 to decode
// letters are encoded to digits
// by their positions in the alphabet
// how may ways are there to decode it?

public final class CountWaysToDecodeDigits {
	// Time Complexity: O(2^n)

	public static int count(String s) {
		return dfs(0, s);
	}

	private static int dfs(int index, String s) {
		if (s.startsWith("0")) {
			return 0;
		}
		if (index == s.length()) {
			return 1;
		}
		int count = 0;
		for (int i = index; i < s.length(); i++) {
			String prefix = s.substring(index, i + 1);
			if (valid(prefix)) {
				count += dfs(index + prefix.length(), s);
			}
		}
		return count;
	}

	private static boolean valid(String s) {
		int num = parseInt(s);
		return num > 0 && num <= 26;
	}

	public static void main(String args[]) {
		System.out.println(count("18")); // 2
		System.out.println(count("123")); // 3
		System.out.println(count("02")); // 0
		System.out.println(count("1221")); // 5
		System.out.println(count("101")); // 2
	}
}