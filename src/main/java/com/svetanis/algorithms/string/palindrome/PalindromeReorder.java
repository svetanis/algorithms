package com.svetanis.algorithms.string.palindrome;

// CSES: Palindrome Reorder
//
// Input: a string of uppercase letters A-Z. On CSES: length up to 10^6.
// Return: its letters rearranged into a palindrome, any one will do, or "Not possible"
// when no arrangement is a palindrome.
//
// The one idea: count each letter. At most one letter may have an odd count, or no
// arrangement works. Then fill from both ends inward: each pair of a letter takes one
// slot on the left and its mirror slot on the right. The single odd copy, if there is
// one, goes in the middle -- on an odd length exactly the slot the pairs never reach.
//
// Siblings -- the same odd-count test:
//   PalindromePermutation -- only answers whether one exists, lowercase letters
//   ConstructKPalindromes -- k palindromes: at most k odd counts
//
// Time: O(n) -- one counting pass, one pass placing letters.
// Space: O(n) for the answer.

public final class PalindromeReorder {

	public static String palindrome(String s) {
		int[] counts = count(s);
		int odd = countOdd(counts);
		if (odd > 1) {
			return "Not possible";                             // RETURN: only one letter fits in the middle
		}
		int n = s.length();
		char[] chars = new char[n];
		int left = 0, right = n - 1;                           // START at both ends
		for (int i = 0; i < 26; i++) {
			while (counts[i] > 1) {
				chars[left++] = chars[right--] = (char) ('A' + i); // PAIR: a copy each side, MOVE inward
				counts[i] -= 2;
			}
		}
		for (int i = 0; i < 26; i++) {
			if (counts[i] == 1) {
				chars[n / 2] = (char) ('A' + i);               // MIDDLE: the letter with an odd count
				break;
			}
		}
		return new String(chars);
	}

	private static int countOdd(int[] counts) {
		int odd = 0;
		for (int count : counts) {
			if (count % 2 == 1) {
				odd++;                                         // COUNT the letters left without a partner
			}
		}
		return odd;
	}

	private static int[] count(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'A']++;                                  // COUNT each letter
		}
		return chars;
	}

	public static void main(String[] args) {
		System.out.println(palindrome("AAAACACBA")); // AAACBCAAA
		System.out.println(palindrome("AAABBB"));    // Not possible
	}
}
