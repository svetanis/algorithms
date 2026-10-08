package com.svetanis.algorithms.twopointers.pairs;

import java.util.HashSet;
import java.util.Set;

// Count Pairs With a Given XOR
//
// Input: an array of positive integers and a number k.
// Return: how many pairs have a[i] ^ a[j] == k.
// Convention the code follows: the values are distinct, and then this is the number of
// pairs of positions. The set below forgets how many copies it has seen, so with
// repeats it counts each position once if ANY partner came before it: {1, 1, 4} with
// k = 5 gives 1, while it has 2 pairs of positions. The source statement would settle
// whether values can repeat.
//
// The one idea: a ^ b == k exactly when a ^ k == b -- XOR with k undoes itself -- so the
// only partner of x is x ^ k, and a set of the numbers seen so far says in one lookup
// whether it came earlier.
//
// Siblings:
//   CountPairsXorZeroHashing, CountPairsXorZeroSorting -- k == 0, which means equal values
//   PairGivenSumHashing -- the same one-pass lookup with partner target - x
//
// Time: O(n) -- one lookup per number.
// Space: O(n) -- the set.

public final class CountPairsGivenXor {

  public static int count(int[] a, int k) {
    int n = a.length;
    int count = 0;
    Set<Integer> set = new HashSet<>(); // the numbers seen so far
    for (int i = 0; i < n; i++) {
      int xor = k ^ a[i];               // the only partner a[i] can have
      if (set.contains(xor)) {
        count++;                        // FOUND a partner before i
      }
      set.add(a[i]);                    // SEE a[i] for the numbers after it
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a1 = { 5, 4, 10, 15, 7, 6 };
    System.out.println(count(a1, 5)); // 1

    int[] a2 = { 3, 6, 8, 10, 15, 50 };
    System.out.println(count(a2, 5)); // 2
  }
}
