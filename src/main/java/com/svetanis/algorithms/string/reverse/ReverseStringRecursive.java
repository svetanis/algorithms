package com.svetanis.algorithms.string.reverse;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trim;

public final class ReverseStringRecursive {

  public static String reverse(String str) {
    if (isBlank(str) || (trim(str).length() <= 1)) {
      return str;
    }
    return reverse(str.substring(1)) + str.charAt(0);
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str));
  }
}
