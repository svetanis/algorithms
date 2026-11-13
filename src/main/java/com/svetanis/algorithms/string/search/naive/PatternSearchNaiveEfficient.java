package com.svetanis.algorithms.string.search.naive;

import java.util.ArrayList;
import java.util.List;

// No LeetCode problem: find every occurrence of a pattern in a text, for a pattern whose letters
// are all different (the plain version of 28 asks only for the first occurrence).
//
// Input: a text and a pattern in which no letter appears twice. Returns the start index of every
// place the pattern occurs in the text, in increasing order. For a pattern that repeats a letter
// the skips below can jump over an occurrence, so the answer can be wrong (see main).
//
// The one idea: after comparing at start i, move i past starts that cannot match instead of by one.
// If the first j letters of the pattern matched and letter j did not, then none of the starts
// i + 1 .. i + j - 1 can match: each would need the pattern's first letter at a place the text
// holds one of pattern letters 1 .. j - 1, and the first letter appears nowhere else in the
// pattern. So the next start worth trying is i + j (or i + 1 when j is 0). After a full match
// the same argument rules out every start inside the match, so i moves on by m.
//
// Time: O(n) -- n = text.length; a round that compares j + 1 letters moves i on by at least
// max(j, 1), and a full match of m letters moves it by m, so the comparisons are at most twice
// the distance i travels.
// Space: O(1) -- besides the answer.
//
// Siblings: PatternSearchNaive tries every start, is correct for any pattern, and takes O(n * m).

public final class PatternSearchNaiveEfficient {

  public static List<Integer> search(String text, String patt) {

    int i = 0, j;
    int n = text.length();
    int m = patt.length();

    List<Integer> list = new ArrayList<>();
    while (i <= n - m) {
      for (j = 0; j < m; ++j) {
        if (text.charAt(i + j) != patt.charAt(j)) {
          break; // the first j letters matched
        }
      }

      if (j == m) {
        list.add(i); // RECORD: the pattern starts at i
        i += m; // SKIP: no start inside a match can match, the first letter is not repeated
      } else if (j == 0) {
        i += 1; // the first letter differs: try the next start
      } else {
        i += j; // SKIP: starts i + 1 .. i + j - 1 would need the first letter inside the match
      }
    }
    return list;
  }

  public static void main(String[] args) {
    System.out.println(search("ABCEABCDABCEABCD", "ABCD")); // [4, 12]
    System.out.println(search("AABAACAADAABAAABAA", "AABA")); // [0, 9, 13]  (A repeats; equal to the naive answer here only)
    System.out.println(search("AAAA", "AA")); // [0, 2]  (A repeats; the naive answer is [0, 1, 2])
    System.out.println(search("AAAB", "AAB")); // []  (A repeats; the naive answer is [1])
  }
}
