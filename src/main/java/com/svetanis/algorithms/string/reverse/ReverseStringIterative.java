package com.svetanis.algorithms.string.reverse;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.base.Joiner;

public final class ReverseStringIterative {

  public static String reverse(String str) {
    char[] chars = str.toCharArray();
    int n = chars.length;
    List<Character> list = newArrayList();
    for (int i = n - 1; i >= 0; i--) {
      list.add(chars[i]);
    }
    return Joiner.on("").join(list);
  }

  public static void main(String[] args) {
    String str = "Sony is going to introduce Internet TV show soon";
    System.out.println(reverse(str));
  }
}
