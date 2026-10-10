package com.svetanis.algorithms.twopointers.triplet;

import java.util.HashSet;
import java.util.Set;

// Count triplets with XOR 0
//
// Input: an array of DISTINCT integers.
// Return: how many sets of three positions have a[i] ^ a[j] ^ a[k] == 0.
//
// The one idea: x ^ y ^ z == 0 exactly when z == x ^ y. So for every pair, look x ^ y up in
// a set of all the values. The third number must sit at a third position: with distinct
// values, x ^ y equals x only when y == 0 and equals y only when x == 0, and the two !=
// tests rule those out. Each triplet is found once from each of its three pairs, hence / 3.
//
// Time: O(n^2) -- every pair once, a set lookup each.
// Space: O(n) for the set.

public final class CountTripletsXorZero {

  public static int count(int[] a) {
    int n = a.length;
    Set<Integer> set = new HashSet<>();
    for (int x : a) {
      set.add(x);                                   // RECORD every value
    }
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {             // FIX the pair (i, j)
        int xor = a[i] ^ a[j];                      // the only third number that works
        if (set.contains(xor) && xor != a[i] && xor != a[j]) {
          count++;                                  // FOUND, at a third position
        }
      }
    }
    return count / 3;                               // RETURN: each triplet came from 3 pairs
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, 5, 10, 14, 15 };
    System.out.println(count(a)); // 2
  }
}
