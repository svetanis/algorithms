package com.svetanis.algorithms.string.reverse;

import static com.svetanis.java.base.utils.Swap.swap;
import static java.lang.String.valueOf;

public final class ReverseStringSwap {

  public static String reverse(String str) {
    char[] chars = str.toCharArray();
    int n = chars.length;
    reverse(chars, 0, n - 1);
    return valueOf(chars);
  }

  public static void reverse(char[] chars, int low, int high) {
    while (low < high) {
      swap(chars, low, high);
      ++low;
      --high;
    }
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str));
  }
}
