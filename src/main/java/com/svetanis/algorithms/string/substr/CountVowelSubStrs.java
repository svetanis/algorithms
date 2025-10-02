package com.svetanis.algorithms.string.substr;

import java.util.HashSet;
import java.util.Set;

// 2062. Count Vowel Substrings of a String

public final class CountVowelSubStrs {
	// Time Complexity: O(n^3)
	// Space Complexity: O(n)

	public static int vowelSubStrs(String s) {
		int count = 0;
		int n = s.length();
		for (int i = 0; i < n; i++) {
			Set<Character> set = new HashSet<>();
			for (int j = i; j < n; j++) {
				char c = s.charAt(j);
				if (!vowel(c)) {
					break;
				}
				set.add(c);
				if (set.size() == 5) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean vowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	public static void main(String[] args) {
		System.out.println(vowelSubStrs("aeiouu")); // 2
		System.out.println(vowelSubStrs("unicornarihan")); // 0
		System.out.println(vowelSubStrs("cuaieuouac")); // 7
	}
}
