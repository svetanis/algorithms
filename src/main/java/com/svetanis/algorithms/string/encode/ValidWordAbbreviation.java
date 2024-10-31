package com.svetanis.algorithms.string.encode;

import static java.lang.Character.isDigit;
import static java.lang.Integer.parseInt;

// 408. Valid Word Abbreviation

public final class ValidWordAbbreviation {
	// Time Complexity: O(n + m)
	
	public static boolean valid(String word, String abbreviation) {
		int i = 0; // word index
		int j = 0; // abbreviation index
		int n = word.length();
		int m = abbreviation.length();
		while (i < n) {
			// abbreviation index exceeds its length
			if (j >= m) {
				return false;
			}
			// if chars in word and abbreviation
			// match move to the next character
			if (word.charAt(i) == abbreviation.charAt(j)) {
				i++;
				j++;
				continue;
			}
			// abbreviation char is not a digit
			if (!isDigit(abbreviation.charAt(j))) {
				return false;
			}
			// find the end of digit sequence in abbreviation
			int start = j;
			while (start < m && isDigit(abbreviation.charAt(start))) {
				start++;
			}
			String num = abbreviation.substring(j, start);
			// substring of "0" and leading
			// zeros are not valid
			if (j == start || num.charAt(0) == '0') {
				return false;
			}
			// move word and abbreviation index forward
			int skip = parseInt(num);
			i += skip;
			j = start;
		}
		return i == n && j == m;
	}

	public static void main(String[] args) {
		System.out.println(valid("substitution", "s10n")); // true
		System.out.println(valid("substitution", "s55n")); // false
		System.out.println(valid("international", "i11l")); // true
	}
}
