package com.svetanis.algorithms.slidingwindow.fixed;

// given an array of n-elements find the mean of the array 
// as mean of all consecutive m-elements of array for all 
// possible m-length array with consecutive elements. 

public final class MeanOfMeans {

  public static double maxSum(int[] a, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = a.length;
    double sum = 0;
    double mean = 0;

    for (int i = 0; i < k; i++) {
      sum += a[i];
    }
    mean += sum / k;

    for (int i = k; i < n; i++) {
      sum += a[i] - a[i - k];
      mean += sum / k;
    }
    return mean / (n - k + 1);
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
