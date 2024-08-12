package com.svetanis.algorithms.sorting.mergesort;

import static com.svetanis.java.base.utils.Arrays.sum;
import static java.lang.Math.max;

// given two sorted arrays such that 
// the arrays may have some common elements
// find the sum of the max sum path to reach
// from beginning of any array to end of any
// of the two arrays. to switch from one array
// to another array only at common elements

public final class MaxSumPathSortedArrays {

  public static int maxSumPath(int[] a1, int[] a2) {
    // Time complexity: O(n + m)

    if (!isIntersect(a1, a2)) {
      return max(sum(a1), sum(a2));
    }

    int max = 0;
    int i = 0, j = 0;
    int sum1 = 0, sum2 = 0;
    int n = a1.length;
    int m = a2.length;

    while (i < n && j < m) {
      if (a1[i] < a2[j]) { // add elements of a1[] to sum1
        sum1 += a1[i++];
      } else if (a2[j] < a1[i]) { // add elements of a2[] to sum2
        sum2 += a2[j++];
      } else {// (a1[i] == a2[j])
        // take the max of two sums and add to result
        max += max(sum1, sum2);
        // update sum1 && sum2 for elements after this intersect
        sum1 = 0;
        sum2 = 0;
        // keep updating result while there are more common elements
        while (i < n && j < m && a1[i] == a2[j]) {
          max += a1[i];
          i++;
          j++;
        }
      }
    }

    // add remaining elements of a1[]
    while (i < n) {
      sum1 += a1[i++];
    }
    // add remaining elements of a2[]
    while (j < m) {
      sum2 += a2[j++];
    }
    // add max of two sums of remaining elements
    max += max(sum1, sum2);
    return max;
  }

  private static boolean isIntersect(int[] a1, int[] a2) {
    int i = 0, j = 0;
    while (i < a1.length && j < a2.length) {
      if (a1[i] == a2[j]) {
        return true;
      } else if (a1[i] < a2[j]) {
        i++;
      } else {
        j++;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[] a1 = { 2, 3, 7, 10, 12 };
    int[] a2 = { 1, 5, 7, 8 };
    System.out.println(maxSumPath(a1, a2));

    int[] a3 = { 10, 12 };
    int[] a4 = { 5, 7, 9 };
    System.out.println(maxSumPath(a3, a4));

    int[] a5 = { 2, 3, 7, 10, 12, 15, 30, 34 };
    int[] a6 = { 1, 5, 7, 8, 10, 15, 16, 19 };
    System.out.println(maxSumPath(a5, a6));
  }
}