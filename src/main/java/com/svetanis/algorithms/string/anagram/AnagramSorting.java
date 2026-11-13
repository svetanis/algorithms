package com.svetanis.algorithms.string.anagram;

import static java.util.Arrays.sort;

import java.util.Arrays;

// Valid anagram, by sorting -- not a LeetCode file; the question is LeetCode 242, which
// AnagramCounting carries.
//
// Input: two strings s1 and s2. Returns: true when s2 is an anagram of s1 -- the same
// characters, each the same number of times, in any order. Two empty strings are anagrams.
//
// The one idea: sorting puts any arrangement of the same characters into one order, so two
// strings are anagrams exactly when their sorted characters are equal. Different lengths are
// answered first.
//
// Siblings -- the same question, answered three other ways:
//   AnagramCounting -- counts each character in a fixed array of 256 slots: O(n), no sort
//   AnagramHashing  -- the same counting, kept in a HashMap from character to count
//   AnagramSubStr   -- crosses each character of s1 off a shrinking copy of s2; O(n^2)

public final class AnagramSorting {
  // Time Complexity: O(n log n), the two sorts; the comparison after them is one pass
  // Space Complexity: O(n), the two character arrays that are sorted

  public static boolean areAnagrams(String s1, String s2) {
    if (s1.length() != s2.length()) { // LENGTH: different lengths are never anagrams
      return false;
    }
    char[] chars1 = s1.toCharArray(); // COPY: strings cannot be sorted in place
    char[] chars2 = s2.toCharArray();
    sort(chars1); // SORT: anagrams become the same array
    sort(chars2);
    return Arrays.equals(chars1, chars2); // RETURN: equal once sorted
  }

  public static void main(String[] args) {
    for (String[] pair : pairs()) {
      String w1 = pair[0];
      String w2 = pair[1];
      System.out.println(areAnagrams(w1, w2));
    }
  }

  private static String[][] pairs() {
    return new String[][] {
        { "anagramm", "nagaramm" }, // true
        { "aaca", "aca" }, // false
        { "apple", "papel" }, // true
        { "carrot", "tarroc" }, // true
        { "hello", "llloh" }, // false
    };
  }
}
