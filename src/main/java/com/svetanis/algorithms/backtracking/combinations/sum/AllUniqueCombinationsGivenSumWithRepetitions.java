package com.svetanis.algorithms.backtracking.combinations.sum;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Combination sum, numbers reusable, candidates that may repeat
//
// Given a list of candidates, which may repeat, and a target, return every distinct combination
// that sums to the target, each candidate usable any number of times, the numbers in each
// combination in non-decreasing order and the combinations in lexicographic order.
//
// 39's start-index backtracking (the next call starts at i, so a number can be chosen again),
// with the repeat rule of 90 added: when a candidate's whole branch has returned, the while loop
// walks i past every equal copy, so at one level only the first of equal candidates starts a
// branch. On {2, 2, 3} and 7 this returns [2, 2, 3] once; CombinationSum, which has no such loop,
// returns it three times. The twin with each number used once is
// AllUniqueCombinationsGivenSumWithoutRepetitions.

public final class AllUniqueCombinationsGivenSumWithRepetitions {
  // Time Complexity: one call per group of candidates with sum at most t, the target -- numbers
  // only grow along a path, so no group is reached twice. A loose upper bound is O(n^(t/m)),
  // n candidates, m the smallest: each level picks one of n, and a group has at most t/m numbers
  // Space Complexity: O(t/m) besides the output, the recursion depth and the combination built

  public static List<List<Integer>> generate(List<Integer> candidates, int target) {
    List<Integer> combination = new ArrayList<>();
    List<List<Integer>> combinations = new ArrayList<>();
    sort(candidates); // equal candidates side by side, answers in lexicographic order
    dfs(candidates, target, 0, 0, combination, combinations);
    return combinations;
  }

  private static void dfs(List<Integer> candidates, int target, int index, int sum, //
      List<Integer> combination, List<List<Integer>> combinations) {

    if (sum > target) { // an overshoot is never an answer
      return;
    }

    if (sum == target) {
      combinations.add(new ArrayList<>(combination));
      return;
    }

    for (int i = index; i < candidates.size(); i++) {
      combination.add(candidates.get(i)); // choose
      dfs(candidates, target, i, sum + candidates.get(i), combination, combinations); // i: may repeat
      combination.remove(combination.size() - 1); // undo
      // after the branch, skip the equal copies: they would start the same branch again
      while (i < candidates.size() - 1 && candidates.get(i).equals(candidates.get(i + 1))) {
        i++;
      }
    }
  }

  public static void main(String[] args) {
    // [[2, 2, 3], [7]]
    System.out.println(generate(new ArrayList<>(Arrays.asList(2, 3, 6, 7)), 7));
    // 39 combinations: the first is twenty-eight 1s, the last two [6, 11, 11], [8, 10, 10]
    System.out.println(generate(new ArrayList<>(Arrays.asList(8, 10, 6, 11, 1, 16, 8)), 28));
  }
}
