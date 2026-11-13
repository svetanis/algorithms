package com.svetanis.algorithms.string;

import java.util.Arrays;

// 806. Number of Lines To Write String
//
// Input: widths[0..25] -- how many pixels wide each letter a to z is -- and a string s of lowercase
// letters. Letters are written left to right on lines 100 pixels wide; a letter that would not
// fit on the current line starts a new one. Returns {number of lines, width of the last line}.
// On LeetCode: widths.length == 26, 2 <= widths[i] <= 10, 1 <= s.length <= 1000.
//
// The one idea: keep the width used so far on the current line (sum) and the number of lines
// (count). For each letter, if sum + its width goes over 100, the letter starts a new line: count
// goes up and sum restarts at that letter's width. Otherwise it is added to sum. Putting a letter on
// the line it just fits (sum + width == 100) is allowed -- only going over 100 starts a new line.
//
// Time: O(n) -- one pass over s
// Space: O(n) -- toCharArray copies s; O(1) beyond that

public final class CountLines {

	public static int[] lines(int[] width, String s) {
		int sum = 0; // USED: pixels on the current line
		int count = 1; // LINES: the first line is open from the start
		for (char c : s.toCharArray()) {
			int len = width[c - 'a'];
			if (sum + len > 100) { // OVER: the letter does not fit here
				count++; // NEW: open the next line
				sum = len; // the letter is its first one
			} else {
				sum += len; // FIT: stay on this line
			}
		}
		return new int[] { count, sum }; // RETURN: lines, and how full the last one is
	}

	public static void main(String[] args) {
		int[] width1 = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10 };
		String s1 = "abcdefghijklmnopqrstuvwxyz";
		System.out.println(Arrays.toString(lines(width1, s1))); // [3, 60]

		int[] width2 = { 4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10 };
		String s2 = "bbbcccdddaaa";
		System.out.println(Arrays.toString(lines(width2, s2))); // [2, 4]
	}
}
