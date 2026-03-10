package com.svetanis.algorithms.prefixsum;

// 848. Shifting Letters

public final class ShiftingLetters {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String shiftingLetters(String s, int[] shifts) {
		int n = shifts.length;
		long total = 0;
		char[] chars = s.toCharArray();
		for (int i = n - 1; i >= 0; i--) {
			total += shifts[i];
			int index = (int) ((chars[i] - 'a' + total) % 26);
			chars[i] = (char) (index + 'a');
		}
		return String.valueOf(chars);
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 5, 9 };
		System.out.println(shiftingLetters("abc", a1)); // rpl

		int[] a2 = { 1, 2, 3 };
		System.out.println(shiftingLetters("aaa", a2)); // gfd
	}
}
