package com.svetanis.algorithms.string.reverse;

import static com.google.common.collect.Lists.reverse;
import static com.svetanis.java.base.Splitters.split;

import java.util.List;

import com.google.common.base.Joiner;

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

public final class ReverseWords {
	// Time Complexity: O(n)

	public static String reverseWords(String s) {
		List<String> list = split(' ', s);
		List<String> reversed = reverse(list);
		return Joiner.on(" ").join(reversed);
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
