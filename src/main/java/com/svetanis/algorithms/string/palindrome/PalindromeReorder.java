package com.svetanis.algorithms.string.palindrome;

// CSES: Palindrome Reorder

public final class PalindromeReorder {
	// Time Complexity: O(n)

	public static String palindrome(String s) {
		int[] counts = count(s);
		int odd = countOdd(counts);
		if (odd > 1) {
			return "Not possible";
		}
		int n = s.length();
		char[] chars = new char[n];
		int left = 0, right = n - 1;
		for (int i = 0; i < 26; i++) {
			while (counts[i] > 1) {
				chars[left++] = chars[right--] = (char) ('A' + i);
				counts[i] -= 2;
			}
		}
		for (int i = 0; i < 26; i++) {
			if (counts[i] == 1) {
				chars[n / 2] = (char) ('A' + i);
				break;
			}
		}
		return new String(chars);
	}

	private static int countOdd(int[] counts) {
		int odd = 0;
		for (int count : counts) {
			if (count % 2 == 1) {
				odd++;
			}
		}
		return odd;
	}

	private static int[] count(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'A']++;
		}
		return chars;
	}

	public static void main(String[] args) {
		System.out.println(palindrome("AAAACACBA")); // AAACBCAAA
		System.out.println(palindrome("AAABBB")); //
	}
}
