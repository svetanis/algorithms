package com.svetanis.algorithms.slidingwindow.string.anagram;

import static com.svetanis.java.base.utils.Maps.freqMap;

import java.util.Map;

// Given a string and a pattern, 
// find out if the string contains any permutation of the pattern.

public final class ContainsAnagram {

  public static boolean contains(String str, String pat) {
    // Time complexity: O(n)

    int n = str.length();
    int left = 0;
    int matched = 0;
    Map<Character, Integer> map = freqMap(pat.toCharArray());

    for (int right = 0; right < n; right++) {
      char end = str.charAt(right);
      if (map.containsKey(end)) {
        map.put(end, map.get(end) - 1);
        if (map.get(end) == 0) {
          matched++;
        }
      }

      if (matched == map.size()) {
        return true;
      }

      if (right >= pat.length() - 1) {
        char start = str.charAt(left++);
        if (map.containsKey(start)) {
          if (map.get(start) == 0) {
            matched--;
          }
          map.put(start, map.get(start) + 1);
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    String str = "coding interview questions";
    String pat = "weivretni";
    System.out.println(contains(str, pat));
  }
}
