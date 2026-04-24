package com.svetanis.algorithms.string;

import java.util.HashSet;
import java.util.Set;

// 1763. Longest Nice Substring

public final class LongestNiceSubStr {
  // Time Complexity: O(n^2)
  // Space Complexity: O(1)

  public static String dfs(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>();
    for (char c : s.toCharArray()) {
      set.add(c);
    }

    for (int i = 0; i < n; i++) {
      char curr = s.charAt(i);
      char low = Character.toLowerCase(curr);
      char upp = Character.toUpperCase(curr);
      if (set.contains(low) && set.contains(upp)) {
        continue;
      }
      String left = dfs(s.substring(0, i));
      String right = dfs(s.substring(i + 1));
      return left.length() >= right.length() ? left : right;
    }
    return s;
  }

  public static String longestNiceSubstr(String s) {
    int n = s.length();
    int maxLen = 0;
    int start = 0;
    for (int i = 0; i < n; i++) {
      Set<Character> set = new HashSet<>();
      for (int j = i; j < n; j++) {
        set.add(s.charAt(j));
        boolean isNice = true;
        for (char curr : set) {
          char low = Character.toLowerCase(curr);
          char upp = Character.toUpperCase(curr);
          if (!set.contains(low) || !set.contains(upp)) {
            isNice = false;
            break;
          }
        }
        if (isNice && maxLen < j - i + 1) {
          maxLen = j - i + 1;
          start = i;
        }
      }

    }
    return start == -1 ? "" : s.substring(start, start + maxLen);
  }

  public static void main(String[] args) {
    System.out.println(dfs("YazaAay")); // aAa
    System.out.println(dfs("Bb")); // Bb
    System.out.println(dfs("c")); // ""
  }
}
