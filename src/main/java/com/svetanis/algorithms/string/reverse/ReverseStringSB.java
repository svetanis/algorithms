package com.svetanis.algorithms.string.reverse;

public final class ReverseStringSB {

  public static String reverse(String str) {
    return new StringBuilder(str).reverse().toString();
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str));
  }
}
