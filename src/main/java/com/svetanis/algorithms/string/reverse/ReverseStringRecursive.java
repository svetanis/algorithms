package com.svetanis.algorithms.string.reverse;

// Reverse a string (LeetCode 344, Reverse String, asks for the in-place char[] version)
//
// Input: a string str. Returns str with its characters in the opposite order.
//
// The one idea: the reverse of a string is the reverse of everything after its first character,
// followed by that first character. A string of length 0 or 1 is its own reverse, which stops
// the recursion. The test is on the length of str itself: a space counts as a character, so " a"
// reverses to "a ".
//
// Time: O(n^2) -- n calls, and each one copies the rest of the string (substring) and builds the
// result (concatenation).
// Space: O(n^2) -- n calls are open at the deepest point, and each holds its own copy of the rest.
//
// Known limit: a string of about 10^5 characters throws OutOfMemoryError in the substring copies,
// and the recursion is n calls deep as well. Only very long input does this.
// ReverseStringIterative, ReverseStringSwap and ReverseStringStack have no such limit.
//
// Siblings: ReverseStringIterative, ReverseStringSwap, ReverseStringStack, ReverseStringSB,
// ReverseStringSubStr, ReverseStringXor and ReverseStringInPlace solve the same task without
// recursion.

public final class ReverseStringRecursive {

  public static String reverse(String str) {
    if (str == null || str.length() <= 1) { // BASE: length 0 or 1 is its own reverse
      return str;
    }
    return reverse(str.substring(1)) + str.charAt(0); // RETURN: reverse of the rest, then the first
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str)); // noos wohs VT tenretnI ecudortni ot gniog si ynoS
    System.out.println("[" + reverse(" a") + "]"); // [a ]
  }
}
