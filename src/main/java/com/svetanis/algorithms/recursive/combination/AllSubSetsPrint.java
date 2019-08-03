package com.svetanis.algorithms.recursive.combination;

import static com.svetanis.java.base.utils.Print.print;

public final class AllSubSetsPrint {
  
  // Time Complexity: O(n * 2^n)

  public static void subset(String str) {
    int n = str.length();
    char[] out = new char[n];
    subset(str.toCharArray(), 0, out, 0);
  }

  private static void subset(char[] in, int i, char[] out, int k) {
    if (i == in.length) {
      print(out, 0, k - 1);
      return;
    }
    // exclude
    subset(in, i + 1, out, k);
    // include
    out[k] = in[i];
    subset(in, i + 1, out, k + 1);
  }

  public static void main(String[] args) {
    String str = "abc";
    subset(str);
  }
}
