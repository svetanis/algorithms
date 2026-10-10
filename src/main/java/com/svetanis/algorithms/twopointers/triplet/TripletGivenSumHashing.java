package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

// Triplet with a given sum, by hashing -- find one
//
// Input: an array of integers, repeats allowed, and a target k.
// Return: one triplet of numbers at three different positions summing to k, as
// {a[i], a[j], third}, or empty when there is none.
//
// The one idea: FIX a[i]; the rest is Two Sum (LC 1) on the numbers to its right with
// target k - a[i]. Walk them once, and for each a[j] ask a set of the numbers already passed
// for k - a[i] - a[j]. The set is emptied for every i, so it only ever holds numbers at
// positions strictly between i and j -- three different positions.
//
// Siblings -- the same question:
//   twopointers.triplet.TripletGivenSumSorted         -- sorts and converges, O(1) extra space
//   twopointers.triplet.TripletGivenSum3ArraysHashing -- the three numbers come from three arrays
//
// Time: O(n^2) -- n fixed numbers, one pass with a set lookup each.
// Space: O(n) for the set.

public final class TripletGivenSumHashing {

  public static Optional<int[]> triplet(int[] a, int k) {
    int n = a.length;

    for (int i = 0; i < n - 2; ++i) {             // FIX a[i]
      int pairSum = k - a[i];                     // the other two must sum to this
      Set<Integer> set = new HashSet<>();         // START empty: numbers between i and j
      for (int j = i + 1; j < n; j++) {
        int diff = pairSum - a[j];                // the third number is forced
        if (set.contains(diff)) {
          return Optional.of(new int[] { a[i], a[j], diff }); // FOUND
        }
        set.add(a[j]);                            // RECORD a[j] for the positions after it
      }
    }
    return Optional.empty();                      // RETURN empty: no triplet
  }

  public static void main(String[] args) {
    int[] a = { 1, 4, 45, 6, 10, 8 };
    System.out.println(triplet(a, 22).map(Arrays::toString).orElse("none")); // [4, 8, 10]
    System.out.println(triplet(a, 100).map(Arrays::toString).orElse("none")); // none
  }
}