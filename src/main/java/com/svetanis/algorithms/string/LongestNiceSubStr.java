package com.svetanis.algorithms.string;

import java.util.HashSet;
import java.util.Set;

// 1763. Longest Nice Substring
//
// Input: a string s of English letters. A string is nice if every letter it contains appears in
// both its lowercase and its uppercase form. Returns the longest nice substring; if several are
// equally long, the one that starts first; if there is none, "". On LeetCode:
// 1 <= s.length <= 100.
//
// The one idea (dfs): look at the letters of s. If every letter has both forms, s is nice and
// is the answer. Otherwise some character c has no partner anywhere in s, so no nice substring
// can contain c: cut s at c and solve the left and right pieces on their own. Take the longer
// answer, the left one when they tie, because it starts first.
//
// The other method (longestNiceSubstr) is brute force: for every start i, grow the end j one
// character at a time, keep the letters of s[i .. j] in a set, and test whether every letter in
// the set has both forms.
//
// Time: dfs O(n^2) -- each call scans its string once and splits it at the first bad character,
// and a chain of splits is at most n deep. longestNiceSubstr O(n^2) -- n^2 substrings, each
// tested against a set of at most 52 letters.
// Space: dfs O(n^2) -- up to n nested calls, each holding a copy of its piece of s.
// longestNiceSubstr O(1) -- a set of at most 52 letters.
//
// Siblings: dfs splits at a bad character; longestNiceSubstr tries every substring.

public final class LongestNiceSubStr {

  public static String dfs(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>();
    for (char c : s.toCharArray()) {
      set.add(c); // COLLECT: the letters of s
    }

    for (int i = 0; i < n; i++) {
      char curr = s.charAt(i);
      char low = Character.toLowerCase(curr);
      char upp = Character.toUpperCase(curr);
      if (set.contains(low) && set.contains(upp)) { // SKIP: this letter has its partner
        continue;
      }
      String left = dfs(s.substring(0, i)); // SPLIT: no nice substring contains s[i]
      String right = dfs(s.substring(i + 1));
      return left.length() >= right.length() ? left : right; // RETURN: ties go to the left
    }
    return s; // BASE: every letter has its partner, s is nice
  }

  public static String longestNiceSubstr(String s) {
    int n = s.length();
    int maxLen = 0;
    int start = 0;
    for (int i = 0; i < n; i++) { // WALK: every start
      Set<Character> set = new HashSet<>();
      for (int j = i; j < n; j++) { // WALK: grow the end
        set.add(s.charAt(j));
        boolean isNice = true;
        for (char curr : set) { // TEST: every letter needs both forms
          char low = Character.toLowerCase(curr);
          char upp = Character.toUpperCase(curr);
          if (!set.contains(low) || !set.contains(upp)) {
            isNice = false;
            break;
          }
        }
        if (isNice && maxLen < j - i + 1) { // RECORD: strictly longer, so ties keep the first
          maxLen = j - i + 1;
          start = i;
        }
      }

    }
    return start == -1 ? "" : s.substring(start, start + maxLen);
  }

  public static void main(String[] args) {
    System.out.println(dfs("YazaAay")); // aAa
    System.out.println(dfs("Bb")); // Bb
    System.out.println(dfs("c")); // (empty line)
    System.out.println(longestNiceSubstr("YazaAay")); // aAa
    System.out.println(longestNiceSubstr("Bb")); // Bb
    System.out.println(longestNiceSubstr("c")); // (empty line)
  }
}
