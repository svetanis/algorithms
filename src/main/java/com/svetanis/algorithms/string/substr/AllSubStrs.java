package com.svetanis.algorithms.string.substr;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class AllSubStrs {

  public static ImmutableList<String> substrs(String str) {
    int n = str.length();
    List<String> list = newArrayList();
    
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j <= n; j++) {
        list.add(str.substring(i, j));
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    String str = "abcd";
    print(substrs(str));
  }
}
