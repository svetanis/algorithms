package com.svetanis.algorithms.backtracking.combinations.sum;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 40. Combination Sum II (on a list)
//
// Input: a list of candidates, which may repeat, and a target. Returns every distinct combination
// that sums to the target, each candidate used at most once, the numbers in each combination in
// non-decreasing order and the combinations in lexicographic order -- the same problem as
// CombinationSumII, with the same answers in the same order. Assumed: every candidate is positive
// (prev starts at -1). The list is sorted in place. On LeetCode: 1 <= candidates.length <= 100,
// 1 <= candidates[i] <= 50, 1 <= target <= 30.
//
// The one idea: start-index backtracking over the sorted list; one call holds the combination so
// far and its sum, and the next call starts at i + 1, so each position is used at most once. The
// repeat rule is spelled a third way here: prev remembers the last value this level started a
// branch with, and an equal value is passed over.
//
// Time: O(n * 2^n) -- every call is a different set of positions, at most 2^n, and an answer is
// copied in up to n steps.
// Space: O(n) besides the output -- the recursion depth and the combination built.
//
// Siblings: CombinationSumII spells the repeat rule as "i > index and equal to the one before", and
// its twin AllUniqueCombinationsGivenSumWithRepetitions as a while loop after the branch.

public final class AllUniqueCombinationsGivenSumWithoutRepetitions {
  public static List<List<Integer>> generate(List<Integer> candidates, int target) {
    List<Integer> combination = new ArrayList<>();
    List<List<Integer>> combinations = new ArrayList<>();
    sort(candidates); // SORT: equal candidates side by side, answers in lexicographic order
    dfs(candidates, target, 0, 0, combination, combinations);
    return combinations;
  }

  private static void dfs(List<Integer> candidates, int target, int index, int sum, //
      List<Integer> combination, List<List<Integer>> combinations) {

    if (sum > target) { // PRUNE: an overshoot is never an answer
      return;
    }

    if (sum == target) { // RECORD: exactly the target
      combinations.add(new ArrayList<>(combination));
      return;
    }

    int prev = -1; // the value this level last started a branch with; candidates are positive
    for (int i = index; i < candidates.size(); i++) {
      int curr = candidates.get(i);
      if (prev != curr) { // DEDUP: an equal value at this level would start the same branch again
        combination.add(curr); // CHOOSE
        dfs(candidates, target, i + 1, sum + curr, combination, combinations); // RECURSE: i + 1
        combination.remove(combination.size() - 1); // UNDO
        prev = curr; // REMEMBER the value just tried
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
