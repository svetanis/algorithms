package com.svetanis.algorithms.string.search.naive;

import java.util.ArrayList;
import java.util.List;

// No LeetCode problem: find every occurrence of a pattern in a text (the plain version of 28,
// which asks only for the first).
//
// Input: a text and a pattern. Returns the start index of every place the pattern occurs in the
// text, in increasing order; occurrences may overlap.
//
// The one idea: try every start. A start i can hold the pattern only if the pattern fits, so i
// runs from 0 to n - m; at each start, compare the pattern with the text letter by letter and
// stop at the first difference. If all m letters agreed, record i. Nothing is skipped, so
// overlapping occurrences are all found.
//
// Time: O(n * m) -- n = text.length, m = patt.length; n - m + 1 starts, each compared in at most
// m letters.
// Space: O(1) -- besides the answer.
//
// Siblings: PatternSearchNaiveEfficient skips ahead after a mismatch or a match, which is correct
// only for a pattern whose letters are all different.

public final class PatternSearchNaive {

  public static List<Integer> search(String text, String patt) {

    int j;
    int n = text.length();
    int m = patt.length();

    List<Integer> list = new ArrayList<>();
    for (int i = 0; i <= n - m; ++i) { // WALK: every start where the pattern fits
      for (j = 0; j < m; ++j) {
        if (text.charAt(i + j) != patt.charAt(j)) {
          break; // SKIP: this start fails at the first difference
        }
      }
      if (j == m) { // all m letters agreed
        list.add(i); // RECORD: the pattern starts at i
      }
    }
    return list;
  }

  public static void main(String[] args) {
    System.out.println(search("AABAACAADAABAAABAA", "AABA")); // [0, 9, 13]
    System.out.println(search("ABCEABCDABCEABCD", "ABCD")); // [4, 12]
    System.out.println(search("AAAA", "AA")); // [0, 1, 2]
  }
}
