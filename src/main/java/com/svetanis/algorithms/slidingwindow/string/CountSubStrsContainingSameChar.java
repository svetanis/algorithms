package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// Count Substrings of Length K Made of One Repeated Character
// (no LeetCode number; source unknown)
//
// Input: a string and a number k >= 1.
// Return: how many substrings of exactly k characters consist of a single
// character repeated, such as "aaa" for k = 3; 0 when k is longer than the string.
// k = 0 is outside the problem and gives a meaningless number (the string's
// length); no rule for it is known, so it is left as it is.
//
// The one idea: a window of k characters is one character repeated exactly when
// some character fills all k places. The only candidate is the character that just
// came in on the right -- it is in the window, so if the window is uniform it is
// that character. So after each step, compare that one character's count with k.
//
// Siblings: none -- no other file in the repo solves this problem.
//   CountSubStrsASCIISumDivisibleByK uses the same fixed-length window with a
//   different test.
//
// Time: O(n) -- each character comes in once and goes out at most once.
// Space: O(d) -- one map entry per different character seen; entries are not
//   removed when their count falls to 0, so d is the number of different
//   characters in the whole string.

public final class CountSubStrsContainingSameChar {

  public static int count(String str, int k) {
    int len = 0; // length of sliding window
    int left = 0;
    int count = 0;

    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      int freq = map.getOrDefault(c, 0);
      map.put(c, freq + 1); // IN: c enters on the right
      len++;

      if (len > k) {
        char first = str.charAt(left);
        map.put(first, map.get(first) - 1); // OUT: one past k, the leftmost character leaves
        len--;
        left++;
      }

      if (len == k && map.get(c) == len) {
        count++; // RECORD: c fills all k places
      }
    }
    return count;
  }

  public static void main(String[] args) {
    String s1 = "aaaabbbccdddd";
    System.out.println(count(s1, 4));

    String s2 = "aaaaaa";
    System.out.println(count(s2, 4));
  }
}
