package com.svetanis.algorithms.string.reverse;

// 151. Reverse Words in a String
//
// Input: a string s of words separated by spaces; s may have leading, trailing or repeated
// spaces. Returns the words in the opposite order, joined by exactly one space and with no space
// at either end. On LeetCode: 1 <= s.length <= 10^4, s holds English letters, digits and ' ',
// and there is at least one word.
//
// The one idea: a word is a run of non-space characters. Cut s at every ' ' (leading, trailing and
// repeated spaces then show up as empty pieces), walk the pieces from the last to the first, skip
// the empty ones, and put one space before every word but the first.
//
// Time: O(n) -- the split reads s once, and the walk visits each piece once.
// Space: O(n) -- the array of pieces and the result.
//
// Siblings: ReverseWordsSubmit trims and splits on runs of spaces, then reverses the list;
// ReverseWordsInPlace reverses a char[] twice and squeezes the spaces without a list.

public final class ReverseWords {

	public static String reverseWords(String s) {
		String[] pieces = s.split(" "); // SPLIT: spaces are the cuts; extra spaces make empty pieces
		StringBuilder sb = new StringBuilder();
		for (int i = pieces.length - 1; i >= 0; i--) { // WALK: last piece first
			if (pieces[i].isEmpty()) {
				continue; // SKIP: an empty piece is a repeated or edge space
			}
			if (sb.length() > 0) {
				sb.append(' '); // GLUE: one space between words only
			}
			sb.append(pieces[i]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String s1 = "the sky is blue";
		System.out.println(reverseWords(s1)); // blue is sky the

		String s2 = "  hello world  ";
		System.out.println(reverseWords(s2)); // world hello

		String s3 = "a good   example";
		System.out.println(reverseWords(s3)); // example good a
	}
}
