package com.svetanis.algorithms.slidingwindow.string;

import java.util.Arrays;

// 1358. Number of Substrings Containing All Three Characters

public final class CountSubStrs3Chars {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int countSubStrs(String s) {
		int[] counts = new int[3];
		int total = 0;
		int left = 0;
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			counts[c - 'a']++;
			while (hasAllChars(counts)) {
				total += s.length() - right;
				char lc = s.charAt(left++);
				counts[lc - 'a']--;
			}
		}
		return total;
	}

	private static boolean hasAllChars(int[] counts) {
		return counts[0] > 0 && counts[1] > 0 && counts[2] > 0;
	}

	public static int countSubStrs2(String s) {
		int[] counts = new int[3];
		Arrays.fill(counts, -1);
		int total = 0;
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			counts[c - 'a'] = right;
			int left = Math.min(counts[0], Math.min(counts[1], counts[2]));
			total += left + 1;
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(countSubStrs("abcabc")); // 10
		System.out.println(countSubStrs("aaacb")); // 3
		System.out.println(countSubStrs("abc")); // 1
	}
}
