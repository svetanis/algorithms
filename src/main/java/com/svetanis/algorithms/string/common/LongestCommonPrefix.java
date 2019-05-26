package com.svetanis.algorithms.string.common;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.Preconditions.checkMin;
import static java.lang.Math.min;

import java.util.List;

public final class LongestCommonPrefix {

  public static String lcp(List<String> list) {
    int n = list.size();
    checkMin(n, 1, "size");
    String prefix = list.get(0);
    for (int i = 1; i < n; ++i) {
      String str = list.get(i);
      prefix = lcp(str, prefix);
    }
    return prefix;
  }

  private static String lcp(String str, String prefix) {
    int n = str.length();
    int m = prefix.length();

    int j = 0;
    for (j = 0; j < min(n, m); j++) {
      if (prefix.charAt(j) != str.charAt(j)) {
        break;
      }
    }
    return prefix.substring(0, j);
  }

  public static void main(String[] args) {
    List<String> array = newArrayList("calendar", "calendula", "calculator", "caligula");
    System.out.println(lcp(array));
  }
}