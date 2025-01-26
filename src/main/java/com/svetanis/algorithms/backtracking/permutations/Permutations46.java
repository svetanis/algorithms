package com.svetanis.algorithms.backtracking.permutations;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 46. Permutations

public final class Permutations46 {
  // Time Complexity: O(n * n!)
  // Space Complexity: O(n * n!)

  public static List<List<Integer>> permutations(int[] a) {
    List<List<Integer>> permutations = new ArrayList<>();
    List<Integer> permutation = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    dfs(a, visited, permutation, permutations);
    return permutations;
  }

  private static void dfs(int[] a, Set<Integer> visited, 
      List<Integer> permutation, List<List<Integer>> permutations) {
    if (permutation.size() == a.length) {
      permutations.add(new ArrayList<>(permutation));
      return;
    }
    for (int i = 0; i < a.length; i++) {
      if (!visited.contains(a[i])) {
        permutation.add(a[i]);
        visited.add(a[i]);
        dfs(a, visited, permutation, permutations);
        permutation.remove(permutation.size() - 1);
        visited.remove(a[i]);
      }
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3 };
    // [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]
    print(permutations(a));

    int[] a1 = { 0, 1 };
    print(permutations(a1)); // [0,1] [1,0]

    int[] a2 = { 1 };
    print(permutations(a2)); // [1]
  }
}
