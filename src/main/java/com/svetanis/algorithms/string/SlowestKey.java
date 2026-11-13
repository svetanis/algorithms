package com.svetanis.algorithms.string;

// 1629. Slowest Key
//
// Input: release[i] is the time key i of the string keys was released; key i was pressed the
// moment key i - 1 was released (key 0 at time 0), so key i was held for release[i] - release[i-1].
// Returns the key held longest; on a tie, the largest letter. On LeetCode: 2 <= n <= 1000,
// keys is lowercase letters, 1 <= release[i] <= 10^9, release is strictly increasing.
//
// The one idea: one pass, remembering the longest hold so far (max) and the key that had it. A key
// held longer replaces the answer; a key held equally long replaces it only if its letter is
// larger. Every hold is at least 1, so starting with max = 0 means the first key always replaces
// the placeholder 'a'.
//
// Time: O(n) -- one pass.
// Space: O(1) -- a few variables.

public final class SlowestKey {

	public static char slowestKey(int[] release, String keys) {
		int n = release.length;
		char slowestKey = 'a';
		int max = 0;
		int press = 0;
		int duration = 0;
		for (int i = 0; i < n; i++) { // WALK: key by key
			char key = keys.charAt(i);
			press = i == 0 ? 0 : release[i - 1]; // PRESS time: the previous release
			duration = release[i] - press;
			if (duration > max) { // TAKE: held longer
				max = duration;
				slowestKey = key;
			} else if (duration == max) { // TIE: the larger letter wins
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
