package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.algorithms.slidingwindow.string.LongestSubStrKUniqueHashMap.countUnique;
import static java.lang.Math.max;

import java.util.Map;

// Given a string, find the length of the longest 
// substring with no more than K unique characters 

public final class LongestSubStrLenKUnique {

  public static int kUniqueMaxLen(String str, int k) {
    // Time complexity: O(n)

    int n = str.length();
    if (countUnique(str) < k) {
      return 0;
    }

    int left = 0; // current start
    int max = 0; // max window size
    Map<Character, Integer> map = newHashMap();
    for (int right = 0; right < n; right++) {
      char next = str.charAt(right);
      int freq = map.getOrDefault(next, 0) + 1;
      map.put(next, freq);
      // shrink the sliding window, until k
      // distinct chars left in frequency map
      while (map.size() > k) {
        char front = str.charAt(left);
        map.put(front, map.get(front) - 1);
        if (map.get(front) == 0) {
          map.remove(front);
        }
        left++; // shrink the window
      }
      // max length so far
      if (map.size() == k) {
        max = max(max, right - left + 1);
      }
    }
    return max;
  }

  public static void main(String[] args) {
    String str1 = "aabbcc";
    System.out.println(kUniqueMaxLen(str1, 1));
    System.out.println(kUniqueMaxLen(str1, 2));
    System.out.println(kUniqueMaxLen(str1, 3));

    String str2 = "aaabbb";
    System.out.println(kUniqueMaxLen(str2, 3));

    String str3 = "aabacbebebe";
    System.out.println(kUniqueMaxLen(str3, 3));

    String str4 = "araaci";
    System.out.println(kUniqueMaxLen(str4, 2));

    String str5 = "araaci";
    System.out.println(kUniqueMaxLen(str5, 1));
    
    String str6 = "cbbebi";
    System.out.println(kUniqueMaxLen(str6, 3));
  }
}
