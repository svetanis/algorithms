package com.svetanis.algorithms.slidingwindow.array;

// Count subarrays with a given product -- no LeetCode number; source unknown
//
// Given an array of positive integers and a number k, count the subarrays whose product
// is exactly k. Returns the count as an int.
//
// Every value is at least 1, so growing the window on the right never lowers its product
// and dropping from the left never raises it. The window keeps its product at most k by
// dropping from the left. When the product is exactly k, only 1s keep it there: every 1
// right after right gives one more valid end, and every 1 at the front of the window gives
// one more valid start with the same ends. So the count is (ones after + 1) for the start
// left and again for each leading 1. Then the first value above 1 is dropped, the product
// falls below k, and the loop carries on after the run of 1s it already counted.
// k = 1 is different: a product of 1 needs a subarray made only of 1s, and such a window
// has no value above 1 to drop, so countOnes counts those runs instead.
//
// Time: O(n) -- right and left only move forward, the skip over 1s included
// Space: O(1) -- the product, two indices and a count
// The product is an int: it stays at most k before each multiply, so k * max(a) must fit.
//
// Siblings: CountSubArrsProductLessThanK (713) counts products below k with the same
// window, and SubArrsProductLessThanK lists those subarrays. prefixsum/CountSubArraysGivenSum
// (560) is the same question for a sum, answered with a map of prefix sums.

public final class CountSubArrsGivenProduct {

  public static int count(int[] a, int k) {
    if (k == 1) {
      return countOnes(a); // RETURN: a product of 1 is a run of 1s
    }
    int n = a.length;
    int prod = 1;
    int count = 0;
    int left = 0;

    for (int right = 0; right < n; right++) {
      prod *= a[right]; // ADD
      if (prod > k) {
        while (left <= right && prod > k) { // SHRINK while the product is too big
          prod /= a[left];
          left++;
        }
      }

      if (prod == k) {
        int ones = 0;
        while (right + 1 < n && a[right + 1] == 1) {
          ones++;
          right++;
        }
        count += (ones + 1); // COUNT: ends right - ones .. right, from the start left

        while (left <= right && a[left] == 1) {
          count += (ones + 1); // COUNT: the same ends, from a start one place later
          left++;
        }

        prod /= a[left]; // REMOVE: the first value above 1, so the product drops below k
        left++;
      }
    }
    return count;
  }

  // the subarrays made only of 1s: a run of m ones holds m(m+1)/2 of them
  private static int countOnes(int[] a) {
    int count = 0;
    int run = 0;
    for (int num : a) {
      run = num == 1 ? run + 1 : 0; // the run of 1s ending here
      count += run; // COUNT: starts inside the run, ending here
    }
    return count;
  }

  public static void main(String[] args) {

    int[] a1 = { 2, 1, 1, 1, 4, 5 };
    System.out.println(count(a1, 4));

    int[] a2 = { 1, 2, 3, 4, 1 };
    System.out.println(count(a2, 24));

  }
}
