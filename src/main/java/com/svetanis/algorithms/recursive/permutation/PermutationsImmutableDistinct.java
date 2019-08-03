package com.svetanis.algorithms.recursive.permutation;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ImmutableList;

//print N! permutation of the characters of the string in order

public final class PermutationsImmutableDistinct {

  public static ImmutableList<String> permute(String str) {
    List<String> list = newArrayList();
    permute(str, "", list);
    return newList(list);
  }

  private static void permute(String str, String prefix, List<String> list) {
    int n = str.length();
    if (n == 0) {
      list.add(prefix);
      return;
    }
    boolean[] visited = new boolean[26];
    for (int i = 0; i < n; ++i) {
      char c = str.charAt(i);
      String substr = str.substring(0, i) + str.substring(i + 1, n);
      if (!visited[c - 'a']) {
        permute(substr, prefix + c, list);
      }
      visited[c - 'a'] = true;
    }
  }

  public static void main(String[] args) {
    String str = "geek";
    printLines(permute(str));
  }
}
