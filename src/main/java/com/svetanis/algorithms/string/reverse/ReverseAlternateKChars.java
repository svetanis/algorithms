package com.svetanis.algorithms.string.reverse;

// 541. Reverse String II

public final class ReverseAlternateKChars {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static String reverse(String s, int k) {
		char[] chars = s.toCharArray();
		int n = chars.length;
		for (int start = 0; start < n; start += 2 * k) {
			int end = Math.min(start + k - 1, n - 1);
			reverse(chars, start, end);
		}
		return new String(chars);
	}

	private static void reverse(char[] chars, int start, int end) {
		while (start < end) {
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