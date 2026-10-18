package com.svetanis.algorithms.slidingwindow.fixed;

// Mean of means of all windows of size k -- no LeetCode number; source unknown
//
// given an array of n-elements find the mean of the array 
// as mean of all consecutive m-elements of array for all 
// possible m-length array with consecutive elements. 
// Takes the array a and the window size k (the m above), 1 <= k <= a.length. Returns the
// average of the n - k + 1 window averages. The method is named maxSum, but it returns
// this mean.
//
// Each window's mean is its sum divided by k, and one step of a fixed window changes its sum
// by two numbers: a[i] enters, a[i - k] leaves. So every window's sum is an edit of the one
// before it, and the n - k + 1 window means are added up in one pass.
//
// Time: O(n) -- k additions build the first window, then one edit per remaining index
// Space: O(1) -- the running sum and the running total of means

public final class MeanOfMeans {

  public static double maxSum(int[] a, int k) {
    int n = a.length;
    double sum = 0;
    double mean = 0;

    for (int i = 0; i < k; i++) {
      sum += a[i]; // BUILD: the first window, a[0..k-1]
    }
    mean += sum / k; // RECORD: the first window's mean joins the total

    for (int i = k; i < n; i++) {
      sum += a[i] - a[i - k]; // IN a[i], OUT a[i - k]
      mean += sum / k; // RECORD: this window's mean joins the total
    }
    return mean / (n - k + 1); // DIVIDE: once, by the number of windows
  }

  public static void main(String[] args) {

    int[] a1 = { 2, 5, 7, 1, 9, 3, 9 };
    System.out.println(maxSum(a1, 4));

    int[] a2 = { 3, 5, 1, 8, 9, 4 };
    System.out.println(maxSum(a2, 4));

    int[] a3 = { 9, 4 };
    System.out.println(maxSum(a3, 1));

  }
}
