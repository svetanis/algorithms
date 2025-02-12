package com.svetanis.algorithms.string;

// 434. Number of Segments in a String

public final class CountSegments {
	// Time Complexity: O(n)

	public static int countSegments(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(countSegments("Hello, my name is John")); // 5
		System.out.println(countSegments("Hello")); // 1
		System.out.println(countSegments("                ")); // 0
	}
}
