package com.svetanis.algorithms.string.reverse;

// Reverse a string (LeetCode 344, Reverse String, asks for the in-place char[] version)
//
// Input: a string str. Returns str with its characters in the opposite order.
//
// The one idea: step i = 0, 1, 2, ... takes the one-character piece of str that sits i places
// from the end, which is substring(n - i - 1, n - i), and appends it. The pieces come out last
// to first, so the builder ends up holding str backwards. The letters array keeps each piece as
// well; nothing reads it back, so the result would be the same without it.
//
// Time: O(n) -- n one-character substrings, each of constant cost.
// Space: O(n) -- the letters array and the builder.
//
// Siblings: ReverseStringIterative also reads the characters backwards, as chars in a list;
// ReverseStringSwap and ReverseStringInPlace swap in place.

public final class ReverseStringSubStr {

  public static String reverse(String str) {
    int n = str.length();
    String[] letters = new String[n];
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < n; i++) { // WALK: i places from the end
      letters[i] = str.substring(n - i - 1, n - i); // TAKE: the one-character piece
      sb.append(letters[i]);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str)); // noos wohs VT tenretnI ecudortni ot gniog si ynoS
  }
}
