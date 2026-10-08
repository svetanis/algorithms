package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static java.util.Arrays.sort;

import java.util.Arrays;

// Pair With Sum Closest to x
//
// Input: an array of n >= 2 integers, in any order (the code sorts it), and a target x.
// Return: a pair {y, z}, y <= z, of values at two different positions whose sum is
// closest to x; the first one met when several tie.
//
// The one idea: LC 167's converging loop, recording the closest sum seen. If the sum is
// above x, every other partner left for a[right] is at least a[left], so its sums are
// further above x still: drop right. A sum at or below x mirrors it: drop left. Each
// step drops a number whose closest sum has already been compared.
//
// Siblings:
//   PairSumClosestToZeroSorted -- x fixed at 0
//   PairSumClosestToXTwoSorted -- one number from each of two sorted arrays
//   triplet.TripletClosestToGivenSum (LC 16) -- three numbers: the same loop beside a fixed one
//
// Time: O(n log n) -- the sort; the loop is O(n).
// Space: O(log n) -- the sort. The array is sorted in place: the caller's order is lost.

public final class PairSumClosestToXSorted {

  public static int[] pair(int[] a, int x) {
    int n = a.length;
    int first = -1;
    int second = -1;
    int left = 0;          // START: the smallest number
    int right = n - 1;     // START: the largest number
    int diff = MAX_VALUE;  // the closest distance to x so far
    sort(a);               // SORT
    while (right > left) { // STOP: a pair needs two different positions
      int sum = a[left] + a[right];
      if (abs(sum - x) < diff) {
        first = left;      // RECORD the closest pair so far
        second = right;
        diff = abs(sum - x);
      }
      if (sum > x) {
        right--;           // DROP RIGHT: every other partner makes the sum bigger
      } else {
        left++;            // DROP LEFT: every other partner makes the sum smaller
      }
    }
    return new int[] { a[first], a[second] };
  }

  public static void main(String[] args) {
    int[] a = { 10, 22, 28, 29, 30, 40 };
    System.out.println(Arrays.toString(pair(a, 54))); // [22, 30]
  }
}
