package com.svetanis.algorithms.twopointers;

import java.util.Arrays;
import java.util.List;

// 524. Longest Word in Dictionary through Deleting

public final class LongestWordInDictionary {
	// Time Complexity: O(n * m)

	public static String longestWord(String s, List<String> list) {
		String result = "";
		for (String word : list) {
			if (isSubSeq(s, word)) {
				int len = word.length();
				int max = result.length();
				boolean equals = len == max && word.compareTo(result) < 0;
				if (len > max || equals) {
					result = word;
				}
			}
		}
		return result;
	}

	private static boolean isSubSeq(String s, String word) {
		int n = s.length();
		int m = word.length();
		int i = 0;
		int j = 0;
		while (i < n && j < m) {
			if (s.charAt(i) == word.charAt(j)) {
				j++;
			}
			i++;
		}
		return j == m;
	}

	public static void main(String[] args) {
		String s1 = "abpcplea";
		List<String> list1 = Arrays.asList("ale", "apple", "monkey", "plea");
		System.out.println(longestWord(s1, list1)); // apple

		String s2 = "abpcplea";
		List<String> list2 = Arrays.asList("a", "b", "c");
		System.out.println(longestWord(s2, list2)); // a
	}
}
