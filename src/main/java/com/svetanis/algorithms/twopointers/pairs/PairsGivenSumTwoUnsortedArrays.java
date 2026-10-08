package com.svetanis.algorithms.twopointers.pairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// All Pairs With a Given Sum From Two Unsorted Arrays
//
// Input: two lists of distinct integers, in any order, and a target x.
// Return: every pair {y, z}, y from list2 and z from list1, with y + z == x, in the
// order of list2.
//
// The one idea: put list1 in a set. Each number y of list2 then has exactly one possible
// partner, x - y, and the set says in one lookup whether list1 holds it.
//
// Siblings:
//   CountPairsGivenSum2ArraysHashing -- counts the pairs instead of listing them
//   PairsGivenSumHashing -- both numbers from one array
//
// Time: O(n + m) -- the set over list1, then one lookup per number of list2.
// Space: O(n) -- the set.

public final class PairsGivenSumTwoUnsortedArrays {

  public static List<int[]> pairs(List<Integer> list1, List<Integer> list2, int x) {
    Set<Integer> set = new HashSet<>(list1);        // SEE every number of list1
    List<int[]> list = new ArrayList<>();
    for (int j = 0; j < list2.size(); j++) {
      int diff = x - list2.get(j);                  // the only partner list2.get(j) can have
      if (set.contains(diff)) {
        list.add(new int[] { list2.get(j), diff }); // FOUND
      }
    }
    return list;
  }

  public static void main(String[] args) {
    List<Integer> list1 = List.of(1, 2, 3, 7, 5, 4);
    List<Integer> list2 = List.of(0, 7, 4, 3, 2, 1);
    System.out.println(show(pairs(list1, list2, 8))); // [[7, 1], [4, 4], [3, 5], [1, 7]]

    List<Integer> list3 = List.of(1, 0, -4, 7, 6, 4);
    List<Integer> list4 = List.of(0, 2, 4, -3, 2, 1);
    System.out.println(show(pairs(list3, list4, 8))); // [[2, 6], [4, 4], [2, 6], [1, 7]]
  }

  // the pairs as text, e.g. [[7, 1], [4, 4]]
  private static String show(List<int[]> pairs) {
    List<String> out = new ArrayList<>();
    for (int[] p : pairs) {
      out.add(Arrays.toString(p));
    }
    return out.toString();
  }
}
