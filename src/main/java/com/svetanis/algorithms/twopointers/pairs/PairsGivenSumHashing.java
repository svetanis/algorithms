package com.svetanis.algorithms.twopointers.pairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// All Pairs With a Given Sum -- one pass with a set
//
// Input: an array of integers, in any order, and a target k.
// Return: the pairs {x, y} with x + y == k, each listed when its later number is
// reached, later number first.
// Convention the code follows: the values are distinct, and then every pair is listed
// once. The set forgets how many copies it has seen, so with repeats a number is
// listed once if ANY partner came before it: {3, 3, 3} with k = 6 lists 2 pairs, while
// it has 3 pairs of positions. The source statement would settle whether values can repeat.
//
// The one idea: the only partner of x is k - x, and a set of the numbers seen so far
// says in one lookup whether it came earlier. Looking up before adding x keeps x from
// pairing with itself.
//
// Siblings:
//   PairsGivenSumSorted -- sorts, then converging pointers; the pairs come out in order
//   PairGivenSumHashing -- stops at the first pair
//   CountPairsGivenSumHashing -- counts pairs of positions instead of listing pairs
//
// Time: O(n) -- one lookup per number.
// Space: O(n) -- the set and the list.

public final class PairsGivenSumHashing {

  public static List<int[]> pairs(int[] a, int k) {
    int n = a.length;
    Set<Integer> set = new HashSet<>();     // the numbers seen so far
    List<int[]> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int diff = k - a[i];                  // the only partner a[i] can have
      if (set.contains(diff)) {
        list.add(new int[] { a[i], diff }); // FOUND: the partner came earlier
      }
      set.add(a[i]);                        // SEE a[i] for the numbers after it
    }
    return list;
  }

  public static void main(String[] args) {
    int[] a = { 9, 3, 6, 5, 7, -1, 13, 14, -2, 12, 0 };
    System.out.println(show(pairs(a, 12))); // [[3, 9], [7, 5], [13, -1], [-2, 14], [0, 12]]
  }

  // the pairs as text, e.g. [[3, 9], [7, 5]]
  private static String show(List<int[]> pairs) {
    List<String> out = new ArrayList<>();
    for (int[] p : pairs) {
      out.add(Arrays.toString(p));
    }
    return out.toString();
  }
}
