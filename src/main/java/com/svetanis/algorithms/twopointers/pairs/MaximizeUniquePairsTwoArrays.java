package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Math.abs;
import static java.util.Arrays.sort;

// Most Pairs Within K From Two Arrays
//
// Input: two arrays of integers, in any order and of any lengths, and k >= 0.
// Return: the most pairs (x from a, y from b) with |x - y| <= k, each number used in at
// most one pair.
//
// The one idea: sort both and match greedily from the smallest. Look at the smallest
// unused x and y. If they are within k, pair them: in any best matching that pairs
// them elsewhere, x' with y and x with y', the swap to x with y and x' with y' is also
// within k, so pairing them never costs a pair. If they are not within k, the smaller
// of the two is too small for every number left on the other side, so it is in no pair
// and is dropped. Each pointer is bounded by its OWN array's length.
//
// Sibling: CountPairsGivenSum2SortedTwoPointers -- one pointer per array as well, for an
//   exact sum instead of a difference of at most k
//
// Time: O(n log n + m log m) -- the sorts; the walk is O(n + m).
// Space: O(log n + log m) -- the sorts. Both arrays are sorted in place: the caller's
// order is lost.

public final class MaximizeUniquePairsTwoArrays {

  public static int count(int[] a, int[] b, int k) {
    sort(a);                                  // SORT both: the smallest unused numbers first
    sort(b);
    int n = a.length;
    int m = b.length;
    int count = 0;
    for (int i = 0, j = 0; i < n && j < m;) { // STOP when either array runs out
      if (abs(a[i] - b[j]) <= k) {
        count++;                              // FOUND: pair the two smallest numbers left
        i++;
        j++;
      } else if (a[i] > b[j]) {
        j++;                                  // DROP b[j]: too small for every number left in a
      } else {
        i++;                                  // DROP a[i]: too small for every number left in b
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 3, 4, 5, 2, 1 };
    int[] b = { 6, 5, 4, 7, 15 };
    System.out.println(count(a, b, 3)); // 4

    int[] c = { 10, 15, 20 };
    int[] d = { 17, 12, 24 };
    System.out.println(count(c, d, 3)); // 2

    int[] e = { 1, 2, 3 };
    int[] f = { 2 };
    System.out.println(count(e, f, 0)); // 1
  }
}
