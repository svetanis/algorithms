package com.svetanis.algorithms.twopointers.pairs;

import static com.svetanis.algorithms.twopointers.pairs.PairGivenSumRotated.pivot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// All Pairs With a Given Sum in a Sorted and Rotated Array
//
// Input: an array of n >= 1 distinct integers that was sorted and then rotated, and a
// target k.
// Return: every pair {x, y} with x + y == k, smaller first, from the outermost pair inwards.
//
// The one idea: PairGivenSumRotated's circular converging loop, but a match does not
// end it. With distinct values each number is in at most one pair, so after a match
// both ends move in. When the two ends were neighbours, moving both would make them
// cross without ever being equal, so the loop stops right after that pair.
//
// Siblings:
//   PairGivenSumRotated -- stops at the first pair; pivot() lives there
//   PairsGivenSumSorted -- the same loop on an array that is not rotated
//
// Time: O(n) -- one pass for the pivot, then every step drops at least one number.
// Space: O(1) besides the list.

public final class PairsGivenSumRotated {

  public static List<int[]> pairs(int[] a, int k) {
    int n = a.length;
    int left = 0;                                  // START: smallest and largest, when not rotated
    int right = n - 1;
    int p = pivot(a);
    if (p != -1) {
      left = (p + 1) % n;                          // START: the smallest number, just after the drop
      right = p;                                   // START: the largest number, just before it
    }
    List<int[]> list = new ArrayList<>();
    while (left != right) {                        // STOP: the two ends have met
      int sum = a[left] + a[right];                // COMPARE
      if (sum == k) {
        list.add(new int[] { a[left], a[right] }); // FOUND
        if ((left + 1) % n == right) {
          break;                                   // STOP: the ends were neighbours
        }
        left = (left + 1) % n;                     // MOVE both: each number is in one pair at most
        right = (n - 1 + right) % n;
      } else if (sum < k) {
        left = (left + 1) % n;                     // DROP LEFT: step up, wrapping past the end
      } else {
        right = (n - 1 + right) % n;               // DROP RIGHT: step down, wrapping past the start
      }
    }
    return list;
  }

  public static void main(String[] args) {
    int[] a1 = { 11, 15, 6, 8, 9, 10 };
    System.out.println(show(pairs(a1, 16))); // [[6, 10]]

    int[] a2 = { 11, 15, 26, 38, 9, 10 };
    System.out.println(show(pairs(a2, 35))); // [[9, 26]]

    int[] a3 = { 11, 15, 26, 38, 9, 10 };
    System.out.println(show(pairs(a3, 45))); // []

    int[] a4 = { 11, 15, 6, 7, 8, 9, 10 };
    System.out.println(show(pairs(a4, 16))); // [[6, 10], [7, 9]]

    int[] a5 = { 1, 2, 3, 4 };
    System.out.println(show(pairs(a5, 5))); // [[1, 4], [2, 3]]
  }

  // the pairs as text, e.g. [[6, 10], [7, 9]]
  private static String show(List<int[]> pairs) {
    List<String> out = new ArrayList<>();
    for (int[] p : pairs) {
      out.add(Arrays.toString(p));
    }
    return out.toString();
  }
}
