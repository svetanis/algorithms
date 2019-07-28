package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static com.svetanis.java.base.utils.Maps.freqMap;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

import java.util.Map;

public final class MinWindowSubStrLen {

  public static int minWindow(String s, String p) {
    int n = s.length();
    int m = p.length();

    int left = 0;
    int matched = 0;
    int min = MAX_VALUE;
    Map<Character, Integer> map = freqMap(p);

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
        min = min(min, right - left + 1);
        char front = s.charAt(left++);
        if (map.containsKey(front)) {
          if (map.get(front) == 0) {
            matched--;
          }
          map.put(front, map.get(front) + 1);
        }
      }
    }
    return min;
  }

  public static void main(String[] args) {
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(minWindow(s, t));

    String S = "coobdafceeaxab";
    String T = "abc";
    System.out.println(minWindow(S, T));
  }
}
