package com.svetanis.algorithms.string;

// 2047. Number of Valid Words in a Sentence
//
// Input: a sentence; tokens are separated by one or more spaces. A token is a valid word if it
// has (1) no digit, (2) at most one hyphen, and a hyphen has a lowercase letter on both sides, and
// (3) at most one of '!', '.', ',' and only as the token's last character. Returns the number of
// valid tokens. On LeetCode: 1 <= sentence.length <= 1000; it holds lowercase letters, digits,
// ' ', '-', '!', '.', ','; there is at least one token.
//
// The one idea: split on single spaces and test each token with its own method. Splitting on one
// space gives empty strings where spaces repeat; an empty token is not a word, so it is skipped
// in the test, which keeps the count right. The test walks the token once: a digit fails at once;
// a punctuation mark fails unless it is the last character (which also limits it to one); a
// hyphen fails if it is a second one, at either end, or has a non-letter beside it.
//
// Time: O(n) -- every character is looked at a constant number of times
// Space: O(n) -- the array of tokens

public final class CountValidWords {

	public static int countValidWords(String s) {
		int count = 0;
		for (String token : s.split(" ")) { // SPLIT: repeated spaces leave empty tokens
			if (valid(token)) {
				count++;
			}
		}
		return count;
	}

	private static boolean valid(String s) {
		int len = s.length();
		if (len == 0) {
			return false; // SKIP: an empty token between two spaces
		}
		boolean hasHyphen = false;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				return false; // REJECT: no digits
			}
			boolean pc = c == '!' || c == '.' || c == ',';
			if (i < len - 1 && pc) {
				return false; // REJECT: punctuation only as the last character
			}
			if (c == '-') {
				if (hasHyphen || i == 0 || i == len - 1
						|| !Character.isLetter(s.charAt(i - 1))
						|| !Character.isLetter(s.charAt(i + 1))) {
					return false; // REJECT: a second hyphen, or one not between two letters
				}
				hasHyphen = true;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(countValidWords("cat and  dog")); // 3
		System.out.println(countValidWords("!this  1-s b8d!")); // 0
		System.out.println(countValidWords("alice and  bob are playing stone-game10")); // 5
	}
}
