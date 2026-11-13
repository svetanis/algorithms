package com.svetanis.algorithms.string.palindrome;

// Palindrome check, every character counted as it is
//
// Input: a string, or a range of one.
// Return: whether it reads the same forwards and backwards. Nothing is skipped and case
// matters: "Aa" and "a,a" are not palindromes here.
//
// The one idea: compare the two ends and move both inward. Position i is mirrored by
// position n - 1 - i, so only the first half needs its partner checked; on an odd
// length the middle character mirrors itself and needs no test. One disagreeing pair
// is enough to answer false.
//
// The methods:
//   palindrome                       -- one index i, its mirror computed as n - 1 - i
//   palindrome2                      -- two pointers, left and right, moved together
//   isPalindrome(str)                -- the whole string, through the range version
//   isPalindrome(str, left, right)   -- only str[left..right], both ends included; copies the whole string first
//   isPalindrome(chars, left, right) -- the same range walk on a char array
//
// Siblings:
//   PalindromeRecursive   -- the same comparisons written as recursion
//   ValidPalindromeSimple -- LC 125: skips what is not a letter or digit, ignores case
//
// Time: O(n) -- at most n / 2 comparisons; isPalindrome(str, left, right) also copies the string.
// Space: O(1), except that copy.

public final class PalindromeIterative {

	public static boolean palindrome(String str) {
		int n = str.length();
		for (int i = 0; i < n / 2; ++i) {                 // STOP at the middle
			if (str.charAt(i) != str.charAt(n - i - 1)) { // COMPARE i with its mirror
				return false;                             // MISMATCH
			}
		}
		return true;                                      // MATCH: every pair agreed
	}

	public static boolean palindrome2(String str) {
		int left = 0;                                     // START at both ends
		int right = str.length() - 1;
		while (left < right) {                            // STOP when they meet
			if (str.charAt(left) != str.charAt(right)) {
				return false;                             // MISMATCH
			}
			left++;                                       // MATCH: MOVE both inward
			right--;
		}
		return true;
	}

	public static boolean isPalindrome(String str) {
		int n = str.length();
		int left = 0;
		int right = n - 1;
		return isPalindrome(str, left, right);
	}

	public static boolean isPalindrome(String str, int left, int right) {
		char[] chars = str.toCharArray();
		return isPalindrome(chars, left, right);
	}

	public static boolean isPalindrome(char[] chars, int left, int right) {
		while (right > left) {                            // STOP when they meet
			if (chars[left++] != chars[right--]) {        // COMPARE, then MOVE both inward
				return false;                             // MISMATCH
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String str = "ABCDEDCBA";
		System.out.println(palindrome(str));               // true
		System.out.println(palindrome2(str));              // true
		System.out.println(isPalindrome(str));             // true
		System.out.println(isPalindrome("xABAy", 1, 3));   // true
		System.out.println(isPalindrome("ABCA".toCharArray(), 0, 3)); // false
		System.out.println(palindrome(""));                // true
	}
}
