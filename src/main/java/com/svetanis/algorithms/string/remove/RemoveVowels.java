package com.svetanis.algorithms.string.remove;

import java.util.function.Predicate;
import java.util.stream.Collectors;

// 1119. Remove Vowels from a String
//
// Input: a string s. Returns s without its vowels a, e, i, o, u. On LeetCode: 1 <= s.length <= 1000,
// s is lowercase English letters (the vowel test checks lowercase only).
//
// The one idea: keep a character exactly when it is not a vowel. Read s left to right and append
// every non-vowel to a builder; the order of the rest never changes, so nothing else is needed.
//
// Time: O(n) -- one pass, with a constant-time vowel test.
// Space: O(n) -- the result.
//
// Siblings: removeVowelsSimple and remove are the same loop written twice; removeVowels is the same
// filter as a stream of characters.

public final class RemoveVowels {

	public static String removeVowelsSimple(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (!vowel(c)) { // KEEP: every non-vowel
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String removeVowels(String s) {
		return s.codePoints().mapToObj(c -> (char) c)
					.filter(Predicate.not(c -> vowel(c))) // FILTER: drop the vowels
					.map(String::valueOf)
					.collect(Collectors.joining());
	}

	public static String remove(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (!vowel(c)) { // KEEP: every non-vowel
				sb.append(c);
			}
		}
		return sb.toString();
	}

	private static boolean vowel(char c) {
		return c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u';
	}

	public static void main(String[] args) {
		System.out.println(removeVowels("leetcode")); // ltcd
		System.out.println(removeVowelsSimple("aeiou")); // ""
		System.out.println(remove("leetcodeisacommunityforcoders")); // ltcdscmmntyfrcdrs
	}
}
