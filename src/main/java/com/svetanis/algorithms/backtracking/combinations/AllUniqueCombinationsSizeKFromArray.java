package com.svetanis.algorithms.backtracking.combinations;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;
import static java.util.Collections.sort;

import java.util.List;

import com.google.common.collect.ImmutableList;

// Given array of integers, find all distinct combinations of given len 
// Make sure the combinations are sorted.
// Within every entry, elements should be sorted. 
// [1, 4] is a valid entry while [4, 1] is not.
// Entries should be sorted within themselves.

public final class AllUniqueCombinationsSizeKFromArray {
  // Time Complexity: O(2^n)

  public static ImmutableList<ImmutableList<Integer>> combinations(List<Integer> in, int k) {
    List<Integer> combination = newArrayList();
    List<ImmutableList<Integer>> combinations = newArrayList();
    sort(in);
    dfs(in, k, 0, combination, combinations);
    return newList(combinations);
  }

  private static void dfs(List<Integer> list, int k, int index, //
      List<Integer> combination, List<ImmutableList<Integer>> combinations) {
    if (combination.size() == k) {
      combinations.add(newList(combination));
      return;
    }
    for (int i = index; i < list.size(); i++) {
      combination.add(list.get(i));
      dfs(list, k, i + 1, combination, combinations);
      combination.remove(combination.size() - 1); // backtrack
      while (i < list.size() - 1 && list.get(i).equals(list.get(i + 1))) {
        i++;
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> list = newArrayList(1, 2, 3);
    printLines(combinations(list, 2));

    List<Integer> list1 = newArrayList(1, 2, 1);
    printLines(combinations(list1, 2));
  }
}

// [1, 2]
// [1, 3]
// [2, 3]

// [1, 1]
// [1, 2]
