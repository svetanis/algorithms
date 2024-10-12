package com.svetanis.algorithms.string.reverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public final class ReverseWordsSubmit {
	// Time Complexity: O(n)

	public static String reverse(String s) {
		String[] splitted = s.trim().split("\\s+");
		List<String> words = Arrays.asList(splitted);
		List<String> reversed = reverse(words);
		return String.join(" ", reversed);
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
	}
}
