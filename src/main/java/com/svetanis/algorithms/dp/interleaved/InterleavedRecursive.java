package com.svetanis.algorithms.dp.interleaved;

// Given two strings str1 and str2, write a function that prints all interleavings of the given two strings.

public final class InterleavedRecursive {

  public static boolean isInterleaved(String s1, String s2, String s) {
    return isInterleaved(s1, 0, s2, 0, "", s);
  }

  private static boolean isInterleaved(String s1, int i, String s2, int j, String res, String s) {
    int n = s1.length();
    int m = s2.length();

    if (res.equals(s) && i == n && j == m) {
      return true;
    }

    boolean interleaved = false;

    if (i < n) {
      interleaved = interleaved || isInterleaved(s1, i + 1, s2, j, res + s1.charAt(i), s);
    }

    if (j < m) {
      interleaved = interleaved || isInterleaved(s1, i, s2, j + 1, res + s2.charAt(j), s);
    }
    return interleaved;
  }

  public static void main(String[] args) {
    System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY")); // false
    System.out.println(isInterleaved("XY", "WZ", "WZXY")); // true
    System.out.println(isInterleaved("XY", "X", "XXY")); // true
    System.out.println(isInterleaved("YX", "X", "XXY")); // false
    System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY")); // true
  }
}
