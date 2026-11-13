package com.svetanis.algorithms.string.reverse;

// Reverse a string (LeetCode 344, Reverse String, asks for the in-place char[] version)
//
// Input: a string str. Returns str with its characters in the opposite order.
//
// The one idea: two characters a and b can trade places with no third variable, using XOR three
// times: a ^= b gives a ^ b; b ^= a then gives b ^ (a ^ b) = a; a ^= b then gives (a ^ b) ^ a = b.
// The two pointers walk inward as in ReverseStringSwap. The trick fails if both names point to
// the same cell (the cell becomes 0), which cannot happen here because the loop runs only while
// start < end.
//
// Time: O(n) -- n / 2 swaps of three XORs each.
// Space: O(n) -- the char[] copy, since a Java String cannot be changed in place.
//
// Siblings: ReverseStringSwap does the same walk with an ordinary temporary variable, which is
// the version to write in an interview; this one only shows the XOR identity.

public final class ReverseStringXor {

  public static String reverse(String str) {
    int n = str.length();
    char[] chars = str.toCharArray();
    return reverse(chars, 0, n - 1);
  }

  public static String reverse(char[] chars, int start, int end) {
    while (start < end) { // WALK: start < end, so the two cells are always different
      chars[start] ^= chars[end]; // XOR: chars[start] now holds a ^ b
      chars[end] ^= chars[start]; // XOR: chars[end] now holds a
      chars[start] ^= chars[end]; // XOR: chars[start] now holds b
      start++;
      end--;
    }
    return String.valueOf(chars);
  }

  public static void main(String[] args) {
    String str = "GeeksforGeeks";
    System.out.println(reverse(str)); // skeeGrofskeeG
  }
}
