package com.svetanis.algorithms.string.substr;

import java.util.ArrayList;
import java.util.List;

// No LeetCode problem: list every substring of a string.
//
// Input: a string str. Returns every substring of str, one entry for each pair of positions, so
// a substring that occurs twice is listed twice. They come ordered by start position, then by
// length.
//
// The one idea: a substring is fixed by where it starts (i) and where it stops (j, exclusive), so
// two loops over i and j list each one exactly once -- the stop is always after the start, so j
// runs from i + 1.
//
// Time: O(n^3) -- n * (n + 1) / 2 substrings, and building each one copies up to n letters.
// Space: O(n^3) -- the answer holds all of them.

public final class AllSubStrs {

  public static List<String> substrs(String str) {
    int n = str.length();
    List<String> list = new ArrayList<>();

    for (int i = 0; i < n; i++) { // START: the first letter
      for (int j = i + 1; j <= n; j++) { // STOP: one past the last letter
        list.add(str.substring(i, j)); // RECORD: str[i .. j - 1]
      }
    }
    return list;
  }

  public static void main(String[] args) {
    String str = "abcd";
    System.out.println(substrs(str)); // [a, ab, abc, abcd, b, bc, bcd, c, cd, d]
  }
}
