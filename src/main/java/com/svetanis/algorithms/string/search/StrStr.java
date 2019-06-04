package com.svetanis.algorithms.string.search;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.startsWith;

public final class StrStr {

  public static int strStr(String haystack, String needle) {

    if (isBlank(haystack) || isBlank(needle)) {
      return -1;
    }

    if (haystack.length() < needle.length()) {
      return -1;
    }

    int diff = haystack.length() - needle.length() + 1;

    for (int i = 0; i < diff; ++i) {
      if (startsWith(haystack.substring(i), needle)) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    String haystack = "This is a simple string";
    String needle = "simple";
    System.out.println(strStr(haystack, needle));
  }
}
