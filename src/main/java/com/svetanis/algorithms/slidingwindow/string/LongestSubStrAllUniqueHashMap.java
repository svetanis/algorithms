package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Math.max;

import java.util.Map;

public final class LongestSubStrAllUniqueHashMap {

  public static String allUnique(String str) {
    // Time complexity: O(n)
    int n = str.length();
    Map<Character, Integer> map = newHashMap();
    String unique = "";
    for(int left = 0, right = 0; right < n; right++) {
      char c = str.charAt(right);
      if(map.get(c) != null) {
        left = max(map.get(c) + 1, left);
      }
      if(unique.length() < right - left + 1) {
        unique = str.substring(left, right + 1);
      }
      map.put(c, right);
    }
    return unique;
  }

  public static void main(String[] args) {
    String str1 = "abcadbef";
    String out1 = allUnique(str1);
    System.out.println(out1 + ": " + out1.length());
    System.out.println();

    String str2 = "abac";
    String out2 = allUnique(str2);
    System.out.println(out2 + ": " + out2.length());
    System.out.println();

    String str3 = "aaaaa";
    String out3 = allUnique(str3);
    System.out.println(out3 + ": " + out3.length());
    System.out.println();

    String str4 = "abccdefgh";
    String out4 = allUnique(str4);
    System.out.println(out4 + ": " + out4.length());
    System.out.println();
  }
}
