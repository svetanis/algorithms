package com.svetanis.algorithms.string.reverse;

// 541. Reverse String II
//
// Input: a string s and an integer k. Cut s from the left into blocks of 2k characters. Returns s
// with the first k characters of every block reversed and the other k left alone; a last block
// shorter than k is reversed whole. On LeetCode: 1 <= s.length <= 10^4, s is lowercase English
// letters, 1 <= k <= 10^4.
//
// The one idea: a block starts at index 0, 2k, 4k, ... and the part to reverse is the k characters
// from the block's start, cut off at the end of the string when the last block is short. So the
// loop visits each block start once and reverses the stretch start .. min(start + k - 1, n - 1) by
// swapping its two ends and moving inward; the second half of the block is never touched.
//
// Time: O(n) -- the stretches are disjoint and each character is swapped at most once.
// Space: O(n) -- the char[] copy, since a Java String cannot be changed in place.
//
// Siblings: ReverseStringSwap reverses the whole string with the same swap loop; ReverseWordsIII
// applies that loop to each word instead of to each block.

public final class ReverseAlternateKChars {

	public static String reverse(String s, int k) {
		char[] chars = s.toCharArray();
		int n = chars.length;
		for (int start = 0; start < n; start += 2 * k) { // WALK: one block start at a time
			int end = Math.min(start + k - 1, n - 1); // CAP: a short last block ends at the string's end
			reverse(chars, start, end);
		}
		return new String(chars);
	}

	private static void reverse(char[] chars, int start, int end) {
		while (start < end) { // SWAP the two ends, then move inward
			char temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		System.out.println(reverse("abcdefg", 2)); // bacdfeg
		System.out.println(reverse("abcd", 2)); // bacd
	}
}