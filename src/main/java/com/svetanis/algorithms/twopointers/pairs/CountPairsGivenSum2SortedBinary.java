package com.svetanis.algorithms.twopointers.pairs;

// Count Pairs With a Given Sum From Two Sorted Arrays -- binary search
//
// Input: two arrays sorted in non-decreasing order, of any lengths, and a target k.
// Return: how many pairs (x from a1, y from a2) add up to k.
// Convention the code follows: it counts the numbers of a1 whose partner k - x occurs
// in a2. With no repeated value inside either array that is exactly the number of
// pairs; with repeats it is neither the number of pairs of positions nor the number of
// pairs that use each number once (a1 = {5, 5}, a2 = {5}, k = 10 gives 2). The source
// statement would settle which count is wanted.
//
// The one idea: a number x of a1 has exactly one possible partner, k - x, and a2 is
// sorted, so a binary search says whether it is there.
//
// Siblings:
//   CountPairsGivenSum2SortedTwoPointers -- O(n + m) with one pointer per array
//   CountPairsGivenSum2ArraysHashing -- unsorted input, a map over a1
//
// Time: O(n log m) -- one binary search in a2 for each of the n numbers of a1.
// Space: O(log m) -- the recursion of the search.

public final class CountPairsGivenSum2SortedBinary {

  public static int count(int[] a1, int[] a2, int k) {
    int n1 = a1.length;
    int n2 = a2.length;
    int count = 0;
    for (int i = 0; i < n1; i++) {
      int diff = k - a1[i];                // the only partner a1[i] can have
      if (isBinary(a2, 0, n2 - 1, diff)) { // SEE whether a2 holds it
        count++;                           // COUNT a1[i]
      }
    }
    return count;
  }

  public static boolean isBinary(int[] a, int start, int end, int x) {
    if (end < start) {
      return false;                          // STOP: empty range, x is absent
    }
    int mid = start + (end - start) / 2;
    if (a[mid] == x) {
      return true;                           // FOUND
    } else if (x > a[mid]) {
      return isBinary(a, mid + 1, end, x);   // DROP LEFT half: all of it is below x
    } else {
      return isBinary(a, start, mid - 1, x); // DROP RIGHT half: all of it is above x
    }
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 5, 7 };
    int[] a2 = { 2, 3, 5, 8 };
    System.out.println(count(a1, a2, 10)); // 2

    int[] a3 = { 1, 2, 3, 4, 5, 7, 11 };
    int[] a4 = { 2, 3, 4, 5, 6, 8, 12 };
    System.out.println(count(a3, a4, 9)); // 5
  }
}
