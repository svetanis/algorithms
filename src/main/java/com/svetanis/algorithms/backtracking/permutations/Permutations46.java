package com.svetanis.algorithms.backtracking.permutations;

import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayList;
import java.util.List;

// 46. Permutations

public final class Permutations46 {
  // Time Complexity: O(n * n!)
  // Space Complexity: O(n * n!)

  public static List<List<Integer>> permutations(int[] a) {
    List<List<Integer>> permutations = new ArrayList<>();
    List<Integer> permutation = new ArrayList<>();
    // indexed by POSITION, not by value -- two equal values are still two
    // distinct elements, and only an index can tell them apart
    boolean[] visited = new boolean[a.length];
    dfs(a, visited, permutation, permutations);
    return permutations;
  }

  private static void dfs(int[] a, boolean[] visited, 
      List<Integer> permutation, List<List<Integer>> permutations) {
    if (permutation.size() == a.length) {
      permutations.add(new ArrayList<>(permutation));
      return;
    }
    for (int i = 0; i < a.length; i++) {
      if (!visited[i]) {
        permutation.add(a[i]);
        visited[i] = true;
        dfs(a, visited, permutation, permutations);
        permutation.remove(permutation.size() - 1);
        visited[i] = false;
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
