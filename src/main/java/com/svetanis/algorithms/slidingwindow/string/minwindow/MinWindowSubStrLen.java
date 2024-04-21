package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static com.svetanis.java.base.utils.Maps.freqMap;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

import java.util.Map;

// given a string and a pattern, find the smallest substring
// in the given string which has all the chars of the given pattern
// the required substring can have some additional characters
// and doesn't need to be a permutation of the pattern.

public final class MinWindowSubStrLen {
  // Time complexity: O(n + m)	
	
  public static int minWindow(String s, String p) {
    int n = s.length();
    int m = p.length();

    int left = 0;
    int matched = 0;
    int min = MAX_VALUE;
    Map<Character, Integer> map = freqMap(p);

    for (int right = 0; right < n; right++) {
      // 1. keep a running count of every 
      // matching instance of a char	
      char c = s.charAt(right);
      if (map.containsKey(c)) {
        map.put(c, map.get(c) - 1);
        if (map.get(c) >= 0) {
          matched++;
        }
      }
    
      // 2. whenever all the chars are matched, 
      // shrink the window from the beginning
      while (matched == m) {
        // keep track of the smallest substr
    	// that has all the matching chars
    	min = min(min, right - left + 1);
    	// 3. stop the shrinking process as soon as
    	// a matched char removed from sliding window 
        char front = s.charAt(left++);
        // redundant matching char (aa)
        // when need only one
        if (map.containsKey(front)) {
          // second redundant: decrement the matched count  
          // when second char goes out of the window	
          if (map.get(front) == 0) {
            matched--;
          }
          // first redundant: shrink window without
          // decrementing the matched count
          map.put(front, map.get(front) + 1);
        }
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
