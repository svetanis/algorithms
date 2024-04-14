package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;

import java.util.Map;

// Given a string, find the length of the longest 
// substring, which has all distinct characters.

public final class LongestAllUniqueSubStrLen {

  public static int allUniqueSubStrLen(String str) {
    // Time complexity: O(n)
	// Space complexity: O(1)

    int max = 0;
    int left = 0;
    Map<Character, Integer> map = newHashMap();
    for (int right = 0; right < str.length(); right++) {
      char c = str.charAt(right);
      // if map already contains the char 'c'
      // shrink the window from the beginning
      // so only one occurrence of 'c' remains
      if (map.containsKey(c)) {
        left = max(left, map.get(c) + 1);
      }
      // insert 'c' into the map
      map.put(c, right);
      // max length so far
      max = max(max, right - left + 1);
    }
    return max;
  }

  public static void main(String[] args) {
    System.out.println(allUniqueSubStrLen("abcadbef"));
    System.out.println(allUniqueSubStrLen("abac"));
    System.out.println(allUniqueSubStrLen("aaaaa"));
    System.out.println(allUniqueSubStrLen("abccdefgh"));
  }
}
