package com.svetanis.algorithms.string;

// 520. Detect Capital
//
// Input: a word. The use of capitals is right if (1) every letter is a capital ("USA"), or (2) no
// letter is a capital ("leetcode"), or (3) only the first letter is a capital ("Google").
// Returns true if the word uses capitals right. On LeetCode: 1 <= word.length <= 100, English
// letters, upper or lower case.
//
// The one idea: count the capital letters. Three counts are right: all of them (count == length),
// none (count == 0), and exactly one that is the first letter. A count of 1 with the capital
// somewhere else ("gOogle") is wrong, which is why the third case also looks at the first letter.
//
// Time: O(n) -- one pass to count
// Space: O(n) -- toCharArray copies the word; O(1) beyond that

public final class DetectCapitals {

	public static boolean detectCapitals(String word) {
		int len = word.length();
		int capitals = capitals(word);
		boolean one = len == capitals; // ALL: every letter is a capital
		boolean two = capitals == 0; // NONE: no capital at all
		boolean three = capitals == 1 && Character.isUpperCase(word.charAt(0)); // FIRST: the single capital leads
		return one || two || three;
	}

	private static int capitals(String word) {
		int count = 0;
		for (char c : word.toCharArray()) {
			if (Character.isUpperCase(c)) {
				count++; // COUNT: one more capital
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(detectCapitals("USA")); // true
		System.out.println(detectCapitals("FlaG")); // false
		System.out.println(detectCapitals("g")); // true
		System.out.println(detectCapitals("ggg")); // true
		System.out.println(detectCapitals("ffffffffffffffffffffF")); // false

	}
}
