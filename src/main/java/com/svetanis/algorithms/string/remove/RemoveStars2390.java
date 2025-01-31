package com.svetanis.algorithms.string.remove;

// 2390. Removing Stars From a String

public final class RemoveStars2390 {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static String remove(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c == '*') {
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length() - 1);
        }
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(remove("leet**cod*e")); // lecoe
    System.out.println(remove("erase*****")); // ""
  }
}
