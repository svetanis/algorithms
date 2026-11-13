package com.svetanis.algorithms.string.reverse;

// 344. Reverse String
//
// Input: a char[] s. Returns nothing; s is reversed in place, using O(1) extra memory. On
// LeetCode: 1 <= s.length <= 10^5, s[i] is a printable ASCII character.
//
// The one idea: two pointers, left at index 0 and right at index n - 1. Swap the two characters,
// move both one step inward, and stop when they meet or cross: every pair has then been swapped
// exactly once, and a middle character, when the length is odd, stays where it is.
//
// Time: O(n) -- n / 2 swaps of constant cost.
// Space: reverse -- O(1), only the two pointers and a temporary. reverseStr -- O(n), one call
// is open for every pair at the deepest point.
//
// Known limit: reverseStr is recursive and n / 2 calls deep, so a char[] of 10^5 characters,
// which LeetCode allows, throws StackOverflowError. The loop in reverse(char[]) does the same
// work with no such limit; it is the one to submit.
//
// Siblings: ReverseStringSwap and ReverseStringInPlace make the same swaps on a String copy;
// ReverseStringXor swaps with XOR.

public final class ReverseStringSubmit {

	public static void reverseStr(char[] chars) {
		reverse(chars, 0, chars.length - 1);
	}

	private static void reverse(char[] chars, int left, int right) {
		if (left >= right) { // BASE: the pointers met or crossed
			return;
		}
		char temp = chars[left]; // SWAP the two ends, then recurse on the inside
		chars[left] = chars[right];
		chars[right] = temp;
		reverse(chars, left + 1, right - 1);
	}

	public static void reverse(char[] chars) {
		int n = chars.length;
		int left = 0;
		int right = n - 1;
		while (left < right) { // WALK: until the pointers meet or cross
			char temp = chars[left]; // SWAP the two ends
			chars[left] = chars[right];
			chars[right] = temp;
			left++;
			right--;
		}
	}

	public static void main(String[] args) {
		char[] a1 = "hello".toCharArray();
		reverse(a1);
		System.out.println(new String(a1)); // olleh

		char[] a2 = "Hannah".toCharArray();
		reverse(a2);
		System.out.println(new String(a2)); // hannaH

		char[] a3 = "hello".toCharArray();
		reverseStr(a3);
		System.out.println(new String(a3)); // olleh
	}
}