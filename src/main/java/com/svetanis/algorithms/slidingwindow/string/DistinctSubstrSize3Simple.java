package com.svetanis.algorithms.slidingwindow.string;

// 1876. Substrings of Size Three with Distinct Characters

public final class DistinctSubstrSize3Simple {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int count(String s) {
		int count = 0;
		for (int i = 0; i < s.length() - 2; i++) {
			char first = s.charAt(i);
			char second = s.charAt(i + 1);
			char third = s.charAt(i + 2);
			if (first != second && first != third && second != third) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		String s1 = "xyzzaz";
		System.out.println(count(s1)); // 1

		String s2 = "aababcabc";
		System.out.println(count(s2)); // 4

		String s3 = "owuxoelszb";
		System.out.println(count(s3)); // 8
	}
}
