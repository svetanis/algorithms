package com.svetanis.algorithms.recursive.combination;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.util.Collections.sort;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given a set of candidate numbers (C) and a target number (T), 
// find all unique combinations in C where the candidate numbers sums to T. 
// Each number in C may only be used ONCE in the combination.

public final class AllUniqueCombinationsGivenSumWithoutRepetitions {
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

    int prev = -1;
    for (int i = index; i < in.size(); i++) {
      int curr = in.get(i);
      if (prev != curr) {
        out.add(curr);
        subset(in, k, i + 1, sum + curr, out, lists);
        out.remove(out.size() - 1); // backtrack
        prev = curr;
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> list = newArrayList(2, 3, 6, 7);
    printLines(generate(list, 7));
    List<Integer> list2 = newArrayList(10, 1, 2, 7, 6, 1, 5);
    printLines(generate(list2, 8));

  }
}

