package com.svetanis.algorithms.string;

// 58. Length of Last Word

public final class LengthOfLastWord58 {
  // Time Complexity: O(n)

  private static int len(String s) {
    int end = s.length() - 1;
    while (end >= 0 && s.charAt(end) == ' ') {
      end--;
    }
    int start = end;
    while (start >= 0 && s.charAt(start) != ' ') {
      start--;
    }
    return end - start;
  }

  public static void main(String[] args) {
    System.out.println(len("Hello World")); // 5
    System.out.println(len("    fly me    to   the moon  ")); // 4
    System.out.println(len("luffy is still joyboy")); // 6
  }
}