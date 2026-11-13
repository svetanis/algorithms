package com.svetanis.algorithms.string.reverse;

// 345. Reverse Vowels of a String
//
// Input: a string s. Returns s with its vowels (a, e, i, o, u, upper or lower case) in the opposite
// order; every other character stays where it is. On LeetCode: 1 <= s.length <= 3 * 10^5, s is
// printable ASCII.
//
// The one idea: two pointers, left and right. Move left forward until it stands on a vowel, move
// right backward until it stands on a vowel, and if they have not crossed, swap the two vowels and
// step both inward. The k-th vowel from the left trades with the k-th vowel from the right, which
// is what reversing their order means; no other character moves.
//
// Time: O(n) -- each pointer only moves inward, so the two loops together make at most n steps.
// Space: O(n) -- the char[] copy; the lookup table has one slot for each of the 65,536 possible
// char values, a constant.
//
// Siblings: ReverseVowels makes the same walk with a Set and four cases in one loop.

public final class ReverseVowelsSubmit {

	public static String reverse(String s) {
		boolean[] vowels = vowels();
		int n = s.length();
		char[] chars = s.toCharArray();
		int left = 0;
		int right = n - 1;
		while (left < right) {
			while (left < right && !vowels[chars[left]]) { // SKIP: non-vowels from the left
				left++;
			}
			while (left < right && !vowels[chars[right]]) { // SKIP: non-vowels from the right
				right--;
			}
			if (left < right) {
				swap(chars, left, right); // SWAP: vowel with vowel
				left++;
				right--;
			}
		}
		return new String(chars);
	}

	private static boolean[] vowels() {
		// one slot for every possible char value, so vowels[chars[i]] is in bounds for any
		// character, not only ASCII
		boolean[] vowels = new boolean[Character.MAX_VALUE + 1];
		char[] chars = "aeiouAEIOU".toCharArray();
		for (char c : chars) {
			vowels[c] = true;
		}
		return vowels;
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