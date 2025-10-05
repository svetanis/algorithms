package com.svetanis.algorithms.string;

import static java.util.Arrays.asList;

import java.util.List;

// 422. Valid Word Square

public final class ValidWordSquare {
	// Time Complexity: O(n * m)
	// Space Complexity: O(1)

	public static boolean validWordSquare(List<String> words) {
		int n = words.size();
		for (int row = 0; row < n; row++) {
			int m = words.get(row).length();
			for (int col = 0; col < m; col++) {
				if (col >= n || row >= words.get(col).length()) {
					return false;
				}
				char c1 = words.get(row).charAt(col);
				char c2 = words.get(col).charAt(row);
				if (c1 != c2) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] agrs) {
		List<String> words = asList("AREA", "BALL", "DEAR", "LADY");
		System.out.println(validWordSquare(words)); // false
		List<String> words2 = asList("BALL", "AREA", "LEAD", "LADY");
		System.out.println(validWordSquare(words2)); // true
	}
}
