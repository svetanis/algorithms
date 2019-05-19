package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Sets.newHashSet;
import java.util.Set;

public final class LongestSubStrAllUnique {

  public static String allUnique(String str) {
    // Time complexity: O(n)
    
    int left = 0;
    int right = 0; // current end
    Set<Character> set = newHashSet();

    while (right < str.length()) {
      char c = str.charAt(right);
      if (!set.contains(c)) {
        set.add(c);
      } else {
        while (left < right && str.charAt(left) != c) {
            set.remove(str.charAt(left));
            left++;
        }
        left++;
      }
      right++;
    }
    return str.substring(left, right);
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
