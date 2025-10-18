package com.svetanis.algorithms.string;

// 1370. Increasing Decreasing String

public final class IncDecStr {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String sortStr(String s) {
		int[] counts = counts(s);
		StringBuilder sb = new StringBuilder();
		while (sb.length() < s.length()) {
			for (int i = 0; i < 26; i++) {
				char c = (char) (i + 'a');
				if (counts[i] > 0) {
					sb.append(c);
					counts[i]--;
				}
			}
			for (int i = 25; i >= 0; i--) {
				char c = (char) (i + 'a');
				if (counts[i] > 0) {
					sb.append(c);
					counts[i]--;
				}
			}
		}
		return sb.toString();
	}

	private static int[] counts(String s) {
		int[] counts = new int[26];
		for (char c : s.toCharArray()) {
			counts[c - 'a']++;
		}
		return counts;
	}

	public static void main(String[] args) {
		System.out.println(sortStr("aaaabbbbcccc")); // abccbaabccba
		System.out.println(sortStr("rat")); // art
	}
}
