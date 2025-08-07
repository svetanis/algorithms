package com.svetanis.algorithms.string.pangram;

// 1832. Check if the Sentence Is Pangram

public final class IsPangramBitMask {
	// Time complexity: O(n)
	// Space complexity: O(1)

	public static boolean isPangram(String s) {
		for (char c = 'a'; c <= 'z'; c++) {
			if (s.indexOf(c) == -1) {
				return false;
			}
		}
		return true;
	}

	public static boolean isPangram2(String s) {
		int mask = 0;
		for (char c : s.toCharArray()) {
			int index = c - 'a';
			mask |= 1 << index;
		}
		return mask == (1 << 26) - 1;
	}

	public static void main(String[] args) {
		String s1 = "thequickbrownfoxjumpsoverthelazydog";
		System.out.println(isPangram(s1)); // true

		String s2 = "leetcode";
		System.out.println(isPangram(s2)); // false
	}
}