package com.svetanis.algorithms.string.reverse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 345. Reverse Vowels of a String

public final class ReverseVowels {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

	public static String reverse(String s) {
		int n = s.length();
		char[] chars = s.toCharArray();
		int left = 0;
		int right = n - 1;
		while (left < right) {
			char c1 = Character.toLowerCase(chars[left]);
			char c2 = Character.toLowerCase(chars[right]);
			if (VOWELS.contains(c1) && VOWELS.contains(c2)) {
				swap(chars, left, right);
				left++;
				right--;
			} else if (VOWELS.contains(c1)) {
				right--;
			} else if (VOWELS.contains(c2)) {
				left++;
			} else {
				left++;
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