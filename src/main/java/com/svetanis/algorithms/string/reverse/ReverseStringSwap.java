package com.svetanis.algorithms.string.reverse;

import static java.lang.String.valueOf;

// Reverse a string (LeetCode 344, Reverse String, asks for the in-place char[] version)
//
// Input: a string str. Returns str with its characters in the opposite order.
//
// The one idea: two pointers, one at each end. Swap the two characters they point to, move both
// one step inward, and stop when they meet or cross: every pair has then been swapped once, and
// a middle character, when the length is odd, stays where it is.
//
// Time: O(n) -- n / 2 swaps of constant cost.
// Space: O(n) -- the char[] copy, since a Java String cannot be changed in place.
//
// Siblings: ReverseStringInPlace makes the same swaps with one index i and its mirror n - 1 - i;
// ReverseStringXor swaps with XOR; ReverseStringSubmit (344) works on the char[] it is given.

public final class ReverseStringSwap {

  public static String reverse(String str) {
    char[] chars = str.toCharArray();
    int n = chars.length;
    reverse(chars, 0, n - 1);
    return valueOf(chars);
  }

  public static void reverse(char[] chars, int low, int high) {
    while (low < high) { // WALK: until the pointers meet or cross
      swap(chars, low, high);
      ++low;
      --high;
    }
  }

  private static void swap(char[] chars, int i, int j) {
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str)); // noos wohs VT tenretnI ecudortni ot gniog si ynoS
  }
}
