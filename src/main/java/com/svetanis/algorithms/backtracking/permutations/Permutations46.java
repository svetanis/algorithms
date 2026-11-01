package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// 46. Permutations
//
// Input: an array a of distinct integers. On LeetCode: 1 <= a.length <= 6,
// -10 <= a[i] <= 10, all values distinct. Returns every ordering of the numbers, in any order.
//
// The one idea: fill the positions of the answer left to right. permutation holds the numbers
// placed so far, and visited[i] says whether a[i] is among them. At each position try every
// number not yet used: place it, fill the rest, then take it back so the next number can try
// the same position. When all n positions are filled, copy the list into the answer. The
// first position has n choices, the next n - 1, and so on: n! orderings.
//
// Time: O(n * n!) -- n! orderings, each copied out in O(n); the tree has fewer than 3 * n!
// nodes, each scanning the n flags.
// Space: O(n) beyond the answer -- recursion depth n and the flags; the answer holds n! lists of n.
//
// Siblings: Permutations47 is the same walk for an array with repeated values, sorted so that
// equal values are skipped; PermutationsRecursive and PermutationsIterative build each
// ordering by inserting the next number at every position instead.

public final class Permutations46 {

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
      permutations.add(new ArrayList<>(permutation)); // RECORD: a copy -- the list keeps changing
      return;
    }
    for (int i = 0; i < a.length; i++) {
      if (!visited[i]) { // SKIP: a[i] is already placed
        permutation.add(a[i]); // PLACE: a[i] takes the next position
        visited[i] = true;
        dfs(a, visited, permutation, permutations);
        permutation.remove(permutation.size() - 1); // UNDO: take it back
        visited[i] = false;
      }
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 3 };
    // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    System.out.println(permutations(a));

    int[] a1 = { 0, 1 };
    System.out.println(permutations(a1)); // [[0, 1], [1, 0]]

    int[] a2 = { 1 };
    System.out.println(permutations(a2)); // [[1]]
  }
}
