package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 893. Groups of Special-Equivalent Strings

public final class SpecialEquivalentGroups {
	// Time Complexity: O(n * m * log m)
	// Space Complexity: O(n * m)

	public static int seg(String[] words) {
		Set<String> set = new HashSet<>();
		for (String word : words) {
			set.add(convert(word));
		}
		return set.size();
	}

	private static String convert(String s) {
		List<Character> even = new ArrayList<>();
		List<Character> odd = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (i % 2 == 0) {
				even.add(c);
			} else {
				odd.add(c);
			}
		}
		Collections.sort(even);
		Collections.sort(odd);

		StringBuilder sb = new StringBuilder();
		for (char c : even) {
			sb.append(c);
		}
		for (char c : odd) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String[] a1 = { "abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx" };
		System.out.println(seg(a1)); // 3
		String[] a2 = { "abc", "acb", "bac", "bca", "cab", "cba" };
		System.out.println(seg(a2)); // 3
	}
}
