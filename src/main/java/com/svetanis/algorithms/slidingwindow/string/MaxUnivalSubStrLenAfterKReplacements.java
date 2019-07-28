package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;

import java.util.Map;

// Given a string with lowercase letters only, 
// if you are allowed to replace no more than ‘k’ letters 
// with any letter, find the length of the longest substring 
// having the same letters after replacement.

public final class MaxUnivalSubStrLenAfterKReplacements {

  public static int subStrLen(String str, int k) {
    // Time complexity: O(n)

    int max = 0;
    int left = 0;
    int count = 0; // max repeated letter count
    Map<Character, Integer> map = newHashMap();
    
    for (int right = 0; right < str.length(); right++) {
      char c = str.charAt(right);
      map.put(c, map.getOrDefault(c, 0) + 1);
      count = max(count, map.get(c));
      if (right - left + 1 - count > k) {
        char front = str.charAt(left);
        map.put(front, map.get(front) - 1);
        left++;
      }
      max = max(max, right - left + 1);
    }
    return max;
  }

  public static void main(String[] args) {
    System.out.println(subStrLen("aabccbb", 2));
    System.out.println(subStrLen("abbcb", 1));
    System.out.println(subStrLen("abccde", 1));
  }
}
