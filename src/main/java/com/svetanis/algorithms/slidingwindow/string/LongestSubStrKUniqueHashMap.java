package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import com.google.common.base.Optional;

// Given a string you need to print longest possible substring 
// that has exactly K unique characters. 

public final class LongestSubStrKUniqueHashMap {

  private static final int MAX = 256;

  public static Optional<String> kUnique(String str, int k) {
    // Time complexity: O(n)

    int n = str.length();
    if (countUnique(str) < k) {
      return absent();
    }

    int left = 0; // current start
    int start = 0; // max window start
    int end = 0; // max window end
    int max = 0; // max window size
    Map<Character, Integer> map = newHashMap();

    for (int right = 0; right < n; right++) {
      char next = str.charAt(right);
      int freq = map.getOrDefault(next, 0) + 1;
      map.put(next, freq);
      while (map.size() > k) {
        char front = str.charAt(left);
        map.put(front, map.get(front) - 1);
        if (map.get(front) == 0) {
          map.remove(front);
        }
        left++;
      }
      if (map.size() == k && right - left + 1 > max) {
        max = right - left + 1;
        start = left;
        end = right;
      }
    }
    return of(str.substring(start, end + 1));
  }

  public static int countUnique(String str) {
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
