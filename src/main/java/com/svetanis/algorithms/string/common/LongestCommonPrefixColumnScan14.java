package com.svetanis.algorithms.string.common;

// 14. Longest Common Prefix
//
// Input: an array of strings of lowercase letters. On LeetCode: 1 <= strs.length <= 200,
// 0 <= strs[i].length <= 200. An empty array is outside the constraints and is not handled.
// Returns: the longest string that every string starts with; "" when there is none.
//
// The one idea: look at one column at a time. Column i holds the i-th character of every
// word. The prefix ends at the first column where some word has already ended or its
// letter differs from the first word's; every column before it agreed in all words, so
// those characters of the first word are the answer. The first word is the longest the
// answer can be, so only its columns are tried.
//
// Time: O(S) -- S the total number of characters; at most n * m comparisons for n words
// of length m, and it stops at the first column that fails.
// Space: O(1) besides the answer.
//
// Siblings: LongestCommonPrefix14 -- the same problem as a fold: shorten a running
// prefix word by word instead of scanning column by column.

public final class LongestCommonPrefixColumnScan14 {

  public static String lcp(String[] a) {
    String s = a[0];
    for (int i = 0; i < s.length(); i++) {     // WALK the columns of the first word
      for (String word : a) {
        if (i >= word.length() || word.charAt(i) != s.charAt(i)) {
          return s.substring(0, i);            // RETURN: column i failed, so i characters agreed
        }
      }
    }
    return s;                                  // RETURN: every column of the first word agreed
  }

  public static void main(String[] args) {
    String[] s1 = { "flower", "flow", "flight" };
    String[] s2 = { "dog", "racecar", "car" };
    String[] s3 = { "flower", "flow" };
    System.out.println(lcp(s1)); // fl
    System.out.println(lcp(s2)); // (empty line)
    System.out.println(lcp(s3)); // flow
  }
}
