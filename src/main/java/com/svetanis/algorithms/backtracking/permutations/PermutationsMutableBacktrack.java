package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// No LeetCode number: LeetCode 46 asks the same for an array of numbers (see Permutations46).
//
// Input: a string str. Assumed distinct characters; with repeated characters it still returns
// n! entries, one per ordering of the positions, so equal strings repeat
// (PermutationsMutableBacktrackDistinct leaves the repeats out). Returns every ordering of the
// characters, in the order the search finds them. A string of length 0 gives an empty list.
//
// The one idea: the array a is edited in place. permute(a, i) means "positions 0 .. i - 1 are
// settled; every ordering of the characters in positions i .. n - 1 is still to be made".
// Put each of those characters in position i in turn, by swapping it with the character
// there, settle position i, and recurse on i + 1. Swap back afterwards, so the next choice
// starts from the same array. When only the last position is left there is nothing to choose:
// the array is one ordering.
//
// Time: O(n * n!) -- n! orderings, each turned into a string in O(n); the tree has fewer than
// 3 * n! nodes, each doing swaps in O(1).
// Space: O(n) beyond the answer -- the array and recursion depth n; the answer holds n! strings of length n.
//
// Siblings: PermutationsBacktracking keeps a flag per character instead of swapping;
// PermutationsImmutable cuts the chosen character out of a string.

public final class PermutationsMutableBacktrack {

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
      swap(a, i, j); // CHOOSE: a[j] goes to position i
      permute(a, i + 1, list);
      swap(a, i, j); // backtrack: put it back
    }
  }

  private static void swap(char[] a, int i, int j) {
    char temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    String str = "123";
    System.out.println(permute(str)); // [123, 132, 213, 231, 321, 312]
  }
}
