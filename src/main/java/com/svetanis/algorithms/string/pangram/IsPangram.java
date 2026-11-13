package com.svetanis.algorithms.string.pangram;

import static java.lang.Character.toLowerCase;

// 1832. Check if the Sentence Is Pangram
//
// Input: a sentence. On LeetCode: 1 <= sentence.length <= 1000, lowercase English letters only.
// Returns: whether every letter a-z appears at least once.
//
// The one idea: one yes/no flag per letter. chars[i] is true once the letter number i
// ('a' is 0, 'z' is 25) has been seen. Read the sentence once, switch on the flag of every
// letter, then the sentence is a pangram exactly when all 26 flags are on.
//
// The methods:
//   isPangram                -- any character may appear: upper case is lowered, everything
//                               else is ignored
//   isPangramLettersOnly     -- letters only, either case counts as the same letter; any
//                               other character throws IllegalArgumentException
//
// Time: O(n) -- one pass over the sentence, then 26 flags.
// Space: O(1) -- 26 flags, however long the sentence is.
//
// Siblings: IsPangramBitMask -- the same 26 flags kept as the bits of one int, and an
// indexOf version that needs no flags.

public final class IsPangram {

	private static int N = 26;

	public static boolean isPangram(String s) {
		int n = s.length();
		boolean[] chars = new boolean[N]; // FLAGS: chars[i] -- letter number i was seen
		for (int i = 0; i < n; i++) {
			int index = toLowerCase(s.charAt(i)) - 'a';
			if (index >= 0 && index < N) { // SKIP what is not a letter
				chars[index] = true; // RECORD: letter seen
			}
		}
		for (int i = 0; i < N; i++) {
			if (!chars[i]) {
				return false; // RETURN false: this letter never appeared
			}
		}
		return true; // RETURN true: all 26 flags are on
	}

	public static boolean isPangramLettersOnly(String s) {
		int n = s.length();
		boolean[] chars = new boolean[N]; // FLAGS: chars[i] -- letter number i was seen

		for (int i = 0; i < n; i++) {
			int index = 0;
			char c = s.charAt(i);
			if ('A' <= c && c <= 'Z') {
				index = c - 'A';
			} else if ('a' <= c && c <= 'z') {
				index = c - 'a';
			} else {
				throw new IllegalArgumentException(String.format("not valid char %s", c)); // REJECT: not a letter
			}
			chars[index] = true; // RECORD: letter seen
		}

		for (int i = 0; i < N; i++) {
			if (!chars[i]) {
				return false; // RETURN false: this letter never appeared
			}
		}
		return true; // RETURN true: all 26 flags are on
	}

	private static String strict(String s) {
		try {
			return String.valueOf(isPangramLettersOnly(s));
		} catch (IllegalArgumentException e) {
			return "IllegalArgumentException";
		}
	}

	public static void main(String[] args) {
		String str1 = "A quick brown fox jumps over the lazy dog";
		System.out.println(isPangram(str1)); // true
		System.out.println(strict(str1)); // IllegalArgumentException

		String str2 = "A slow yellow fox crawls under the proactive dog";
		System.out.println(isPangram(str2)); // false
		System.out.println(strict(str2)); // IllegalArgumentException

		String str3 = "Lions, and tigers, and bears, oh my!";
		System.out.println(isPangram(str3)); // false
		System.out.println(strict(str3)); // IllegalArgumentException

		String str4 = "";
		System.out.println(isPangram(str4)); // false
		System.out.println(strict(str4)); // false

		String str5 = "thequickbrownfoxjumpsoverthelazydog";
		System.out.println(isPangram(str5)); // true
		System.out.println(strict(str5)); // true

		String str6 = "leetcode";
		System.out.println(isPangram(str6)); // false
		System.out.println(strict(str6)); // false
	}
}