package com.svetanis.algorithms.twopointers.dups;

// Find the duplicate in a permutation with one extra copy
//
// Input: n + 1 integers holding each of 1..n exactly once, plus a second copy of one
// of them.
// Return: the value that appears twice.
//
// The one idea: the array is 1..n plus the repeated value, so its sum is
// n * (n + 1) / 2 plus the repeated value. Subtract the sum of 1..n and the repeat is
// what is left.
//
// Siblings:
//   DuplicateInPermutationXor -- the same contract; XOR cancels the pairs instead of a subtraction
//   DuplicateInPlaceFloyd     -- LC 287, where the repeat may crowd other values out: no sum works there
//   DuplicateInPlaceBinary    -- LC 287 by counting the numbers <= mid, O(n log n)
//
// Time: O(n) -- one pass to add the numbers up.
// Space: O(1).

public final class DuplicateInPermutationSum {

  // the contract above is load-bearing: 1..n must each be present, so
  // that sum(a) exceeds sum(1..n) by exactly the repeated value.
  // on { 3, 3, 3, 3, 3 } this returns 5 -- see DuplicateInPlaceMarking.
  public static int duplicate(int[] a) {
    int n = a.length - 1;        // START: n + 1 numbers hold the values 1..n
    int total = n * (n + 1) / 2; // COUNT: the sum of 1..n, each once
    int sum = 0;
    for (int x : a) {
      sum += x;                  // COUNT: the sum of the array
    }
    return sum - total;          // RETURN what the extra copy added
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 4, 2, 2 };
    System.out.println(duplicate(a1)); // 2

    int[] a2 = { 3, 1, 3, 4, 2 };
    System.out.println(duplicate(a2)); // 3

    int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
    System.out.println(duplicate(a4)); // 7
  }
}
