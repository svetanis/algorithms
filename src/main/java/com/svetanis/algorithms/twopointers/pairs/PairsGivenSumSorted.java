package com.svetanis.algorithms.twopointers.pairs;

import static java.util.Arrays.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// All Pairs With a Given Sum -- sorting and two pointers
//
// Input: an array of integers, in any order (the code sorts it), and a target k.
// Return: the pairs {x, y}, x <= y, with x + y == k, smallest x first.
// Convention the code follows: the values are distinct, and then every pair is listed
// once. With repeats each number is used in at most one pair: {1, 1, 1, 1} with k = 2
// lists {1, 1} twice, while it has 6 pairs of positions. The source statement would
// settle whether values can repeat.
//
// The one idea: LC 167's converging loop, but a match does not end it. With distinct
// values a number has only one partner, so after a match both ends are used up and
// both move in.
//
// Siblings:
//   PairsGivenSumHashing -- one pass with a set, no sort
//   PairGivenSumSorted -- stops at the first pair
//   PairsGivenSumRotated -- the same loop on a sorted array that was rotated
//   twopointers.MaxNumOfKSumPairs1679 -- the same loop, counting the pairs
//
// Time: O(n log n) -- the sort; the loop is O(n).
// Space: O(log n) -- the sort, besides the list. The array is sorted in place: the
// caller's order is lost.

public final class PairsGivenSumSorted {

  public static List<int[]> pairs(int[] a, int k) {
    sort(a);                                       // SORT
    int n = a.length;
    int left = 0;                                  // START: the smallest number
    int right = n - 1;                             // START: the largest number
    List<int[]> list = new ArrayList<>();
    while (left < right) {                         // STOP: a pair needs two different positions
      int sum = a[left] + a[right];                // COMPARE
      if (sum == k) {
        list.add(new int[] { a[left], a[right] }); // FOUND
        left++;                                    // MOVE both: each number is in one pair at most
        right--;
      } else if (sum < k) {
        left++;                                    // DROP LEFT: too small even with the largest
      } else {
        right--;                                   // DROP RIGHT: too big even with the smallest
      }
    }
    return list;
  }

  public static void main(String[] args) {
    int[] a = { 9, 3, 6, 5, 7, -1, 13, 14, -2, 12, 0 };
    System.out.println(show(pairs(a, 12))); // [[-2, 14], [-1, 13], [0, 12], [3, 9], [5, 7]]
  }

  // the pairs as text, e.g. [[-2, 14], [-1, 13]]
  private static String show(List<int[]> pairs) {
    List<String> out = new ArrayList<>();
    for (int[] p : pairs) {
      out.add(Arrays.toString(p));
    }
    return out.toString();
  }
}
