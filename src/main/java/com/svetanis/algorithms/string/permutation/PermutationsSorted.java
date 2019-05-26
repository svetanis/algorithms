package com.svetanis.algorithms.string.permutation;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.string.permutation.NextHigherPermutation.ceiling;
import static com.svetanis.algorithms.string.permutation.NextHigherPermutation.smaller;
import static com.svetanis.algorithms.string.reverse.ReverseStringSwap.reverse;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static com.svetanis.java.base.utils.Swap.swap;
import static java.lang.String.valueOf;
import static java.util.Arrays.sort;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class PermutationsSorted {

  public static ImmutableList<String> sortedPermutations(String str) {
    int n = str.length();
    char[] chars = str.toCharArray();
    boolean isFinished = false;
    List<String> list = newArrayList();
    sort(chars);
    while (!isFinished) {
      list.add(valueOf(chars));
      int first = smaller(chars);
      if (first == -1) {
        isFinished = true;
      } else {
        int second = ceiling(chars, chars[first], first + 1, n - 1);
        swap(chars, first, second);
        reverse(chars, first + 1, n - 1);
      }
    }
    return newList(list);
  }

  public static void main(String[] args) {
    String str = "ABCD";
    printLines(sortedPermutations(str));
  }
}
