package com.svetanis.algorithms.slidingwindow.string;

// 567. Permutation in String
//
// Input: two strings of lowercase letters, s1 and s2.
// Return: true when some substring of s2 is a rearrangement of s1 -- the same
// letters, each the same number of times; false when s1 is longer than s2.
// Any other character indexes outside the 26 counts and throws.
//
// The one idea: a rearrangement of s1 has exactly s1's length, so only windows of
// s2 of that length k can match, and a window matches exactly when its 26 letter
// counts equal s1's. Moving the window one step changes two counts -- one letter
// comes in on the right, one goes out on the left -- so the counts are kept up to
// date instead of rebuilt. Each window is compared before the move; the last one
// is compared after the loop.
//
// Siblings:
//   anagram/ContainsAnagram (also 567) takes the text first and keeps one map of
//   what the window still needs plus a matched counter, so no 26-count comparison.
//   anagram/StringAnagrams and anagram/StringAnagramsSubmit (LeetCode 438) run
//   the same window and report the start of every match instead of a yes or no.
//
// Time: O(26 * n) -- each move changes two counts, and each comparison reads 26.
// Space: O(1) -- two arrays of 26 counts.

public final class PermutationInString {

	public static boolean checkInclusion(String s1, String s2) {
		int k = s1.length();
		if (k > s2.length()) {
			return false; // RETURN: no window of s2 is long enough
		}
		int[] chars = chars(s1, k);
		int[] sw = chars(s2, k); // BUILD: counts of s2's first k letters
		for (int i = k; i < s2.length(); i++) {
			if (matches(chars, sw)) {
				return true; // RETURN: the window ending at i - 1 matches
			}
			char right = s2.charAt(i);
			sw[right - 'a']++; // IN: letter i enters
			char left = s2.charAt(i - k);
			sw[left - 'a']--; // OUT: the letter k places back leaves
		}
		return matches(chars, sw); // RETURN: the last window, never compared inside the loop
	}

	private static boolean matches(int[] a1, int[] a2) {
		for (int i = 0; i < 26; i++) {
			if (a1[i] != a2[i]) {
				return false;
			}
		}
		return true;
	}

	// counts of the first k letters of s
	private static int[] chars(String s, int k) {
		int[] a = new int[26];
		for (int i = 0; i < k; i++) {
			a[s.charAt(i) - 'a']++;
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(checkInclusion("ab", "eidbaooo")); // true
		System.out.println(checkInclusion("ab", "eidboaoo")); // false
		System.out.println(checkInclusion("adc", "dcda")); // true
	}
}
