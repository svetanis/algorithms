package com.svetanis.algorithms.backtracking.combinations.sum;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Combination sum, numbers reusable, candidates that may repeat (not a LeetCode problem)
//
// Input: a list of candidates, which may repeat, and a target. Assumed: every candidate is positive
// (a 0 would be chosen forever). Returns every distinct combination that sums to the target, each
// candidate usable any number of times, the numbers in each combination in non-decreasing order
// and the combinations in lexicographic order. The list is sorted in place.
//
// The one idea: 39's start-index backtracking (the next call starts at i, so a number can be
// chosen again; one call holds the combination so far and its sum), with the repeat rule of 90
// added: when a candidate's whole branch has returned, the while loop walks i past every equal
// copy, so at one level only the first of equal candidates starts a branch. On {2, 2, 3} and 7 this
// returns [2, 2, 3] once; CombinationSum, which has no such loop, returns it three times.
//
// Time: O(n^(t/m)) -- a loose upper bound, n candidates, t the target, m the smallest. One call per
// group of candidates with sum at most t; numbers only grow along a path, so no group is reached
// twice. Each level picks one of n, and a group has at most t/m numbers.
// Space: O(t/m) besides the output -- the recursion depth and the combination built.
//
// Siblings: AllUniqueCombinationsGivenSumWithoutRepetitions is the twin with each number used once.
// CombinationSum is 39 for distinct candidates.

public final class AllUniqueCombinationsGivenSumWithRepetitions {
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

    for (int i = index; i < candidates.size(); i++) {
      combination.add(candidates.get(i)); // CHOOSE
      dfs(candidates, target, i, sum + candidates.get(i), combination, combinations); // RECURSE: i, may repeat
      combination.remove(combination.size() - 1); // UNDO
      // SKIP: after the branch, skip the equal copies: they would start the same branch again
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
