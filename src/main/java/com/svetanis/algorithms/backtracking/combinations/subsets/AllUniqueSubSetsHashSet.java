package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// All distinct non-empty subsequences of a string, by deleting characters
//
// Given a string that may repeat characters, return every distinct non-empty subsequence once
// (the characters kept in their original order), in no particular order. The empty subsequence
// is not returned: the recursion stops at the empty string before adding it.
//
// Recurse by deleting one character at a time, starting from the whole string. Different
// deletions reach the same string -- deleting the x or the y of "xy" leaves one letter, but
// deleting either x of "xx" leaves the same "x" -- so the Set records every string reached and
// stops it being expanded a second time. The repeats are made and then absorbed, not prevented;
// SubsetsBacktracking never makes them.

public final class AllUniqueSubSetsHashSet {

  // Time Complexity: O(n^2 * 2^n), up to 2^n distinct strings, each expanded once into n
  // shorter strings that each cost up to n to build and hash
  // Space Complexity: O(n * 2^n), the Set of up to 2^n strings of up to n characters

  public static List<String> generate(String str) {
    Set<String> set = new HashSet<>();
    subset(str, set);
    return new ArrayList<>(set);
  }

  private static void subset(String str, Set<String> set) {
    if (str.length() == 0) { // the empty string is never added
      return;
    }

    if (!set.contains(str)) { // a string already reached was already expanded
      set.add(str);
      for (int i = 0; i < str.length(); i++) {
        String substr = str.substring(0, i) + str.substring(i + 1); // str without position i
        subset(substr, set);
      }
    }
  }

  public static void main(String[] args) {
    // [xx, yz, xy, xz, xyz, x, xxy, xxyz, y, z, xxz], in hash order
    System.out.println(generate("xxyz"));
  }
}
