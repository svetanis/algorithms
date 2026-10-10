package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;
import java.util.Optional;

// Triplet with a given sum, by sorting -- find one
//
// Input: an array of integers, repeats allowed, and a target k.
// Return: one triplet of numbers at three different positions summing to k, in ascending
// order, or empty when there is none.
//
// The one idea: sort, then FIX the smallest number a[i] and converge on the numbers to its
// right -- Two Sum on a sorted array (LC 167). A sum too small can only grow by dropping
// left; a sum too big can only shrink by dropping right. Each drop discards a number that
// has no partner left, so a triplet is never skipped.
//
// Siblings -- the same question:
//   twopointers.triplet.TripletGivenSumHashing -- no sort; a set per fixed number, O(n) space
//   twopointers.ThreeSum15                     -- LC 15: lists EVERY distinct triplet summing
//                                                to 0
//
// Time: O(n^2) -- n fixed numbers, an O(n) converging pass for each; the sort is O(n log n).
// Space: O(1) besides the sort, which works in place.

public final class TripletGivenSumSorted {

  public static Optional<int[]> triplet(int[] a, int k) {
    int n = a.length;
    Arrays.sort(a);                               // SORT: the converging pass needs it
    for (int i = 0; i < n - 2; ++i) {             // FIX the smallest number a[i]
      int left = i + 1;                           // START: the two ends right of i
      int right = n - 1;
      while (left < right) {                      // STOP: a pair needs two positions
        int sum = a[i] + a[left] + a[right];      // COMPARE
        if (sum == k) {
          return Optional.of(new int[] { a[i], a[left], a[right] }); // FOUND
        } else if (sum < k) {
          left++;                                 // DROP LEFT: too small even with the largest
        } else {
          right--;                                // DROP RIGHT: too big even with the smallest
        }
      }
    }
    return Optional.empty();                      // RETURN empty: no triplet
  }

  public static void main(String[] args) {
    int[] a = { 1, 4, 45, 6, 10, 8 };
    System.out.println(triplet(a, 22).map(Arrays::toString).orElse("none")); // [4, 8, 10]
    System.out.println(triplet(a, 100).map(Arrays::toString).orElse("none")); // none
  }
}