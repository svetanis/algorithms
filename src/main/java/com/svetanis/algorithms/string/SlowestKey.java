package com.svetanis.algorithms.string;

// 1629. Slowest Key

public final class SlowestKey {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static char slowestKey(int[] release, String keys) {
		int n = release.length;
		char slowestKey = 'a';
		int max = 0;
		int press = 0;
		int duration = 0;
		for (int i = 0; i < n; i++) {
			char key = keys.charAt(i);
			press = i == 0 ? 0 : release[i - 1];
			duration = release[i] - press;
			if (duration > max) {
				max = duration;
				slowestKey = key;
			} else if (duration == max) {
				slowestKey = slowestKey < key ? key : slowestKey;
			}
		}
		return slowestKey;
	}

	public static void main(String[] args) {
		int[] a1 = { 9, 29, 49, 50 };
		System.out.println(slowestKey(a1, "cbcd")); // c
		int[] a2 = { 12, 23, 36, 46, 62 };
		System.out.println(slowestKey(a2, "spuda")); // a
	}
}
