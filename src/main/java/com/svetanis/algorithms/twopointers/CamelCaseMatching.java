package com.svetanis.algorithms.twopointers;

import static java.lang.Character.isLowerCase;

import java.util.ArrayList;
import java.util.List;

// 1023. Camelcase Matching

public final class CamelCaseMatching {
  // Time Complexity: O(k * (n + m))
  // Space Complexity: O(k)

  public static List<Boolean> camelMatch(String[] queries, String pattern) {
    List<Boolean> list = new ArrayList<>();
    for (String query : queries) {
      list.add(match(query, pattern));
    }
    return list;
  }

  private static boolean match(String s, String p) {
    int n = s.length();
    int m = p.length();
    int i = 0;
    int j = 0;
    while (i < n && j < m) {
      if (s.charAt(i) == p.charAt(j)) {
        j += 1;
      } else {
        char c = s.charAt(i);
        if (c >= 'A' && c <= 'Z') {
          return false;
        }
      }
      i += 1;
    }
    if (j != m) {
      return false;
    }
    while (i < n) {
      char c = s.charAt(i);
      if (c >= 'A' && c <= 'Z') {
        return false;
      }
      i += 1;
    }
    return true;
  }

  private static boolean match2(String s, String p) {
    int n = s.length();
    int m = p.length();
    int i = 0;
    int j = 0;
    while (j < m) {
      while (i < n && isLowerCase(s.charAt(i))//
          && s.charAt(i) != p.charAt(j)) {
        i++;
      }
      if (i == n || s.charAt(i) != p.charAt(j)) {
        return false;
      }
      i += 1;
      j += 1;
    }
    while (i < n && isLowerCase(s.charAt(i))) {
      i++;
    }
    return i == n;
  }

  public static void main(String[] args) {
    String[] p1 = { "FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack" };
    System.out.println(camelMatch(p1, "FB")); // true,false,true,true,false
    String[] p2 = { "FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack" };
    System.out.println(camelMatch(p2, "FoBa")); // true,false,true,false,false
    String[] p3 = { "FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack" };
    System.out.println(camelMatch(p3, "FoBaT")); // false,true,false,false,false
  }
}
