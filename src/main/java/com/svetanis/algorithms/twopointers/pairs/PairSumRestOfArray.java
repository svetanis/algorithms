package com.svetanis.algorithms.twopointers.pairs;

import java.util.HashSet;
import java.util.Set;

// Pair Whose Sum Equals the Rest of the Array
//
// Input: an array of integers.
// Return: whether two numbers at different positions add up to the sum of all the
// other numbers.
//
// The one idea: if x + y equals the rest, then x + y is exactly half of the total, so
// this is a two-sum search for total / 2 -- and an odd total rules out every pair at once.
//
// Sibling: PairGivenSumHashing -- the same one-pass search (isPair), with the target given
//
// Time: O(n) -- one pass for the total, one for the search.
// Space: O(n) -- the set.

public final class PairSumRestOfArray {

  public static boolean isPair(int[] a) {
    int sum = 0;
    for (int x : a) {
      sum += x;                // the total of the whole array
    }
    if (sum % 2 != 0) {
      return false;            // RETURN: an odd total has no half
    }
    return isPair(a, sum / 2); // two numbers must make up half the total
  }

  private static boolean isPair(int[] a, int k) {
    int n = a.length;
    Set<Integer> set = new HashSet<>(); // the numbers seen so far
    for (int i = 0; i < n; i++) {
      int diff = k - a[i];              // the only partner a[i] can have
      if (set.contains(diff)) {
        return true;                    // FOUND: the partner came earlier
      }
      set.add(a[i]);                    // SEE a[i] for the numbers after it
    }
    return false;
  }

  public static void main(String[] args) {
    int[] a = { 2, 11, 5, 1, 4, 7 };
    System.out.println(isPair(a)); // true
  }
}
