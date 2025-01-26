package com.svetanis.algorithms.backtracking.combinations;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.util.Collections.sort;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a set of candidate numbers (C) and a target number (T), 
// find all unique combinations in C where the candidate numbers sums to T. 
// The same repeated number may be chosen from C unlimited number of times.

public final class AllUniqueCombinationsGivenSumWithRepetitions {
  // Time Complexity: O(2^n)

  public static ImmutableList<ImmutableList<Integer>> generate(List<Integer> in, int k) {
    List<Integer> out = newArrayList();
    List<ImmutableList<Integer>> lists = newArrayList();
    sort(in);
    subset(in, k, 0, 0, out, lists);
    return newList(lists);
  }

  private static void subset(List<Integer> in, int k, int index, int sum, //
      List<Integer> out, List<ImmutableList<Integer>> lists) {

    if (sum > k) {
      return;
    }

    if (sum == k) {
      lists.add(newList(out));
      return;
    }

    for (int i = index; i < in.size(); i++) {
      out.add(in.get(i));
      subset(in, k, i, sum + in.get(i), out, lists);
      out.remove(out.size() - 1); // backtrack
      while (i < in.size() - 1 && in.get(i).equals(in.get(i + 1))) {
        i++;
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> list = newArrayList(2, 3, 6, 7);
    printLines(generate(list, 7));
    List<Integer> list2 = newArrayList(8, 10, 6, 11, 1, 16, 8);
    printLines(generate(list2, 28));

  }
}

// [2, 2, 3]
// [7]
