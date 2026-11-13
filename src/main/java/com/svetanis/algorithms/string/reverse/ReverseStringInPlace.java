package com.svetanis.algorithms.string.reverse;

import static java.lang.String.valueOf;

// Reverse a string (LeetCode 344, Reverse String, asks for the in-place char[] version)
//
// Input: a string str. Returns str with its characters in the opposite order.
//
// The one idea: character i trades places with character n - 1 - i. Only the first half of the
// positions, i = 0 .. n / 2 - 1, needs a visit: every trade moves two characters at once, so
// going on past the middle would swap each pair a second time and put it back.
//
// Time: O(n) -- n / 2 swaps of constant cost.
// Space: O(n) -- the char[] copy, since a Java String cannot be changed in place.
//
// Siblings: ReverseStringSwap makes the same swaps with two pointers that walk toward each other;
// ReverseStringXor swaps with XOR; ReverseStringSubmit (344) works on the char[] it is given.

public final class ReverseStringInPlace {

  public static String reverse(String str) {
    char[] chars = str.toCharArray();
    int n = chars.length;

    for (int i = 0; i < n / 2; i++) { // WALK: first half only
      int j = n - 1 - i; // MIRROR: the position that trades with i
      swap(chars, i, j);
    }
    return valueOf(chars);
  }

  private static void swap(char[] chars, int i, int j) {
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
  }

  public static void main(String[] args) {
    String str = "introduce";
    System.out.println(reverse(str)); // ecudortni
  }
}