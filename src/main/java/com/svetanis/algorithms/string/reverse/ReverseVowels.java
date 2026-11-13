package com.svetanis.algorithms.string.reverse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 345. Reverse Vowels of a String
//
// Input: a string s. Returns s with its vowels (a, e, i, o, u, upper or lower case) in the opposite
// order; every other character stays where it is. On LeetCode: 1 <= s.length <= 3 * 10^5, s is
// printable ASCII.
//
// The one idea: two pointers, left and right, one at each end. Each step looks at the two
// characters they point to. If both are vowels, swap them and move both inward. If only one is a
// vowel, the other pointer steps in, looking for a partner. If neither is, both step in. Only
// vowels are ever swapped, and the k-th vowel from the left trades with the k-th from the right,
// which is what reversing their order means.
//
// Time: O(n) -- each step moves at least one pointer, and the pointers cross after at most n steps.
// Space: O(n) -- the char[] copy; the vowel set has 5 entries.
//
// Siblings: ReverseVowelsSubmit makes the same walk with two inner skipping loops and a lookup
// table instead of a set.

public final class ReverseVowels {

	private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

	public static String reverse(String s) {
		int n = s.length();
		char[] chars = s.toCharArray();
		int left = 0;
		int right = n - 1;
		while (left < right) {
			char c1 = Character.toLowerCase(chars[left]); // CASE: 'A' counts as a vowel too
			char c2 = Character.toLowerCase(chars[right]);
			if (VOWELS.contains(c1) && VOWELS.contains(c2)) {
				swap(chars, left, right); // SWAP: vowel with vowel
				left++;
				right--;
			} else if (VOWELS.contains(c1)) {
				right--; // SKIP: the right side has no vowel yet
			} else if (VOWELS.contains(c2)) {
				left++; // SKIP: the left side has no vowel yet
			} else {
				left++; // SKIP: neither side is a vowel
				right--;
			}
		}
		return new String(chars);
	}

	private static void swap(char[] a, int left, int right) {
		char temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	public static void main(String[] args) {
		System.out.println(reverse("IceCreAm")); // AceCreIm
		System.out.println(reverse("leetcode")); // leotcede
	}
}