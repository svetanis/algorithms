package com.svetanis.algorithms.string.reverse;

// 345. Reverse Vowels of a String

public final class ReverseVowelsSubmit {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String reverse(String s) {
		boolean[] vowels = vowels();
		int n = s.length();
		char[] chars = s.toCharArray();
		int left = 0;
		int right = n - 1;
		while (left < right) {
			while (left < right && !vowels[chars[left]]) {
				left++;
			}
			while (left < right && !vowels[chars[right]]) {
				right--;
			}
			if (left < right) {
				swap(chars, left, right);
				left++;
				right--;
			}
		}
		return new String(chars);
	}

	private static boolean[] vowels() {
		boolean[] vowels = new boolean[128];
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