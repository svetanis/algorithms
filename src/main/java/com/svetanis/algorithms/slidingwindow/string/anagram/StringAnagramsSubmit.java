package com.svetanis.algorithms.slidingwindow.string.anagram;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

// 438. Find All Anagrams in a String

// Given a string and a pattern, find all anagrams 
// of the pattern in the given string.

public final class StringAnagramsSubmit {
	// Time complexity: O(n)

	public static List<Integer> anagrams(String s, String p) {
		int n = s.length();
		int m = p.length();
		if (n < m) {
			return new ArrayList<>();
		}
		int[] pcount = frequencies(p, m);
		int[] wcount = frequencies(s, m - 1);
		List<Integer> list = new ArrayList<>();
		for (int i = m - 1; i < n; i++) {
			wcount[s.charAt(i) - 'a']++;
			if (Arrays.equals(pcount, wcount)) {
				list.add(i - m + 1);
			}
			wcount[s.charAt(i - m + 1) - 'a']--;
		}
		return list;
	}

	private static int[] frequencies(String s, int size) {
		int[] a = new int[26];
		for (int i = 0; i < size; i++) {
			a[s.charAt(i) - 'a']++;
		}
		return a;
	}

	public static void main(String[] args) {
		ImmutableList<Pair<String, String>> pairs = pairs();
		for (Pair<String, String> pair : pairs) {
			print(anagrams(pair.getLeft(), pair.getRight()));
		}
	}

	private static ImmutableList<Pair<String, String>> pairs() {
		List<Pair<String, String>> list = newArrayList();
		list.add(Pair.build("ppqp", "pq")); // 1, 2
		list.add(Pair.build("abbcabc", "abc")); // 2, 3, 4
		list.add(Pair.build("cbaebabacd", "abc")); // 0, 6
		list.add(Pair.build("abab", "ab")); // 0, 1, 2
		return newList(list);
	}

}
