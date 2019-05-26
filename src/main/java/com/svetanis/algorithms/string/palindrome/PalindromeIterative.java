package com.svetanis.algorithms.string.palindrome;

// Palindrome is a sentence that may be read the same way in either direction

public final class PalindromeIterative {

  public static boolean palindrome(String str) {
    int n = str.length();
    for (int i = 0; i < n / 2 + 1; ++i) {
      if (str.charAt(i) != str.charAt(n - i - 1)) {
        return false;
      }
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
    while (right > left) {
      if (chars[left++] != chars[right--]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String str = "ABCDEDCBA";
    System.out.println(isPalindrome(str));
  }
}
