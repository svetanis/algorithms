package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;
import java.util.Optional;

// Pythagorean triplet with a given sum
//
// Input: a whole number n.
// Return: natural numbers a < b < c with a^2 + b^2 == c^2 and a + b + c == n, as {a, b, c}
// -- the one with the smallest a, and then the smallest b -- or empty when none exists.
//
// The one idea: the sum fixes the third number once two are chosen, c = n - a - b, so only
// two loops are needed, not three. a is the smallest of three, so a <= n / 3; b is smaller
// than c, so b < n / 2. c > b needs no test: a^2 + b^2 == c^2 with a >= 1 already forces it.
//
// Time: O(n^2) -- about n / 3 * n / 2 pairs.
// Space: O(1).

public final class PythagoreanTripletGivenSum {

  public static Optional<int[]> triplet(int n) {
    for (int i = 1; i <= n / 3; i++) {            // FIX the smallest number a
      for (int j = i + 1; j <= n / 2; j++) {      // FIX the middle number b
        int k = n - i - j;                        // the sum forces c
        if (i * i + j * j == k * k) {
          return Optional.of(new int[] { i, j, k }); // FOUND
        }
      }
    }
    return Optional.empty();                      // RETURN empty: no such triplet
  }

  public static void main(String[] args) {
    System.out.println(triplet(12).map(Arrays::toString).orElse("none")); // [3, 4, 5]
    System.out.println(triplet(30).map(Arrays::toString).orElse("none")); // [5, 12, 13]
    System.out.println(triplet(10).map(Arrays::toString).orElse("none")); // none
  }
}
