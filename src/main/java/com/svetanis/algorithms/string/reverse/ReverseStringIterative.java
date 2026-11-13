package com.svetanis.algorithms.string.reverse;

import java.util.ArrayList;
import java.util.List;

// Reverse a string (LeetCode 344, Reverse String, asks for the in-place char[] version)
//
// Input: a string str. Returns str with its characters in the opposite order.
//
// The one idea: read the characters from the last index down to 0 and put each one, as it is read,
// at the end of a new list. The first character read (the last of str) lands first, so the list
// is str backwards; the list is then written out as a string.
//
// Time: O(n) -- one read and one append per character.
// Space: O(n) -- the char[] copy and the list of n boxed characters.
//
// Siblings: ReverseStringSB, ReverseStringStack and ReverseStringSubStr also build a new string
// from the characters read backwards; ReverseStringSwap and ReverseStringInPlace swap in place.

public final class ReverseStringIterative {

  public static String reverse(String str) {
    char[] chars = str.toCharArray();
    int n = chars.length;
    List<Character> list = new ArrayList<>();
    for (int i = n - 1; i >= 0; i--) { // WALK: from the last index down
      list.add(chars[i]); // APPEND: the last character read ends up last
    }
    StringBuilder sb = new StringBuilder();
    for (char c : list) {
      sb.append(c);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str)); // noos wohs VT tenretnI ecudortni ot gniog si ynoS
  }
}
