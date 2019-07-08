package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.utils.Maps.freqMap;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

import java.util.Map;

public final class MinWindowSubStrLen {

  public static int minWindow(String s, String p) {
    int n = s.length();
    int m = p.length();

    Map<Character, Integer> map = freqMap(p);
    Map<Character, Integer> sw = newHashMap();

    int left = 0;
    int count = 0;
    int min = MAX_VALUE;
    for (int right = 0; right < n; right++) {
      char c = s.charAt(right);
      // skip chars not in t
      if (!map.containsKey(c)) {
        continue;
      }
      sw.put(c, sw.getOrDefault(c, 0) + 1);
      if (sw.get(c) <= map.get(c)) {
        count++;
      }

      // if window constraint is satisfied
      if (count == m) {
        // advance begin index as far right as possible,
        // stop when advancing breaks window constraint
        while (!map.containsKey(s.charAt(left)) || 
            sw.get(s.charAt(left)) > map.get(s.charAt(left))) {
          char ch = s.charAt(left);
          if (map.containsKey(ch) && sw.get(ch) > map.get(ch)) {
            sw.put(ch, sw.get(ch) - 1);
          }
          left++;
        }
        min = min(min, right - left + 1);
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
