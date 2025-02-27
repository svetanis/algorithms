package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 424. Longest Repeating Character Replacement

// Given a string with lowercase letters only, 
// if you are allowed to replace no more than ‘k’ letters 
// with any letter, find the length of the longest substring 
// having the same letters after replacement.

public final class MaxUnivalSubStrLenAfterKReplacements {
	// Time complexity: O(n)

	public static int subStrLen(String s, int k) {
		int max = 0;
		int left = 0;
		int count = 0; // max repeated letter count
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			map.put(c, map.getOrDefault(c, 0) + 1);
			count = Math.max(count, map.get(c));
			while (right - left + 1 - count > k) {
				char front = s.charAt(left);
				map.put(front, map.get(front) - 1);
				left++;
			}
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	public static int subStrLen2(String s, int k) {
		int max = 0;
		int left = 0;
		int count = 0;
		int[] a = new int[26];
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			a[c - 'A']++;
			count = Math.max(count, a[c - 'A']);
			if (right - left + 1 - count > k) {
				char front = s.charAt(left);
				a[front - 'A']--;
				left++;
			}
			max = Math.max(max, right - left + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(subStrLen("aabccbb", 2)); // 5
		System.out.println(subStrLen("abbcb", 1)); // 4
		System.out.println(subStrLen("abccde", 1)); // 3

		System.out.println(subStrLen2("ABAB", 2)); // 4
		System.out.println(subStrLen2("AABABBA", 1)); // 4
	}
}
