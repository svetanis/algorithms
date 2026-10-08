package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Find a Pair Whose Sum Is in the Array
//
// Input: an array of distinct positive integers.
// Return: the first pair {a[i], a[j]}, i < j, in the order the loops try them, whose
// sum is also a value of the array; {-1, -1} if there is none.
//
// The one idea: put every value in a set, then try every pair; the set says in one
// lookup whether the pair's sum is in the array. The values are positive, so the sum
// is bigger than both numbers of the pair and is always a third number.
//
// Sibling: PairGivenSumHashing -- the target sum is given, so one pass is enough
//
// Time: O(n^2) -- every pair once.
// Space: O(n) -- the set.

public final class PairExistingSumHashing {

  public static int[] pair(int[] a) {
    int n = a.length;
    Set<Integer> set = new HashSet<>();
    for (int x : a) {
      set.add(x);                          // SEE every value
    }
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int sum = a[i] + a[j];
        if (set.contains(sum)) {
          return new int[] { a[i], a[j] }; // FOUND: the sum is a value of the array
        }
      }
    }
    return new int[] { -1, -1 };           // RETURN: no pair; -1 is never a value here
  }

  public static void main(String[] args) {
    int[] a = { 10, 4, 8, 13, 5 };
    System.out.println(Arrays.toString(pair(a))); // [8, 5]
  }
}
