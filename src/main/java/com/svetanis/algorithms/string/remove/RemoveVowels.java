package com.svetanis.algorithms.string.remove;

import java.util.function.Predicate;
import java.util.stream.Collectors;

// 1119. Remove Vowels from a String

public final class RemoveVowels {
	// Time Complexity: O(n)
	// Space Complexity: O(n)
	
	public static String removeVowelsSimple(String s) {
		StringBuilder sb = new StringBuilder();
		for(char c : s.toCharArray()) {
			if(!vowel(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String removeVowels(String s) {
		return s.codePoints().mapToObj(c -> (char) c)
				.filter(Predicate.not(c -> vowel(c)))
				.map(String::valueOf)
				.collect(Collectors.joining());
	}

	public static String remove(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (!vowel(c)) {
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
	}
}
