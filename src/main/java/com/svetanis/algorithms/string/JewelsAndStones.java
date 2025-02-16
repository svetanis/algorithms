package com.svetanis.algorithms.string;

import java.util.HashSet;
import java.util.Set;

// 771. Jewels and Stones

public final class JewelsAndStones {
	// Time Complexity: O(n + m)
	// Space Complexity: O(n)

	public static int countSimple(String jewels, String stones) {
		int[] ascii = new int[128];
		for (char c : jewels.toCharArray()) {
			ascii[c] = 1;
		}
		int count = 0;
		for (char c : stones.toCharArray()) {
			count += ascii[c];
		}
		return count;
	}

	public static int count(String jewels, String stones) {
		Set<Character> set = toSet(jewels);
		int count = 0;
		for (char c : stones.toCharArray()) {
			if (set.contains(c)) {
				count++;
			}
		}
		return count;
	}

	private static Set<Character> toSet(String s) {
		Set<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			set.add(c);
		}
		return set;
	}

	public static void main(String[] args) {
		System.out.println(countSimple("aA", "aAAbbbb")); // 3
		System.out.println(countSimple("z", "ZZ")); // 0
	}
}
