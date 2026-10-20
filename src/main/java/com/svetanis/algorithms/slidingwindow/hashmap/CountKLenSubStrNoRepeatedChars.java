package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.HashMap;
import java.util.Map;

// 1100. Find K-Length Substrings With No Repeated Characters
//
// Input: a string s and a length k.
// Return: how many substrings of exactly k characters have no character twice. Each
// position counts separately, so a substring that occurs twice is counted twice.
// k <= 0 and k > s.length() return 0.
//
// countSubStrNoRepeats: a window with no repeat grows on the right one character at
// a time. When the new character is already in the window, characters leave from the
// left until its earlier copy has gone -- every substring still holding both copies
// has a repeat. When the window reaches exactly k characters it has no repeat, so it
// is one answer: count it, then let its leftmost character go so the next window
// starts one place later. The window never grows past k.
// It indexes counts by c - 'a', so s must hold only the lowercase letters 'a'..'z';
// any other character throws ArrayIndexOutOfBoundsException.
//
// countSubStrNoRepeats2: a window of exactly k characters slides one place per step,
// with a count map of its characters. A character whose count drops to 0 is removed,
// so the map has k keys exactly when all k characters are different. It takes any
// characters.
//
// Time: O(n) for both. In countSubStrNoRepeats, right and left each move forward at
// most n times, so the inner while runs at most n times in total.
// Space: O(1) for countSubStrNoRepeats -- 26 counters. O(k) for countSubStrNoRepeats2 --
// the map holds at most k characters.
//
// Siblings: DistinctSubstrSize3 and DistinctSubstrSize3Simple (slidingwindow/string)
// are 1876, the same count with k fixed at 3. LongestDistinctSubStrLen is LeetCode 3, the same
// shrinking window measuring the longest window instead of counting length-k ones.
// CountDistinctElementsInWindow slides countSubStrNoRepeats2's count map over an array
// and records the map's size for every window.

public final class CountKLenSubStrNoRepeatedChars {

	public static int countSubStrNoRepeats(String s, int k) {
		if (k <= 0 || k > s.length()) { // EARLY: no window of k characters fits
			return 0;
		}
		char[] freq = new char[26];
		int left = 0;
		int count = 0;
		for (int right = 0; right < s.length(); right++) {
			int index = s.charAt(right) - 'a';
			freq[index] += 1; // ADD
			while (freq[index] > 1) { // SHRINK while the new character repeats
				freq[s.charAt(left) - 'a'] -= 1; // OUT
				left += 1;
			}
			if (right - left + 1 == k) { // FULL: k characters, none repeated
				count += 1; // COUNT
				freq[s.charAt(left) - 'a'] -= 1; // OUT: the next window starts one place later
				left += 1;
			}
		}
		return count; // RETURN
	}

	// k <= 0 belongs in the guard, not just k > length: with k = 0 the map
	// starts empty, so map.size() == k holds at every window and this counts
	// one empty substring per position -- the sibling above returns 0
	public static int countSubStrNoRepeats2(String s, int k) {
		if (k <= 0 || k > s.length()) { // EARLY: no window of k characters fits
			return 0;
		}
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < k; i++) {
			map.merge(s.charAt(i), 1, Integer::sum); // ADD the first window
		}
		int count = map.size() == k ? 1 : 0; // COUNT the first window if all k differ
		for (int right = k; right < s.length(); right++) {
			map.merge(s.charAt(right), 1, Integer::sum); // IN
			char out = s.charAt(right - k); // OUT: the character k places back leaves
			int f = map.merge(out, -1, Integer::sum);
			if (f == 0) {
				map.remove(out); // DROP, so map.size() is the number of different characters
			}
			if (map.size() == k) { // COUNT: k keys means no repeat
				count += 1;
			}
		}
		return count; // RETURN
	}

	public static void main(String[] args) {
		System.out.println(countSubStrNoRepeats("havefunonleetcode", 5)); // 6
		System.out.println(countSubStrNoRepeats("home", 5)); // 0
		System.out.println(countSubStrNoRepeats("abcab", 0)); // 0
		System.out.println(countSubStrNoRepeats2("havefunonleetcode", 5)); // 6
		System.out.println(countSubStrNoRepeats2("home", 5)); // 0
		System.out.println(countSubStrNoRepeats2("abcab", 0)); // 0
	}
}
