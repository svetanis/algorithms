package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Find a Pair With a Given Sum -- one pass with a set
//
// Input: a list of integers, in any order, and a target.
// Return: pair -- a pair {x, y} of values at two different positions with x + y ==
// target, the later number first; {-1, -1} if there is none. isPair -- whether such a
// pair exists.
//
// The one idea: walk left to right. The only partner of x is target - x, and a set of
// the numbers seen so far says in one lookup whether it came earlier. Looking up
// before adding x keeps x from pairing with itself, and every pair is found when its
// later number is reached.
//
// Siblings:
//   PairGivenSumSorted -- sorted input, converging pointers, O(1) space
//   PairGivenSumHashingIndices (LC 1) -- the same pass with a map, returns positions
//   PairsGivenSumHashing -- every pair, not the first
//
// Time: O(n) -- one lookup per number.
// Space: O(n) -- the set.

public final class PairGivenSumHashing {

  public static int[] pair(List<Integer> list, int target) {
    int n = list.size();
    Set<Integer> set = new HashSet<>();         // the numbers seen so far
    for (int i = 0; i < n; i++) {
      int diff = target - list.get(i);          // the only partner list.get(i) can have
      if (set.contains(diff)) {
        return new int[] { list.get(i), diff }; // FOUND: the partner came earlier
      }
      set.add(list.get(i));                     // SEE list.get(i) for the numbers after it
    }
    return new int[] { -1, -1 };                // RETURN: no pair
  }

  public static boolean isPair(List<Integer> list, int k) {
    int n = list.size();
    Set<Integer> set = new HashSet<>(); // the numbers seen so far
    for (int i = 0; i < n; i++) {
      int diff = k - list.get(i);       // the only partner list.get(i) can have
      if (set.contains(diff)) {
        return true;                    // FOUND: the partner came earlier
      }
      set.add(list.get(i));             // SEE list.get(i) for the numbers after it
    }
    return false;
  }

  public static void main(String[] args) {
    List<Integer> list = List.of(1, 4, 45, 6, 10, -8);
    System.out.println(Arrays.toString(pair(list, 16))); // [10, 6]
    System.out.println(isPair(list, 16));                // true
  }
}
