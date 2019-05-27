package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;

import java.util.Map;

// Given a string you need to print longest possible substring 
// that has exactly K unique characters. 

public final class LongestSubStrLenKUnique {

  private static final int MAX = 256;

  public static int kUnique(String str, int k) {
    // Time complexity: O(n)

    int n = str.length();
    if (countUnique(str) < k) {
      return 0;
    }

    int left = 0; // current start
    int right = 0; // current end
    int max = 0; // max window size
    Map<Character, Integer> map = newHashMap();
    while (right < n) {
      char next = str.charAt(right);
      map.put(next, map.getOrDefault(next, 0) + 1);
      while (map.size() > k) {
        char front = str.charAt(left);
        map.put(front, map.get(front) - 1);
        if (map.get(front) == 0) {
          map.remove(front);
        }
        left++;
      }
      if (map.size() == k) {
        max = max(max, right - left + 1);
      }
      right++;
    }
    return max;
  }

  private static int countUnique(String str) {
    int k = 0;
    int[] count = new int[MAX];
    // count num of unique chars
    for (char c : str.toCharArray()) {
      if (count[c] == 0) {
        k++;
      }
      count[c]++;
    }
    return k;
  }

  public static void main(String[] args) {
    String str1 = "aabbcc";
    System.out.println(kUnique(str1, 1));
    System.out.println(kUnique(str1, 2));
    System.out.println(kUnique(str1, 3));

    String str2 = "aaabbb";
    System.out.println(kUnique(str2, 3));

    String str3 = "aabacbebebe";
    System.out.println(kUnique(str3, 3));
  }
}
