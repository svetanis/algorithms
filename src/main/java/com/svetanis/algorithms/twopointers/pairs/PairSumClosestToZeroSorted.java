package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static java.util.Arrays.sort;

import java.util.Arrays;

// Pair With Sum Closest to Zero
//
// Input: an array of integers, in any order (the code sorts it).
// Return: a pair {y, z}, y <= z, of values at two different positions whose sum is
// closest to 0; {-1, -1} for fewer than 2 numbers.
//
// The one idea: PairSumClosestToXSorted with x = 0. A negative sum can only get closer
// to 0 with a larger left number, since every partner left for a[left] is at most
// a[right]: drop left. A sum of 0 or more mirrors it: drop right.
//
// Siblings:
//   PairSumClosestToXSorted -- any target x
//   PairsPosNegHashing -- the pairs whose sum is exactly 0
//
// Time: O(n log n) -- the sort; the loop is O(n).
// Space: O(log n) -- the sort. The array is sorted in place: the caller's order is lost.

public final class PairSumClosestToZeroSorted {

  public static int[] pair(int[] a) {
    int n = a.length;
    if (n < 2) {
      return new int[] { -1, -1 }; // RETURN: no pair
    }
    sort(a);                       // SORT
    int min = MAX_VALUE;           // the sum closest to 0 so far
    int left = 0;                  // START: the smallest number
    int right = n - 1;             // START: the largest number
    int first = left;
    int second = right;
    while (left < right) {         // STOP: a pair needs two different positions
      int sum = a[left] + a[right];
      if (abs(sum) < abs(min)) {
        min = sum;                 // RECORD the closest pair so far
        first = left;
        second = right;
      }
      if (sum < 0) {
        left++;                    // DROP LEFT: every other partner makes the sum more negative
      } else {
        right--;                   // DROP RIGHT: every other partner makes the sum bigger
      }
    }
    return new int[] { a[first], a[second] };
  }

  public static void main(String[] args) {
    int[] a = { 1, 60, -10, 70, -80, 85 };
    System.out.println(Arrays.toString(pair(a))); // [-80, 85]
  }
}
