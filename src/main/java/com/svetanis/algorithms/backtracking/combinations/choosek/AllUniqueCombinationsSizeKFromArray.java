package com.svetanis.algorithms.backtracking.combinations.choosek;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Distinct combinations of a given length, from a list that may repeat
//
// Given a list of integers that may repeat and a length k, return every distinct combination of
// k of them once, the numbers in each combination in non-decreasing order ([1, 4], never [4, 1]),
// and the combinations in lexicographic order.
//
// 77's start-index backtracking over the sorted list, with the repeat rule of 90: when a number's
// whole branch has returned, the while loop walks i past every equal copy, so at one level only
// the first of equal numbers starts a branch. A later copy can still be taken one level deeper,
// which is how [1, 1] is made.

public final class AllUniqueCombinationsSizeKFromArray {
  // Time Complexity: O(2^n + k * C(n, k)), the tree holds at most 2^n sets, and each of at
  // most C(n, k) answers is copied in k steps
  // Space Complexity: O(k) besides the output, the recursion depth and the combination being built

  public static List<List<Integer>> combinations(List<Integer> nums, int k) {
    List<Integer> combination = new ArrayList<>();
    List<List<Integer>> combinations = new ArrayList<>();
    sort(nums); // equal numbers side by side
    dfs(nums, k, 0, combination, combinations);
    return combinations;
  }

  private static void dfs(List<Integer> nums, int k, int index, //
      List<Integer> combination, List<List<Integer>> combinations) {
    if (combination.size() == k) { // only full-size combinations are answers
      combinations.add(new ArrayList<>(combination));
      return;
    }
    for (int i = index; i < nums.size(); i++) {
      combination.add(nums.get(i)); // choose
      dfs(nums, k, i + 1, combination, combinations);
      combination.remove(combination.size() - 1); // undo
      // after the branch, skip the equal copies: they would start the same branch again
      while (i < nums.size() - 1 && nums.get(i).equals(nums.get(i + 1))) {
        i++;
      }
    }
  }

  public static void main(String[] args) {
    // [[1, 2], [1, 3], [2, 3]]
    System.out.println(combinations(new ArrayList<>(Arrays.asList(1, 2, 3)), 2));
    // [[1, 1], [1, 2]]
    System.out.println(combinations(new ArrayList<>(Arrays.asList(1, 2, 1)), 2));
  }
}
