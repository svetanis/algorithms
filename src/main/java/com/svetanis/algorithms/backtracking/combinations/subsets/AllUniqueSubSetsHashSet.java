package com.svetanis.algorithms.backtracking.combinations.subsets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// All distinct non-empty subsequences of a string, by deleting characters (not a LeetCode problem)
//
// Input: a string that may repeat characters (assumed length at most about 15). Returns every
// distinct non-empty subsequence once (the characters kept in their original order), in no
// particular order. The empty subsequence is not returned: the recursion stops at the empty string
// before adding it.
//
// The one idea: recurse by deleting one character at a time, starting from the whole string. One
// call holds one string; the Set holds every string reached so far. Different deletions reach the
// same string -- deleting the x or the y of "xy" leaves one letter, but deleting either x of "xx"
// leaves the same "x" -- so the Set stops a string being expanded a second time. The repeats are
// made and then absorbed, not prevented; SubsetsBacktracking never makes them.
//
// Time: O(n^2 * 2^n) -- up to 2^n distinct strings, each expanded once into n shorter strings that
// each cost up to n to build and hash.
// Space: O(n * 2^n) -- the Set of up to 2^n strings of up to n characters.
//
// Siblings: SubsetsIIBacktracking and SubsetsIITakeOrSkip make each distinct subset once without a
// Set, on lists of numbers.

public final class AllUniqueSubSetsHashSet {
  public static List<String> generate(String str) {
    Set<String> set = new HashSet<>();
    subset(str, set);
    return new ArrayList<>(set);
  }

  private static void subset(String str, Set<String> set) {
    if (str.length() == 0) { // BASE: the empty string is never added
      return;
    }

    if (!set.contains(str)) { // EXPAND only a string not reached before
      set.add(str); // RECORD
      for (int i = 0; i < str.length(); i++) {
        String substr = str.substring(0, i) + str.substring(i + 1); // DELETE position i
        subset(substr, set);
      }
    }
  }

  public static void main(String[] args) {
    // [xx, yz, xy, xz, xyz, x, xxy, xxyz, y, z, xxz], in hash order
    System.out.println(generate("xxyz"));
  }
}
