package com.svetanis.algorithms.slidingwindow.array;

// Count subarrays with sum less than k -- no LeetCode number; source unknown
//
// Given an array of integers and a number k, find the number of subarrays with sum
// strictly less than k. Assumes no value is negative: the window below needs that.
//
// With no negative values, dropping from the left never raises the sum. So once the
// window a[left..right] has sum below k, every subarray ending at right that starts
// inside the window does too: right - left + 1 of them, counted at the one position
// where each ends. The window drops from the left while its sum is k or more.
//
// Time: O(n) -- right and left only move forward
// Space: O(1) -- a running sum, two indices and a count
//
// Siblings: CountSubArrsProductLessThanK (713) is the same count for a product.
// CountSubArrsSumInGivenRange runs this window twice, with <=, to count a range.

public final class CountSubArrsSumLessThanK {

  public static int count(int[] a, int k) {
    int n = a.length;
    int sum = 0;
    int count = 0;
    int left = 0;
    
    for (int right = 0; right < n; right++) {
      sum += a[right]; // ADD
      while (left <= right && sum >= k) { // SHRINK while the sum is k or more
        sum -= a[left];
        left++;
      }
      count += right - left + 1; // COUNT: starts left .. right, all ending at right
    }
    return count;
  }

  public static void main(String[] args) {

    int[] a1 = { 2, 5, 6 };
    System.out.println(count(a1, 10));

    int[] a2 = { 1, 11, 2, 3, 15 };
    System.out.println(count(a2, 10));

  }
}
