package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Sum Swap -- hashing
//
// Input: two non-empty arrays of integers.
// Return: a pair {x, y}, x from a and y from b, such that swapping x and y leaves the two
// arrays with equal sums; {-1, -1} if there is none.
// Convention the code follows: when the sums are already equal it returns {-1, -1},
// even when the arrays share a value whose swap keeps them equal. {-1, -1} is also what
// a real swap of -1 for -1 looks like. The source statement would settle both.
//
// The one idea: swapping x for y adds y - x to a's sum and takes it from b's. The sums
// meet when y - x is half their difference, (sum(b) - sum(a)) / 2 -- a fixed target, and
// no target at all when the difference is odd. So put b in a set and, for each value x
// of a, look up y = x + target.
//
// Siblings:
//   SwapPairsSort -- sorts both and walks them with two pointers
//   search.binary.FairCandySwap (LC 888) -- the same set lookup on LeetCode, where an
//     answer is guaranteed
//
// Time: O(n + m) -- the sums, the sets, one lookup per value of a.
// Space: O(n + m) -- the two sets.

public final class SwapPairsHashing {

  public static int[] swap(int[] a, int[] b) {
    int diff = diff(a, b);
    if (diff == 0) {
      return new int[] { -1, -1 };   // RETURN: an odd difference, or equal sums
    }
    Set<Integer> set1 = new HashSet<>();
    for (int x : a) {
      set1.add(x);                   // the distinct values of a
    }
    Set<Integer> set2 = new HashSet<>();
    for (int y : b) {
      set2.add(y);                   // SEE every value of b
    }
    for (int s1 : set1) {
      int s2 = s1 + diff;            // the only value s1 can be swapped with
      if (set2.contains(s2)) {
        return new int[] { s1, s2 }; // FOUND
      }
    }
    return new int[] { -1, -1 };     // RETURN: no swap works
  }

  // the target y - x = (sum(b) - sum(a)) / 2; 0 when the difference is odd
  private static int diff(int[] a, int[] b) {
    int sum1 = 0;
    for (int x : a) {
      sum1 += x;
    }
    int sum2 = 0;
    for (int y : b) {
      sum2 += y;
    }
    if ((sum1 - sum2) % 2 != 0) {
      return 0; // an odd difference has no integer half
    } else {
      return (sum2 - sum1) / 2;
    }
  }

  public static void main(String[] args) {
    int[] a = { 4, 1, 2, 1, 1, 2 };
    int[] b = { 3, 6, 3, 3 };
    System.out.println(Arrays.toString(swap(a, b))); // [1, 3]

    int[] c = { 5, 7, 4, 6 };
    int[] d = { 1, 2, 3, 8 };
    System.out.println(Arrays.toString(swap(c, d))); // [5, 1]

    int[] e = { 4, 1, 2, 1, 1, 2 };
    int[] f = { 1, 6, 3, 3 };
    System.out.println(Arrays.toString(swap(e, f))); // [2, 3]
  }
}
