package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// No LeetCode number: LeetCode 46 asks the same for an array of numbers (see Permutations46).
//
// Input: a string str. Assumed distinct characters; with repeated characters it still returns
// n! entries, one per ordering of the positions, so equal strings repeat
// (PermutationsImmutableDistinct leaves the repeats out). Returns every ordering of the
// characters, in the order the search finds them.
//
// The one idea: permute(str, prefix) holds in prefix the characters already chosen and in str
// the ones still left. Choose any character of str, move it to the end of prefix, and
// permute what remains. When nothing remains, prefix is one ordering. Nothing is changed in
// place -- each call gets new strings -- so there is nothing to undo.
//
// Time: O(n * n!) -- the calls with k characters left number n! / k!, each building k strings
// of up to n characters; summed over k that is n * n! * (1 + 1/1! + 1/2! + ...), under 3 * n * n!.
// Space: O(n^2) beyond the answer -- recursion depth n, each level holding strings of up to n characters.
//
// Siblings: PermutationsBacktracking keeps flags instead of cutting characters out of the
// string; PermutationsMutableBacktrack swaps inside one array.

public final class PermutationsImmutable {

  public static List<String> permute(String str) {
    List<String> list = new ArrayList<>();
    permute(str, "", list);
    return list;
  }

  private static void permute(String str, String prefix, List<String> list) {
    int n = str.length();
    if (n == 0) {
      list.add(prefix); // RECORD: nothing left to choose
      return;
    }
    for (int i = 0; i < n; ++i) {
      char c = str.charAt(i);
      String substr = str.substring(0, i) + str.substring(i + 1, n); // REST: str without character i
      permute(substr, prefix + c, list); // CHOOSE: c goes to the end of the prefix
    }
  }

  public static void main(String[] args) {
    String str = "123";
    System.out.println(permute(str)); // [123, 132, 213, 231, 312, 321]
  }
}
