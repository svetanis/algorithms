package com.svetanis.algorithms.string;

// 748. Shortest Completing Word

public final class ShortestCompletingWord {
	// Time Complexity: O(n)

	public static String scw(String license, String[] words) {
		String s = "";
		int len = Integer.MAX_VALUE;
		int[] count = count(license);
		for (String word : words) {
			if (len <= word.length()) {
				continue;
			}
			if (check(word, count)) {
				s = word;
				len = word.length();
			}
		}
		return s;
	}

	private static boolean check(String s, int[] count) {
		int[] a = count(s);
		for (int i = 0; i < 26; i++) {
			if (count[i] > a[i]) {
				return false;
			}
		}
		return true;
	}

	private static int[] count(String s) {
		int[] a = new int[26];
		for (char c : s.toLowerCase().toCharArray()) {
			if (Character.isLetter(c)) {
				a[c - 'a']++;
			}
		}
		return a;
	}

	public static void main(String[] args) {
		String[] words1 = { "step", "steps", "stripe", "stepple" };
		System.out.println(scw("1s3 PSt", words1)); // steps
		String[] words2 = { "looks", "pest", "stew", "show" };
		System.out.println(scw("1s3 456", words2)); // pest
	}
}
