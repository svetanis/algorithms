package com.svetanis.algorithms.string;

import com.svetanis.java.base.utils.Print;

// 806. Number of Lines To Write String

public final class CountLines {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int[] lines(int[] width, String s) {
		int sum = 0;
		int count = 1;
		for (char c : s.toCharArray()) {
			int len = width[c - 'a'];
			if (sum + len > 100) {
				count++;
				sum = len;
			} else {
				sum += len;
			}
		}
		return new int[] { count, sum };
	}

	public static void main(String[] args) {
		int[] width1 = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10 };
		String s1 = "abcdefghijklmnopqrstuvwxyz";
		Print.print(lines(width1, s1)); // 3 60

		int[] width2 = { 4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10 };
		String s2 = "bbbcccdddaaa";
		Print.print(lines(width2, s2)); // 2 4
	}
}
