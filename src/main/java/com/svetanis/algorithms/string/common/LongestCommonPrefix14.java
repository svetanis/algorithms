package com.svetanis.algorithms.string.common;

// 14. Longest Common Prefix
//
// Input: an array of strings of lowercase letters. On LeetCode: 1 <= strs.length <= 200,
// 0 <= strs[i].length <= 200. An empty array is outside the constraints and is not handled.
// Returns: the longest string that every string starts with; "" when there is none.
//
// The one idea: the answer can only get shorter as more strings are read. Start with the
// first string as the prefix; for each next string keep only the part of the prefix that
// string also starts with. What is left after the last string is a prefix of all of them.
//
// Time: O(S) -- S the total number of characters; each fold compares at most as many
// characters as the shorter of the prefix and the string, so at most n * m for n strings
// of length m.
// Space: O(1) besides the answer -- substring copies the kept part once per string.
//
// Sibling -- the same problem:
//   LongestCommonPrefixColumnScan14 -- reads position 0 of every word, then position 1, ...

public final class LongestCommonPrefix14 {

  public static String lcp(String[] a) {
    String prefix = a[0];                      // START: the first word is the longest candidate
    for (int i = 1; i < a.length; i++) {
      prefix = lcp(a[i], prefix);              // FOLD: shorten it to what a[i] also starts with
    }
    return prefix;
  }

  private static String lcp(String s, String prefix) {
    int n = s.length();
    int m = prefix.length();
    int index = 0;
    for (index = 0; index < Math.min(n, m); index++) {
      if (s.charAt(index) != prefix.charAt(index)) {
        break;                                 // STOP at the first position that differs
      }
    }
    return prefix.substring(0, index);         // RETURN: the first index characters agree
  }

  public static void main(String[] args) {
    String[] s1 = { "flower", "flow", "flight" };
    String[] s2 = { "dog", "racecar", "car" };
    System.out.println(lcp(s1)); // fl
    System.out.println(lcp(s2)); // (empty line)
  }
}