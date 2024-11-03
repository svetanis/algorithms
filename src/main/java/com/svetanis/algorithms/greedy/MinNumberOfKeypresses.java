package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 2268 - Minimum Number of Keypresses

public final class MinNumberOfKeypresses {
	// Time Complexity: O(n)
	
	public static int minNumKeypresses(String s) {
		int[] frequency = frequency(s);
		Arrays.sort(frequency);
		int total = 0;
		int keypress = 1;
		for (int i = 1; i <= 26; i++) {
			total += keypress * frequency[26 - i];
			// every 9th char requires additional keypress
			if (i % 9 == 0) {
				keypress++;
			}
		}
		return total;
	}

	private static int[] frequency(String s) {
		int[] a = new int[26];
		for (char c : s.toCharArray()) {
			a[c - 'a']++;
		}
		return a;
	}

	public static void main(String[] args) {
		System.out.println(minNumKeypresses("hello")); // 5
	}
}
