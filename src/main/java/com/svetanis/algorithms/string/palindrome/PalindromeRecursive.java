package com.svetanis.algorithms.string.palindrome;

// Palindrome check, recursive, every character counted as it is
//
// Input: a string.
// Return: whether it reads the same forwards and backwards. Nothing is skipped and case
// matters.
//
// The one idea: a string is a palindrome when its two end characters match AND the
// part between them is a palindrome. Each call compares one pair and hands the inner
// range to the next call. The recursion stops when the range holds one character or
// none (start >= end) -- both are palindromes, and the empty string is the second.
//
// Siblings:
//   PalindromeIterative -- the same comparisons as a loop, O(1) space
//
// Time: O(n) -- n / 2 calls, one comparison each.
// Space: O(n) -- n / 2 frames on the call stack.
//
// Known limit: the recursion is n / 2 calls deep, so a string of about 100,000 characters
// or more overflows the default stack. Only a very large input does; PalindromeIterative
// has no such limit.

public final class PalindromeRecursive {

  public static boolean isPalindrome(String str) {
    int n = str.length();
    return isPalindrome(str, 0, n - 1);             // START at both ends
  }

  private static boolean isPalindrome(String str, int start, int end) {
    if (start >= end) {
      return true;                                  // STOP: one character or none
    }
    if (str.charAt(start) != str.charAt(end)) {
      return false;                                 // MISMATCH
    }
    return isPalindrome(str, start + 1, end - 1);   // MATCH: test the part between them
  }

  public static void main(String[] args) {
    System.out.println(isPalindrome("malayalam")); // true
    System.out.println(isPalindrome("abba"));      // true
    System.out.println(isPalindrome("abca"));      // false
    System.out.println(isPalindrome(""));          // true
  }
}
