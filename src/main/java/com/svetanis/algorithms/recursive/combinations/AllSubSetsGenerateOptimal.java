package com.svetanis.algorithms.recursive.combinations;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class AllSubSetsGenerateOptimal {

  // Time Complexity: O(n * 2^n)

  public static ImmutableList<String> generate(String str) {
    int n = str.length();
    List<String> list = newArrayList();
    list.add("");
    for (int i = 0; i < n; i++) {
      int size = list.size();
      for (int j = 0; j < size; j++) {
        String joined = list.get(j) + str.charAt(i);
        list.add(joined);
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    String str = "xyz";
    printLines(generate(str));
  }
}
