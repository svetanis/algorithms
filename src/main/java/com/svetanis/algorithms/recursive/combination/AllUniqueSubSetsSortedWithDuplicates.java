package com.svetanis.algorithms.recursive.combination;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.util.Collections.sort;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a collection of integers that might contain duplicates, 
// S, return all possible subsets.

// 1. Elements in a subset must be in non-descending order.
// 2. The solution set must not contain duplicate subsets.
// 3. The subsets must be sorted lexicographically.

public final class AllUniqueSubSetsSortedWithDuplicates {

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
      // skip adjacent duplicates
      while (i < in.size() - 1 && in.get(i).equals(in.get(i + 1))) {
        i++;
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> list = newArrayList(1, 2, 2);
    printLines(generate(list));
  }
}

// []
// [1]
// [1, 2]
// [1, 2, 2]
// [2]
// [2, 2]
