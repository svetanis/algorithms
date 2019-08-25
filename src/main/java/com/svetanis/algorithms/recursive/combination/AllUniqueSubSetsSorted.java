package com.svetanis.algorithms.recursive.combination;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.util.Collections.sort;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a set of distinct integers, S, return all possible subsets.

// 1. Elements in a subset must be in non-descending order.
// 2. The solution set must not contain duplicate subsets.
// 3. Also, the subsets should be sorted in ascending ( lexicographic ) order.
// 4. The list is not necessarily sorted.

public final class AllUniqueSubSetsSorted {

  // Time Complexity: O(2^n)

  public static List<ImmutableList<Integer>> generate(List<Integer> in) {
    List<Integer> out = newArrayList();
    List<ImmutableList<Integer>> lists = newArrayList();
    sort(in);
    subset(in, 0, out, lists);
    return newList(lists);
  }

  private static void subset(List<Integer> in, int index, //
      List<Integer> out, List<ImmutableList<Integer>> lists) {
    lists.add(newList(out));
    for (int i = index; i < in.size(); i++) {
      out.add(in.get(i));
      subset(in, i + 1, out, lists);
      out.remove(out.size() - 1); // backtrack
    }
  }

  public static void main(String[] args) {
    List<Integer> list = newArrayList(1, 2, 3);
    printLines(generate(list));
  }
}

// [],
// [1],
// [1, 2],
// [1, 2, 3],
// [1, 3],
// [2],
// [2, 3],
// [3],
