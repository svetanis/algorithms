package com.svetanis.algorithms.twopointers.triplet;

import java.util.Arrays;

// 16. 3Sum Closest
//
// Input: an unsorted array of at least three integers, and a target.
// Return: the sum of three numbers at three different positions that is closest to the
// target. When several sums are equally close, the smallest of them -- LeetCode guarantees
// a single closest sum, so this tie-break never shows there.
//
// The one idea: sort, FIX a[i], converge on the numbers to its right -- the 3Sum loop
// (LC 15) -- but SCORE every pair against a best-so-far, because there is no exact sum to
// wait for. The moves are 3Sum's: a sum below the target can only get closer by growing,
// so drop left; a sum above it, drop right. An exact hit cannot be beaten.
//
// Sibling: twopointers.ThreeSum15 -- the same loop, acting only on a sum of exactly 0.
//
// Time: O(n^2) -- n fixed numbers, an O(n) converging pass for each; the sort is O(n log n).
// Space: O(1) besides the sort, which works in place.

public final class TripletClosestToGivenSum {

  public static int triplet(int[] a, int target) {
    Arrays.sort(a);                               // SORT: the converging pass needs it

    int n = a.length;
    int min = Integer.MAX_VALUE;                  // the best DISTANCE seen so far
    int result = target;                          // its sum; the first pair overwrites both

    for (int i = 0; i < n - 2; ++i) {             // FIX a[i]
      int left = i + 1;                           // START: the two ends right of i
      int right = n - 1;
      while (left < right) {                      // STOP: a pair needs two positions
        int sum = a[i] + a[left] + a[right];
        int diff = Math.abs(target - sum);        // COMPARE: score EVERY pair
        if (diff == 0) {
          return sum;                             // FOUND: an exact hit cannot be beaten
        }
        // the second clause is the header's tie-break: among triplets
        // equally close to the target, keep the SMALLEST sum
        if (diff < min || (diff == min && sum < result)) {
          min = diff;                             // RECORD the closer sum
          result = sum;
        }
        if (sum <= target) {
          left++;                                 // DROP LEFT: too small even with the largest
        } else {
          right--;                                // DROP RIGHT: too big even with the smallest
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] a = { -1, 2, 1, -4 };
    System.out.println(triplet(a, 1)); // 2

    int[] a1 = { -2, 0, 1, 2 };
    System.out.println(triplet(a1, 2)); // 1

    int[] a2 = { -3, -1, 1, 2 };
    System.out.println(triplet(a2, 1)); // 0

    int[] a3 = { 1, 0, 1, 1 };
    System.out.println(triplet(a3, 100)); // 3

    int[] a4 = { 0, 0, 0};
    System.out.println(triplet(a4, 1)); // 0

    int[] a5 = { -2, 10, -5, -9, -2, 4, 2 };
    System.out.println(triplet(a5, 2)); // 1 -- 3 is just as close; the smaller sum wins
  }
}