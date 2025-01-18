package com.svetanis.algorithms.string.anagram;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

// 242. Valid Anagram

public final class AnagramCounting {
  // Time complexity: O(n)
  // Space complexity: O(n)

	public static boolean areAnagrams(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		int[] a = new int[256];
		for (int i = 0; i < s1.length(); ++i) {
			a[s1.charAt(i)]++;
			a[s2.charAt(i)]--;
		}

		for (int count : a) {
			if (count != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		ImmutableList<Pair<String, String>> pairs = pairs();
		for (Pair<String, String> pair : pairs) {
			String w1 = pair.getLeft();
			String w2 = pair.getRight();
			System.out.println(areAnagrams(w1, w2));
		}
	}

	private static ImmutableList<Pair<String, String>> pairs() {
		List<Pair<String, String>> list = newArrayList();
		list.add(Pair.build("anagramm", "nagaramm")); // true
		list.add(Pair.build("aaca", "aca")); // false
		list.add(Pair.build("apple", "papel")); // true
		list.add(Pair.build("carrot", "tarroc")); // true
		list.add(Pair.build("hello", "llloh")); // false
		return copyOf(list);
	}
}
