package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// 267. Palindrome Permutation II

public final class AllPalindromePermutations {

	public static List<String> generatePalindromes(String s) {
		String mid = "";
		int[] count = count(s);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			if (count[i] % 2 == 1) {
				if (!mid.equals("")) {
					return list;
				}
				char c = (char) (i + 'a');
				mid = String.valueOf(c);
			}
		}
		dfs(mid, s.length(), count, list);
		return list;
	}

	private static void dfs(String s, int n, int[] count, List<String> list) {
		if (s.length() == n) {
			list.add(s);
			return;
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] > 1) {
				char c = (char) (i + 'a');
				count[i] -= 2;
				String concat = c + s + c;
				dfs(concat, n, count, list);
				count[i] += 2; // backtrack
			}
		}
	}

	private static int[] count(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'a']++;
		}
		return chars;
	}

	public static void main(String[] args) {
		System.out.println(generatePalindromes("aabb")); // aabb, bbaa
	}
}
