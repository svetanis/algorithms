package com.svetanis.algorithms.string.reverse;

import static com.svetanis.java.base.utils.Swap.swap;
import static java.lang.String.valueOf;

public final class ReverseStringInPlace {

  public static String reverse(String str) {
    char[] chars = str.toCharArray();
    int n = chars.length;

    for (int i = 0; i < n / 2; i++) {
      int j = n - 1 - i;
      swap(chars, i, j);
    }
    return valueOf(chars);
  }

  public static void main(String[] args) {
    String str = "introduce";
    System.out.println(reverse(str));
  }
}