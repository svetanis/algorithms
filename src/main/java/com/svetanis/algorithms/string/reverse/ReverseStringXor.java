package com.svetanis.algorithms.string.reverse;

public final class ReverseStringXor {

  public static String reverse(String str) {
    int n = str.length();
    char[] chars = str.toCharArray();
    return reverse(chars, 0, n - 1);
  }

  public static String reverse(char[] chars, int start, int end) {
    while (start < end) {
      chars[start] ^= chars[end];
      chars[end] ^= chars[start];
      chars[start] ^= chars[end];
      start++;
      end--;
    }
    return String.valueOf(chars);
  }

  public static void main(String[] args) {
    String str = "GeeksforGeeks";
    System.out.println(reverse(str));
  }
}
