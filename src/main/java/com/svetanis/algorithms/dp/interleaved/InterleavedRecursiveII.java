package com.svetanis.algorithms.dp.interleaved;

// Given two strings str1 and str2, write a function that prints all interleavings of the given two strings.

public final class InterleavedRecursiveII {

  public static boolean isInterleaved(String s1, String s2, String s) {
    return isInterleaved(s1, 0, s2, 0, s, 0);
  }

  private static boolean isInterleaved(String s1, int i, String s2, int j, String s, int k) {
    int n = s1.length();
    int m = s2.length();

    if (i == n) {
      return s2.substring(j).equals(s.substring(k));
    }

    if (j == m) {
      return s1.substring(i).equals(s.substring(k));
    }

    boolean one = s1.charAt(i) == s.charAt(k) && isInterleaved(s1, i + 1, s2, j, s, k + 1);
    boolean two = s2.charAt(j) == s.charAt(k) && isInterleaved(s1, i, s2, j + 1, s, k + 1);
    return one || two;
  }

  public static void main(String[] args) {
    System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY")); // false
    System.out.println(isInterleaved("XY", "WZ", "WZXY")); // true
    System.out.println(isInterleaved("XY", "X", "XXY")); // true
    System.out.println(isInterleaved("YX", "X", "XXY")); // false
    System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY")); // true
  }
}
