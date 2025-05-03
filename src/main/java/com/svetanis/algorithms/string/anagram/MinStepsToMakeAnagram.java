package com.svetanis.algorithms.string.anagram;

// 1347. Minimum Number of Steps to Make Two Strings Anagram

public final class MinStepsToMakeAnagram {
	// Time Complexity: O(n + m)

	public static int minSteps(String s, String t) {
		int count = 0;
		int[] chars = freq(s);
		for(char c : t.toCharArray()) {
			if(--chars[c - 'a'] < 0) {
				count++;
			} 
		}
		return count;
	}
	
	private static int[] freq(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'a']++;
		}
		return chars;
	}

	public static void main(String[] args) {
		System.out.println(minSteps("bab", "aba")); // 1
		System.out.println(minSteps("leetcode", "practice")); // 5
		System.out.println(minSteps("anagram", "mangaar")); // 0
	}
}