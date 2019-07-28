package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.svetanis.java.base.utils.Maps.freqMap;
import static java.lang.Integer.MAX_VALUE;

import java.util.Map;

import com.google.common.base.Optional;

public final class MinWindowSubStr {

  public static Optional<String> minWindow(String s, String p) {
    int n = s.length();
    int m = p.length();

    Map<Character, Integer> map = freqMap(p);

    int left = 0;
    int matched = 0;
    int start = 0;
    int min = MAX_VALUE;

    for (int right = 0; right < n; right++) {
      char c = s.charAt(right);
      if (map.containsKey(c)) {
        map.put(c, map.get(c) - 1);
        if (map.get(c) >= 0) {
          matched++;
        }
      }
      // shrink window
      while (matched == m) {
        if (min > right - left + 1) {
          min = right - left + 1;
          start = left;
        }

        char front = s.charAt(left++);
        if (map.containsKey(front)) {
          if (map.get(front) == 0) {
            matched--;
          }
          map.put(front, map.get(front) + 1);
        }
      }

    }
    return min > n ? absent() : of(s.substring(start, start + min));
  }

  public static void main(String[] args) {
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(minWindow(s, t)); // BANC

    String S = "coobdafceeaxab";
    String T = "abc";
    System.out.println(minWindow(S, T)); // bdafc
  }
}
