package com.svetanis.algorithms.string.substr;

// 1180. Count Substrings with Only One Distinct Letter
//
// Input: a string s. Returns the number of substrings that use only one distinct letter; a
// substring that occurs at several places counts once for each place. On LeetCode:
// 1 <= s.length <= 1000, lowercase letters.
//
// The one idea: cut s into runs of one repeated letter ("aaaba" is the runs aaa, b, a). A
// one-letter substring lies inside exactly one run. A run of length L holds L substrings of length
// 1, L - 1 of length 2, and so on down to 1 of length L: L * (L + 1) / 2 in all. The answer is
// that number added up over the runs.
//
// Time: O(n) -- the pointer left jumps to the end of each run, so every letter is read once.
// Space: O(1) -- three counters.

public final class CountSubStrsOneDistinctLetter {

	public static int countLetters(String s) {
		int left = 0;
		int total = 0;
		int n = s.length();
		while (left < n) {
			int right = left;
			while (right < n && s.charAt(left) == s.charAt(right)) { // WALK: to the end of the run
				right += 1;
			}
			int len = right - left; // the run is [left, right - 1]
			total += len * (len + 1) / 2; // COUNT: len + (len - 1) + ... + 1 substrings in the run
			left = right; // SKIP: to the start of the next run
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(countLetters("aaaba")); // 8
		System.out.println(countLetters("aaaaaaaaaa")); // 55
	}
}
