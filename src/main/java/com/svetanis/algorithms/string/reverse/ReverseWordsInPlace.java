package com.svetanis.algorithms.string.reverse;

// 151. Reverse Words in a String

// given an input string s, 
// reverse the order of the words

// a word is defined as a sequence
// of non-space characters.
// the words in s will be separated
// by at least one space

// return a string of the words in
// reverse order concatenated by a
// single space

// s may contain leading or trailing 
// spaces or multiple spaces between 
// two words. the returned string 
// should only have a single space 
// separating the words. don't include
// any extra spaces

public final class ReverseWordsInPlace {
	// Time Complexity: O(n)

	public static String reverse(String s) {
		char[] chars = s.toCharArray();
		// 1. Reverse entire string
		reverse(chars, 0, s.length() - 1);
		// 2. Reverse each string
		reverseWords(chars);
		// 3. Remove spaces
		return removeSpaces(chars);
	}

	private static String removeSpaces(char[] chars) {
		int n = chars.length;
		int i = 0;
		int j = 0;
		while (j < n) {
			// skip spaces
			while (j < n && chars[j] == ' ') {
				j++;
			}
			// copy non-spaces chars
			while (j < n && chars[j] != ' ') {
				chars[i++] = chars[j++];
			}
			while (j < n && chars[j] == ' ') {
				j++;
			}
			if (j < n) {
				chars[i++] = ' ';
			}
		}
		return new String(chars, 0, i);
	}

	private static void reverseWords(char[] chars) {
		int n = chars.length;
		int start = 0;
		for (int end = 0; end < n; end++) {
			if (chars[end] == ' ') {
				reverse(chars, start, end - 1);
				start = end + 1;
			}
		}
		// reverse last word
		reverse(chars, start, n - 1);
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
