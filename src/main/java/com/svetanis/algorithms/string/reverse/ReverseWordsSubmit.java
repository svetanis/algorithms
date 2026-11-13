package com.svetanis.algorithms.string.reverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 151. Reverse Words in a String
//
// Input: a string s of words separated by spaces; s may have leading, trailing or repeated
// spaces. Returns the words in the opposite order, joined by exactly one space and with no space
// at either end. On LeetCode: 1 <= s.length <= 10^4, s holds English letters, digits and ' ',
// and there is at least one word.
//
// The one idea: trim the ends, so no empty word can appear at either side, then split on runs of
// spaces (the pattern \s+ is one or more), which leaves exactly the words. Reverse that list and
// join it with one space. reverseWords does the same through an array of words read from the
// last to the first, adding a space after each word but the last.
//
// Time: reverse -- O(n), one split, one reversal, one join. reverseWords -- O(n * w) for w words,
// because each += copies the string built so far.
// Space: O(n) -- the array of words and the result.
//
// Siblings: ReverseWords cuts at single spaces and skips the empty pieces; ReverseWordsInPlace
// does it on a char[] with no list. The private reverse(List) copies a list backwards; reverse(String)
// uses Collections.reverse instead, so nothing calls it.

public final class ReverseWordsSubmit {

	public static String reverse(String s) {
		String[] splitted = s.trim().split("\\s+"); // SPLIT: trimmed, so no empty word at either end
		List<String> words = Arrays.asList(splitted);
		Collections.reverse(words); // REVERSE: the word order
		return String.join(" ", words); // RETURN: one space between words
	}

	public static String reverseWords(String s) {
		String[] splitted = s.trim().split("\\s+");
		String out = "";
		for (int i = splitted.length - 1; i > 0; i--) { // WALK: last word down to the second
			out += splitted[i] + " ";
		}
		return out + splitted[0]; // RETURN: the first word goes last, with no space after it
	}

	private static List<String> reverse(List<String> words) {
		List<String> list = new ArrayList<>();
		for (int i = words.size() - 1; i >= 0; i--) {
			list.add(words.get(i));
		}
		return list;
	}

	public static void main(String[] args) {
		String s1 = "the sky is blue";
		System.out.println(reverse(s1)); // blue is sky the

		String s2 = "  hello world  ";
		System.out.println(reverse(s2)); // world hello

		String s3 = "a good   example";
		System.out.println(reverse(s3)); // example good a

		System.out.println(reverseWords(s1)); // blue is sky the
		System.out.println(reverseWords(s2)); // world hello
		System.out.println(reverseWords(s3)); // example good a
	}
}
