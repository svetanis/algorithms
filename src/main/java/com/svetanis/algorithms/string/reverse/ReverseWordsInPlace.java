package com.svetanis.algorithms.string.reverse;

// 151. Reverse Words in a String
//
// Input: a string s of words separated by spaces; s may have leading, trailing or repeated
// spaces. Returns the words in the opposite order, joined by exactly one space and with no space
// at either end. On LeetCode: 1 <= s.length <= 10^4, s holds English letters, digits and ' ',
// and there is at least one word.
//
// The one idea: reversing the whole array puts the words in the right order but spells each one
// backwards; reversing each word again spells it forwards. What is left is the spaces: a read
// index j walks the array and a write index i trails it. Each run of non-space characters is
// copied to i, and one space is written after it only if more characters follow; leading,
// trailing and repeated spaces are skipped, never copied. The result is the first i characters.
//
// Time: O(n) -- three passes, each reading every character a constant number of times.
// Space: O(n) -- the char[] copy; the passes themselves use only a few indexes.
//
// Siblings: ReverseWordsII is the same two reversals when the spaces are already single and none
// is at an end, so no squeezing is needed; ReverseWords and ReverseWordsSubmit split into a list.

public final class ReverseWordsInPlace {

	public static String reverse(String s) {
		char[] chars = s.toCharArray();
		reverse(chars, 0, s.length() - 1); // STEP 1: the whole array
		reverseWords(chars); // STEP 2: each word, so it reads forwards again
		return removeSpaces(chars); // STEP 3: squeeze the spaces
	}

	private static String removeSpaces(char[] chars) {
		int n = chars.length;
		int i = 0; // WRITE index: chars[0 .. i - 1] is the answer so far
		int j = 0; // READ index
		while (j < n) {
			while (j < n && chars[j] == ' ') {
				j++; // SKIP: spaces before a word
			}
			while (j < n && chars[j] != ' ') {
				chars[i++] = chars[j++]; // COPY: one word
			}
			while (j < n && chars[j] == ' ') {
				j++; // SKIP: spaces after the word
			}
			if (j < n) {
				chars[i++] = ' '; // GLUE: one space, only if another word follows
			}
		}
		return new String(chars, 0, i); // RETURN: just the part written
	}

	private static void reverseWords(char[] chars) {
		int n = chars.length;
		int start = 0;
		for (int end = 0; end < n; end++) {
			if (chars[end] == ' ') {
				reverse(chars, start, end - 1); // REVERSE: the word before this space
				start = end + 1;
			}
		}
		reverse(chars, start, n - 1); // REVERSE: the last word, which has no space after it
	}

	private static void reverse(char[] chars, int left, int right) {
		while (left < right) {
			char temp = chars[left];
			chars[left] = chars[right];
			chars[right] = temp;
			left++;
			right--;
		}
	}

	public static void main(String[] args) {
		String s1 = "the sky is blue";
		System.out.println(reverse(s1)); // blue is sky the

		String s2 = "  hello world  ";
		System.out.println(reverse(s2)); // world hello

		String s3 = "a good   example";
		System.out.println(reverse(s3)); // example good a
	}
}
