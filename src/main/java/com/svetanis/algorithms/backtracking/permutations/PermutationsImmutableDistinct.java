package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// No LeetCode number: LeetCode 47 asks the distinct orderings for an array of numbers (see
// Permutations47).
//
// Input: a string str of lowercase letters 'a' to 'z' (any other character is out of range of
// the flag array). Returns every DISTINCT ordering of its characters, in the order the search
// finds them; "aab" gives three, not six.
//
// The one idea: the same as PermutationsImmutable -- permute(str, prefix) holds the chosen
// characters in prefix and the ones left in str -- but at each call only the first copy of
// each letter is chosen. visited[c - 'a'] is true once letter c
// has been the choice at this call, so a second copy of the same letter would start the same
// set of orderings again and is skipped. visited is new in every call: the same letter may be
// chosen again one level down.
//
// Time: O(n * n!) -- at worst (all letters different) the same work as PermutationsImmutable,
// plus a 26-flag array per call; repeated letters only cut calls.
// Space: O(n^2) beyond the answer -- recursion depth n, each level holding strings of up to n
// characters and its own 26 flags.
//
// Siblings: PermutationsImmutable keeps the repeats; PermutationsMutableBacktrackDistinct leaves
// them out by swapping inside one array.

public final class PermutationsImmutableDistinct {

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
    boolean[] visited = new boolean[26]; // letters already chosen at THIS call
    for (int i = 0; i < n; ++i) {
      char c = str.charAt(i);
      String substr = str.substring(0, i) + str.substring(i + 1, n); // REST: str without character i
      if (!visited[c - 'a']) { // SKIP: a copy of this letter was already chosen here
        permute(substr, prefix + c, list); // CHOOSE: c goes to the end of the prefix
      }
      visited[c - 'a'] = true;
    }
  }

  public static void main(String[] args) {
    String str = "geek";
    // [geek, geke, gkee, egek, egke, eegk, eekg, ekge, ekeg, kgee, kege, keeg]
    System.out.println(permute(str));
  }
}
