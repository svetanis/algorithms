package com.svetanis.algorithms.string.sort;

// 791. Custom Sort String

public final class CustomSortSubmit {
	// Time Complexity: O(n + m)
	// Space Complexity: O(n)

	public static String customSort(String order, String s) {
		int[] frequency = frequency(s);
		StringBuilder sb = new StringBuilder();
		for (char c : order.toCharArray()) {
			while (frequency[c - 'a'] > 0) {
				sb.append(c);
				frequency[c - 'a']--;
			}
		}
		for (int i = 0; i < 26; i++) {
			while (frequency[i] > 0) {
				char c = (char) (i + 'a');
				sb.append(c);
				frequency[i]--;
			}
		}
		return sb.toString();
	}

	private static int[] frequency(String s) {
		int[] frequency = new int[26];
		for (int i = 0; i < s.length(); i++) {
			frequency[s.charAt(i) - 'a']++;
		}
		return frequency;
	}

	public static void main(String[] args) {
		System.out.println(customSort("cba", "abcd")); // cbad
		System.out.println(customSort("bcafg", "abcd")); // bcad
	}
}
