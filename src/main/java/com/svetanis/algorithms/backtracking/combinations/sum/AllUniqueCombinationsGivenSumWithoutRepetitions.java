package com.svetanis.algorithms.backtracking.combinations.sum;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Combination sum, each number once, candidates that may repeat
//
// Given a list of candidates, which may repeat, and a target, return every distinct combination
// that sums to the target, each candidate used at most once, the numbers in each combination in
// non-decreasing order and the combinations in lexicographic order. The same problem as
// CombinationSumII (40), with the same answers in the same order.
//
// Start-index backtracking over the sorted list; the next call starts at i + 1, so each position
// is used at most once. The repeat rule is spelled a third way here: prev remembers the last value
// this level started a branch with, and an equal value is passed over. CombinationSumII spells it
// as "i > index and equal to the one before", and its twin
// AllUniqueCombinationsGivenSumWithRepetitions as a while loop after the branch.

public final class AllUniqueCombinationsGivenSumWithoutRepetitions {
  // Time Complexity: O(n * 2^n), every call is a different set of positions, at most 2^n, and
  // an answer is copied in up to n steps
  // Space Complexity: O(n) besides the output, the recursion depth and the combination built

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

    int prev = -1; // the value this level last started a branch with; candidates are positive
    for (int i = index; i < candidates.size(); i++) {
      int curr = candidates.get(i);
      if (prev != curr) { // an equal value at this level would start the same branch again
        combination.add(curr); // choose
        dfs(candidates, target, i + 1, sum + curr, combination, combinations); // i + 1: once
        combination.remove(combination.size() - 1); // undo
        prev = curr;
      }
    }
  }

  public static void main(String[] args) {
    // [[7]]
    System.out.println(generate(new ArrayList<>(Arrays.asList(2, 3, 6, 7)), 7));
    // [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
    System.out.println(generate(new ArrayList<>(Arrays.asList(10, 1, 2, 7, 6, 1, 5)), 8));
  }
}
