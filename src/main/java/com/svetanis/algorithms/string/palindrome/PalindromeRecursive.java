package com.svetanis.algorithms.string.palindrome;

// Palindrome is a sentence that may be read the same way in either direction

public final class PalindromeRecursive {

  public static boolean isPalindrome(String str) {
    int n = str.length();
    return isPalindrome(str, 0, n - 1);
  }

  private static boolean isPalindrome(String str, int start, int end) {
    if (start == end) {
      return true;
    }
    if (str.charAt(start) != str.charAt(end)) {
      return false;
    }
    if (start < end + 1) {
      return isPalindrome(str, start + 1, end - 1);
    }
    return true;
  }

  public static void main(String[] args) {
    String str = "malayalam";
    System.out.println(isPalindrome(str));
  }
}
