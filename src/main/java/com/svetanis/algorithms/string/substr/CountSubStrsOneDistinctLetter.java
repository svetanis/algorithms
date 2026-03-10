package com.svetanis.algorithms.string.substr;

// 1180. Count Substrings with Only One Distinct Letter

public final class CountSubStrsOneDistinctLetter {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int countLetters(String s) {
		int left = 0;
		int total = 0;
		int n = s.length();
		while (left < n) {
			int right = left;
			while (right < n && s.charAt(left) == s.charAt(right)) {
				right += 1;
			}
			int len = right - left; // [left, right - 1]
			total += len * (len + 1) / 2; // n * (n + 1) / 2
			left = right;
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(countLetters("aaaba")); // 8
		System.out.println(countLetters("aaaaaaaaaa")); // 55
	}
}
