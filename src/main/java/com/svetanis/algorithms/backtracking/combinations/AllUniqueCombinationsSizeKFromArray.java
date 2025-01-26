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

  public static ImmutableList<ImmutableList<Integer>> generate(List<Integer> in, int k) {
    List<Integer> out = newArrayList();
    List<ImmutableList<Integer>> lists = newArrayList();
    sort(in);
    subset(in, k, 0, out, lists);
    return newList(lists);
  }

  private static void subset(List<Integer> in, int k, int index, //
      List<Integer> out, List<ImmutableList<Integer>> lists) {
    if (out.size() == k) {
      lists.add(newList(out));
      return;
    }
    for (int i = index; i < in.size(); i++) {
      out.add(in.get(i));
      subset(in, k, i + 1, out, lists);
      out.remove(out.size() - 1); // backtrack
      while (i < in.size() - 1 && in.get(i).equals(in.get(i + 1))) {
        i++;
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> list = newArrayList(1, 2, 3);
    printLines(generate(list, 2));

    List<Integer> list1 = newArrayList(1, 2, 1);
    printLines(generate(list1, 2));
  }
}

// [1, 2]
// [1, 3]
// [2, 3]

// [1, 1]
// [1, 2]
