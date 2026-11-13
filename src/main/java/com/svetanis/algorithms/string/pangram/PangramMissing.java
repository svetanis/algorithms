package com.svetanis.algorithms.string.pangram;

import java.util.ArrayList;
import java.util.List;

// Missing letters of a sentence
//
// Input: a sentence, and the list of letters to look for (as one-character strings).
// Returns: the letters of the list that the sentence does not contain, joined into one
// string in the order of the list; case is ignored. With the alphabet a-z as the list,
// an empty result means the sentence is a pangram.
//
// The one idea: lower the sentence once, then ask for each wanted letter whether the
// sentence contains it. The letters that are not found are the answer.
//
// Time: O(k * n) -- k wanted letters, each contains() scans the n characters of the sentence.
// Space: O(k) for the list of missing letters.
//
// Siblings: IsPangram -- the same question answered yes or no, with one flag per letter and
// a single pass over the sentence.

public final class PangramMissing {

	public static String missing(String str, List<String> list) {
		int n = list.size();
		str = str.toLowerCase(); // LOWER the case once
		List<String> missing = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (!str.contains(list.get(i))) { // SKIP letters the sentence holds
				missing.add(list.get(i)); // RECORD a missing letter, in list order
			}
		}
		return String.join("", missing);
	}

	public static void main(String[] args) {
		List<String> list = alphabet();
		String str1 = "A quick brown fox jumps over the lazy dog";
		System.out.println(missing(str1, list)); // (empty line)

		String str2 = "A slow yellow fox crawls under the proactive dog";
		System.out.println(missing(str2, list)); // bjkmqz

		String str3 = "Lions, and tigers, and bears, oh my!";
		System.out.println(missing(str3, list)); // cfjkpquvwxz

		String str4 = "";
		System.out.println(missing(str4, list)); // abcdefghijklmnopqrstuvwxyz
	}

	private static List<String> alphabet() {
		List<String> list = new ArrayList<>();
		for (char c = 'a'; c <= 'z'; c++) {
			list.add(String.valueOf(c));
		}
		return list;
	}
}