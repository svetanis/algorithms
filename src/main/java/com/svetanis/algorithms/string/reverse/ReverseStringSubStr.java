package com.svetanis.algorithms.string.reverse;

public final class ReverseStringSubStr {

  public static String reverse(String str) {
    int n = str.length();
    String[] letters = new String[n];
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < n; i++) {
      letters[i] = str.substring(n - i - 1, n - i);
      sb.append(letters[i]);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str));
  }
}
