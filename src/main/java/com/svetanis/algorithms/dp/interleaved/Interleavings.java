package com.svetanis.algorithms.dp.interleaved;

// Given two strings str1 and str2, write a function that prints all interleavings of the given two strings.

public final class Interleavings {
  
  public static void interleavings(String str1, String str2) {
    
    if (str1 == null && str2 == null) {
      return;
    }
    interleavings(str1, str2, "");
  }

  private static void interleavings(String s1, String s2, String s) {
    int n = s1.length();
    int m = s2.length();

    if (n == 0 && m == 0) {
      return;
    }

    if (s1 == null || n == 0) {
      System.out.println(s + s2);
      return;
    }

    if ((s2 == null || m == 0)) {
      System.out.println(s + s1);
      return;
    }

    interleavings(s1.substring(1), s2, s + s1.charAt(0));
    interleavings(s1, s2.substring(1), s + s2.charAt(0));
  }

  public static void main(String[] args) {
    String str1 = "AB";
    String str2 = "CD";
    interleavings(str1, str2);
  }
}
