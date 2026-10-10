package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

// Triplet with a given sum from three arrays, by hashing
//
// Input: three arrays of integers and a target k.
// Return: {x, y, z} with x from a1, y from a2, z from a3 and x + y + z == k -- the first
// found -- or empty when there is none.
//
// The one idea: two numbers fix the third. For every pair (y, z) from a2 and a3,
// x = k - y - z is forced, and a set of the values of a1 says in O(1) whether it is there.
//
// Sibling: twopointers.triplet.TripletGivenSum3ArraysBinarySearch -- sorts a3 and binary
//   searches it instead: O(log n3) per pair, no extra space.
//
// Time: O(n1 + n2 * n3) -- build the set, then one lookup per pair.
// Space: O(n1) for the set.

public final class TripletGivenSum3ArraysHashing {

  public static Optional<int[]> triplet(int[] a1, int[] a2, int[] a3, int k) {
    int n2 = a2.length;
    int n3 = a3.length;

    Set<Integer> set = new HashSet<>();
    for (int x : a1) {
      set.add(x);                                 // RECORD every value of a1
    }
    for (int i = 0; i < n2; ++i) {                // FIX y from a2
      for (int j = 0; j < n3; ++j) {              // FIX z from a3
        int sum = a2[i] + a3[j];
        int diff = k - sum;                       // the first number is forced
        if (set.contains(diff)) {
          return Optional.of(new int[] { diff, a2[i], a3[j] }); // FOUND
        }
      }
    }
    return Optional.empty();                      // RETURN empty: no triplet
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 2, 3, 4, 5 };
    int[] a2 = { 2, 3, 6, 1, 2 };
    int[] a3 = { 3, 2, 4, 5, 6 };

    System.out.println(triplet(a1, a2, a3, 9).map(Arrays::toString).orElse("none")); // [4, 2, 3]
    System.out.println(triplet(a1, a2, a3, 100).map(Arrays::toString).orElse("none")); // none
  }
}