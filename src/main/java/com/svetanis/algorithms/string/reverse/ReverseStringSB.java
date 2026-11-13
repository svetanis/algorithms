package com.svetanis.algorithms.string.reverse;

// Reverse a string (LeetCode 344, Reverse String, asks for the in-place char[] version)
//
// Input: a string str. Returns str with its characters in the opposite order.
//
// The one idea: StringBuilder already has reverse(), so the whole method is one call; it is the
// version to write when the interviewer allows the library. The point of the other files in this
// package is to show what that call does inside.
//
// Time: O(n) -- the builder copies str once and reverses it.
// Space: O(n) -- the builder holds a copy of the characters.
//
// Siblings: ReverseStringSwap and ReverseStringInPlace write that loop by hand; ReverseStringStack,
// ReverseStringIterative and ReverseStringSubStr build the result from the characters read
// backwards.

public final class ReverseStringSB {

  public static String reverse(String str) {
    return new StringBuilder(str).reverse().toString(); // RETURN: the library does the swaps
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str)); // noos wohs VT tenretnI ecudortni ot gniog si ynoS
  }
}
