package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// No LeetCode number: LeetCode 47 asks the distinct orderings for an array of numbers (see
// Permutations47).
//
// Input: a string str, any characters. Returns every DISTINCT ordering of its characters, in
// the order the search finds them; "aba" gives three, not six. A string of length 0 gives an
// empty list.
//
// The one idea: the same as PermutationsMutableBacktrack -- permute(a, i) settles positions
// 0 .. i - 1 and puts each remaining character in position i by a swap -- but a character is
// swapped into position i only if no equal character sits between position i and its own
// place j (shouldSwap). An equal character in between would already have been put in position
// i earlier in the loop, and from there the same orderings follow, so the second try would only
// repeat them.
//
// Time: O(n * n!) -- at most n! orderings, each turned into a string in O(n); the calls with
// k characters left number at most n! / k!, and each runs k tries of shouldSwap that scan under
// k characters, which sums to O(n!) over all k.
// Space: O(n) beyond the answer -- the array and recursion depth n; the answer holds up to n! strings of length n.
//
// Siblings: PermutationsMutableBacktrack keeps the repeats; PermutationsImmutableDistinct leaves
// them out by choosing each letter once per call.

public final class PermutationsMutableBacktrackDistinct {

  public static List<String> permute(String str) {
    List<String> list = new ArrayList<>();
    permute(str.toCharArray(), 0, list);
    return list;
  }

  private static void permute(char[] a, int i, List<String> list) {
    int n = a.length;
    if (i == n - 1) {
      list.add(String.valueOf(a)); // RECORD: the last position has only one character left
      return;
    }
    for (int j = i; j < n; ++j) {
      // check if swapping a[i] and a[j]
      // would lead to repeated permutations
      if (shouldSwap(a, i, j)) { // SKIP: an equal character was already tried at position i
        swap(a, i, j); // CHOOSE: a[j] goes to position i
        permute(a, i + 1, list);
        swap(a, i, j); // backtrack: put it back
      }
    }
  }

  // false when a[j] equals a character in positions i .. j - 1
  private static boolean shouldSwap(char[] a, int i, int j) {
    for (int k = i; k < j; k++) {
      if (a[k] == a[j]) {
        return false;
      }
    }
    return true;
  }

  private static void swap(char[] a, int i, int j) {
    char temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    String str = "aba";
    System.out.println(permute(str)); // [aba, aab, baa]
  }
}
