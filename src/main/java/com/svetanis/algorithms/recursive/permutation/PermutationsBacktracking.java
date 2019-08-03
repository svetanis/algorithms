package com.svetanis.algorithms.recursive.permutation;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static com.svetanis.java.base.utils.Swap.swap;
import static java.lang.String.valueOf;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class PermutationsBacktracking {

  public static ImmutableList<String> permute(String str) {
    // Time Complexity: O(n * n!)
    
    List<String> list = newArrayList();
    permute(str.toCharArray(), 0, list);
    return newList(list);
  }

  private static void permute(char[] a, int i, List<String> list) {
    int n = a.length;
    if (i == n - 1) {
      list.add(valueOf(a));
      return;
    }
    for (int j = i; j < n; ++j) {
      swap(a, i, j);
      permute(a, i + 1, list);
      swap(a, i, j); // backtrack
    }
  }

  public static void main(String[] args) {
    String str = "abc";
    printLines(permute(str));
  }
}
