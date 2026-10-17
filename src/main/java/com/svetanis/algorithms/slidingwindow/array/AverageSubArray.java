package com.svetanis.algorithms.slidingwindow.array;

import java.util.ArrayList;
import java.util.List;

// Average of every subarray of size k -- no LeetCode number; source unknown
//
// Given an array, find the average of each subarray of k contiguous elements in it.
// Takes an array of integers (positive and negative) and a size k, assumed k >= 1.
// Returns the averages in the order the windows start: n - k + 1 of them, or an empty
// list when k is larger than the array. k = 0 has no average and gives Infinity or NaN
// entries, not an error.
//
// Two neighbouring windows share all but two elements: one step adds a[right] on the
// right and removes a[left], k places behind it. So the running sum is edited, not
// rebuilt, and each full window's average is that sum divided by k. The sum is a double,
// so it does not wrap on values near the int limit.
//
// Time: O(n) -- each element is added once and subtracted at most once
// Space: O(1) besides the returned list of n - k + 1 averages
//
// Siblings: fixed/MaxAverageSubArray and fixed/MinAverageSubArray run the same window
// and keep only the largest or the smallest average, with where it is.
// fixed/MaxAverageSubArraySubmit is 643, returning the largest average only.

public final class AverageSubArray {

  public static List<Double> average(int[] a, int k) {
    int left = 0;
	double sum = 0.0;
    List<Double> list = new ArrayList<>();
    for(int right = 0; right < a.length; right++) {
    	sum += a[right]; // IN: a[right] enters on the right
    	if(right >= k - 1) { // BUILD until here: from right = k - 1 on, a[left..right] holds k elements
    		list.add(sum/k); // RECORD: the average of a[left..right]
    		sum -= a[left]; // OUT: a[left] leaves before the next element enters
    		left++;
    	}
    }
    return list;
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, 2, 6, -1, 4, 1, 8, 2 };
    System.out.println(average(a, 5));
  }
}
