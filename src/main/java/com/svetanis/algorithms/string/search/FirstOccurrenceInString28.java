package com.svetanis.algorithms.string.search;

// 28. Find the Index of the First Occurrence in a String
//
// Input: two strings, haystack and needle. Returns the index in haystack where needle first
// occurs, or -1 if it does not occur. On LeetCode: 1 <= haystack.length, needle.length <= 10^4,
// lowercase letters. An empty needle (outside those limits) returns 0.
//
// The one idea: two pointers, hp into haystack and np into needle, and the match so far is the
// np letters just behind hp. While the letters agree, both move on; when np reaches the needle's
// length, the match began np places back. On a mismatch, the next start to try is one place after
// the start that just failed. That start is hp - np, so hp moves to hp - np + 1 -- written
// hp -= np - 1 -- and np goes back to 0.
//
// The same brute force, written a second way (searchStartsWith): try every start. A start i can
// hold the needle only if the needle fits, so the last start to try is haystack.length -
// needle.length; at each start, startsWith compares the needle with the letters from i on. The
// first start that matches is the answer.
//
// Time: O(n * m) for both -- n = haystack.length, m = needle.length; a long partial match is
// thrown away and re-read, e.g. haystack "aaaa...a" with needle "aaa...ab".
// Space: O(1) -- two pointers, or one start index.

public final class FirstOccurrenceInString28 {

  public static int search(String haystack, String needle) {
    if (needle.isEmpty()) {
      return 0; // BASE: an empty needle is found at the front
    }
    int n = haystack.length();
    int m = needle.length();
    int hp = 0;
    int np = 0;
    while (hp < n) {
      if (haystack.charAt(hp) == needle.charAt(np)) {
        if (m == 1) {
          return hp; // RETURN: a one-letter needle is found at once
        }
        hp++; // MATCH: both pointers move on
        np++;
      } else {
        hp -= np - 1; // BACK UP: to one place after the start that failed
        np = 0;
      }

      if (np == m) {
        return hp - np; // RETURN: the whole needle matched; it began np places back
      }
    }
    return -1;
  }

  public static int searchStartsWith(String haystack, String needle) {
    if (needle.isEmpty()) {
      return 0; // BASE: an empty needle is found at the front
    }
    int starts = haystack.length() - needle.length() + 1; // the number of starts that leave room
    for (int i = 0; i < starts; ++i) { // WALK: every start in order; none when the needle is longer
      if (haystack.startsWith(needle, i)) {
        return i; // RETURN: the first start that matches
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(search("sadbutsad", "sad")); // 0
    System.out.println(search("leetcode", "leeto")); // -1
    System.out.println(searchStartsWith("This is a simple string", "simple")); // 10
    System.out.println(searchStartsWith("sadbutsad", "sad")); // 0
    System.out.println(searchStartsWith("leetcode", "leeto")); // -1
  }
}
