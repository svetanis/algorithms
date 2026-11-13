package com.svetanis.algorithms.string.encode;

// 408. Valid Word Abbreviation
//
// Input: a word and an abbreviation. In an abbreviation a number stands for that many letters of
// the word, skipped, and a letter must equal the word's letter at the same place. A number may not
// start with 0 ("s010n" and "s0ubstitution" are invalid). Returns true if the abbreviation matches
// the whole word. On LeetCode: 1 <= word.length <= 20, lowercase letters; 1 <= abbr.length <= 10,
// lowercase letters and digits; every number in abbr fits in an int. A longer run of digits
// (outside those limits) throws NumberFormatException.
//
// The one idea: two indexes, i into the word and j into the abbreviation, both moving left to
// right. At j there is either a letter, which must equal word[i] (both move one), or the start of
// a number, which is read whole and moves i forward by its value. The abbreviation matches when
// both run out at the same moment.
//
// Time: O(n + m) -- n = word.length, m = abbr.length; each abbreviation character is read once,
// and i jumps over skipped letters without reading them.
// Space: O(1) besides the number's digits, at most 10 characters.

public final class ValidWordAbbreviation {

	public static boolean valid(String word, String abbreviation) {
		int i = 0; // word index
		int j = 0; // abbreviation index
		int n = word.length();
		int m = abbreviation.length();
		while (i < n) { // WALK: until the word is used up
			if (j >= m) {
				return false; // RETURN: the abbreviation ran out first
			}
			if (word.charAt(i) == abbreviation.charAt(j)) {
				i++; // MATCH: the same letter, both move on
				j++;
				continue;
			}
			if (!Character.isDigit(abbreviation.charAt(j))) {
				return false; // RETURN: a different letter
			}
			int start = j;
			while (start < m && Character.isDigit(abbreviation.charAt(start))) {
				start++; // READ: start ends one past the number's last digit
			}
			String num = abbreviation.substring(j, start);
			if (num.charAt(0) == '0') {
				return false; // RETURN: a leading zero, which includes the number 0
			}
			int skip = Integer.parseInt(num);
			i += skip; // SKIP: that many letters of the word
			j = start;
		}
		return i == n && j == m; // RETURN: both used up together; a skip past the end fails here
	}

	public static void main(String[] args) {
		System.out.println(valid("substitution", "s10n")); // true
		System.out.println(valid("substitution", "s55n")); // false
		System.out.println(valid("international", "i11l")); // true
		System.out.println(valid("internationalization", "i12iz4n")); // true
		System.out.println(valid("apple", "a2e")); // false
		System.out.println(valid("substitution", "s010n")); // false
	}
}
