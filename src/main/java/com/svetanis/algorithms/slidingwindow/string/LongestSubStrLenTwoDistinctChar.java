package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;

import java.util.Map;

// Given a string, find the length of the longest 
// substring that contains exactly two distinct char 

public final class LongestSubStrLenTwoDistinctChar {

  public static int maxLen(String str) {
    // Time complexity: O(n)

    int n = str.length();

    int left = 0; // current start
    int max = 0; // max window size
    Map<Character, Integer> map = newHashMap();
    for (int right = 0; right < n; right++) {
      char next = str.charAt(right);
      int freq = map.getOrDefault(next, 0) + 1;
      map.put(next, freq);
      // shrink the sliding window, until k
      // distinct chars left in frequency map
      while (map.size() > 2) {
        char front = str.charAt(left);
        map.put(front, map.get(front) - 1);
        if (map.get(front) == 0) {
          map.remove(front);
        }
        left++; // shrink the window
      }
      if(map.size() == 2) {
        max = max(max, right - left + 1);
      }
    }
    return max;
  }

  public static void main(String[] args) {
    String str1 = "ababababa";
    System.out.println(maxLen(str1));

    String str2 = "e";
    System.out.println(maxLen(str2));

    String str3 = "baabcbab";
    System.out.println(maxLen(str3));
  }
}
