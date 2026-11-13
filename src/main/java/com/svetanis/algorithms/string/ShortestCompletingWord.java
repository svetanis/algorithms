package com.svetanis.algorithms.string;

// 748. Shortest Completing Word
//
// Input: a license plate string (digits, letters and spaces, at least one letter) and an array of
// words. A word completes the plate if it holds every letter of the plate at least as many times
// as the plate does; case is ignored on the plate, digits and spaces are ignored. Returns the
// shortest completing word; on a tie, the one that comes first in the array. On LeetCode:
// 1 <= licensePlate.length <= 7, 1 <= words.length <= 1000, 1 <= words[i].length <= 15, words are
// lowercase letters, and an answer exists.
//
// The one idea: count how many times each of the 26 letters appears in the plate; that is the
// need. One cell of a count array is one letter's count. A word completes the plate when, for
// every letter, its own count is at least the need. Check the words in order and keep the best:
// a word only replaces the best if it is strictly shorter, so a tie keeps the earlier word.
//
// Time: O(m + k * (L + 26)) -- m plate characters; for each of k words, count its L letters and
// compare 26 cells.
// Space: O(1) -- two arrays of 26.

public final class ShortestCompletingWord {

	public static String scw(String license, String[] words) {
		String s = "";
		int len = Integer.MAX_VALUE;
		int[] count = count(license); // NEED: letters the plate asks for
		for (String word : words) {
			if (len <= word.length()) { // SKIP: not strictly shorter, so cannot replace the best
				continue;
			}
			if (check(word, count)) { // TAKE: completes the plate and is the shortest so far
				s = word;
				len = word.length();
			}
		}
		return s;
	}

	private static boolean check(String s, int[] count) {
		int[] a = count(s);
		for (int i = 0; i < 26; i++) {
			if (count[i] > a[i]) { // the word has too few of this letter
				return false;
			}
		}
		return true;
	}

	private static int[] count(String s) {
		int[] a = new int[26];
		for (char c : s.toLowerCase().toCharArray()) {
			if (Character.isLetter(c)) { // SKIP: digits and spaces
				a[c - 'a']++; // COUNT this letter
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
