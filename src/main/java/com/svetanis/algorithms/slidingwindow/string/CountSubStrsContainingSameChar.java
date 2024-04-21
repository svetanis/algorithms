package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Maps.newHashMap;
import java.util.Map;

// given a string and an integer k
// count substrings of length k
// which are comprised of the same char

public final class CountSubStrsContainingSameChar {

  public static int count(String str, int k) {
    int len = 0; // length of sliding window
    int left = 0;
    int count = 0;

    Map<Character, Integer> map = newHashMap();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      int freq = map.getOrDefault(c, 0);
      map.put(c, freq + 1);
      len++;

      // if the len of the substr is greater than k then
      // remove the char from the beginning of the string
      if (len > k) {
        char first = str.charAt(left);
        map.put(first, map.get(first) - 1);
        len--;
        left++;
      }

      // if the freq of one the char is equal to len of
      // the substr itself, then all chars are the same
      if (len == k && map.get(c) == len) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    String s1 = "aaaabbbccdddd";
    System.out.println(count(s1, 4));

    String s2 = "aaaaaa";
    System.out.println(count(s2, 4));
  }
}
