package com.svetanis.algorithms.string.reorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 249. Group Shifted Strings

public final class GroupShiftedStrings249 {
  // Time Complexity: O(n * k)
  // Space Complexity: O(n)

  public static List<List<String>> group(String[] a) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : a) {
      int shift = s.charAt(0) - 'a';
      char[] chars = s.toCharArray();
      for (int i = 0; i < chars.length; i++) {
        char shifted = (char) (chars[i] - shift);
        if (shifted < 'a') {
          shifted += 26;
        }
        chars[i] = shifted;
      }
      String key = new String(chars);
      map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
  }

  public static void main(String[] args) {
    String[] a = { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z" };
    System.out.println(group(a)); // [[a, z], [abc, bcd, xyz], [az, ba], [acef]]
  }
}