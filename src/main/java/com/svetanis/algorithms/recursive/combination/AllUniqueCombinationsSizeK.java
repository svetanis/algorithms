package com.svetanis.algorithms.recursive.combination;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given two integers n and k, 
// return all possible combinations of k numbers out of 1 2 3 ... n.
// Make sure the combinations are sorted.
// Within every entry, elements should be sorted. 
// [1, 4] is a valid entry while [4, 1] is not.
// Entries should be sorted within themselves.

public final class AllUniqueCombinationsSizeK {

  // Time Complexity: O(2^n)

  public static ImmutableList<ImmutableList<Integer>> generate(int n, int k) {
    List<Integer> out = newArrayList();
    List<ImmutableList<Integer>> lists = newArrayList();
    subset(n, k, 1, out, lists);
    return newList(lists);
  }

  private static void subset(int n, int k, int index, //
      List<Integer> out, List<ImmutableList<Integer>> lists) {
    if (out.size() == k) {
      lists.add(newList(out));
      return;
    }
    for (int i = index; i <= n; i++) {
      out.add(i);
      subset(n, k, i + 1, out, lists);
      out.remove(out.size() - 1); // backtrack
    }
  }

  public static void main(String[] args) {
    printLines(generate(5, 3));
  }
}

// [1, 2, 3]
// [1, 2, 4]
// [1, 2, 5]
// [1, 3, 4]
// [1, 3, 5]
// [1, 4, 5]
// [2, 3, 4]
// [2, 3, 5]
// [2, 4, 5]
// [3, 4, 5]
