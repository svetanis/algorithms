package com.svetanis.algorithms.prefixsum;

// 848. Shifting Letters
//
// Input: a string s of lowercase letters and an array shifts of the same length. Shifting a letter
// once moves it to the next letter of the alphabet, with 'z' going round to 'a'. For each i,
// shifts[i] shifts the first i + 1 letters of s that many times. Returns the string after all the
// shifts. On LeetCode: 1 <= s.length <= 10^5, 0 <= shifts[i] <= 10^9.
//
// The one idea: letter i is moved by every shifts[j] with j >= i -- every shift whose first j + 1
// letters include it. So letter i's total shift is the sum of shifts[i..n - 1], a suffix sum, and
// walking right to left builds it one shifts[i] at a time. The total reaches 10^5 * 10^9 = 10^14,
// past the int range, so it is a long; only its remainder after dividing by 26 moves the letter.
//
// Time: O(n) -- one pass.
// Space: O(n) -- the char array that becomes the answer.
//
// Siblings: none.

public final class ShiftingLetters {

	public static String shiftingLetters(String s, int[] shifts) {
		int n = shifts.length;
		long total = 0; // SUFFIX SUM: shifts[i] + ... + shifts[n - 1]
		char[] chars = s.toCharArray();
		for (int i = n - 1; i >= 0; i--) { // WALK right to left
			total += shifts[i]; // ADD the shift that starts covering letter i here
			int index = (int) ((chars[i] - 'a' + total) % 26); // WRAP: 'z' goes round to 'a'
			chars[i] = (char) (index + 'a');
		}
		return String.valueOf(chars);
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 5, 9 };
		System.out.println(shiftingLetters("abc", a1)); // rpl

		int[] a2 = { 1, 2, 3 };
		System.out.println(shiftingLetters("aaa", a2)); // gfd
	}
}
