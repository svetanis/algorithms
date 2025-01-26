package com.svetanis.algorithms.backtracking.permutations;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static com.svetanis.java.base.utils.Swap.swap;
import static java.lang.String.valueOf;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class PermutationsMutableBacktrackDistinct {

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
      // check if swapping a[i] and a[j]
      // would lead to repeated permutations
      if (shouldSwap(a, i, j)) {
        swap(a, i, j);
        permute(a, i + 1, list);
        swap(a, i, j); // backtrack
      }
    }
  }

  private static boolean shouldSwap(char[] a, int i, int j) {
    for (int k = i; k < j; k++) {
      if (a[k] == a[j]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String str = "aba";
    printLines(permute(str));
  }
}
