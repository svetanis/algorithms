package com.svetanis.algorithms.string.palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 336. Palindrome Pairs

public final class PalindromePairsBruteForce {

	public static List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (i == j) {
					continue;
				}
				String concat = words[i] + words[j];
				String reversed = new StringBuilder(concat).reverse().toString();
				if (concat.equals(reversed)) {
					list.add(Arrays.asList(i, j));
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String[] w1 = { "abcd", "dcba", "lls", "s", "sssll" };
		System.out.println(palindromePairs(w1)); // [0,1],[1,0],[3,2],[2,4]
		String[] w2 = { "bat", "tab", "cat" };
		System.out.println(palindromePairs(w2)); // [0,1],[1,0]
		String[] w3 = { "a", "" };
		System.out.println(palindromePairs(w3)); // [0,1],[1,0]
	}
}
