package com.svetanis.algorithms.search.median;

import static com.svetanis.java.base.utils.Nums.isEven;
import static java.lang.Math.max;
import static java.lang.Math.min;

public final class MedianTwoSortedEqualSizeCompare {

  public static double median(int[] a1, int[] a2) {
    int n = a1.length;
    return median(a1, 0, n - 1, a2, 0, n - 1);
  }

  private static double median(int[] a1, int s1, int e1, int[] a2, int s2, int e2) {
    // Time comlexity: O(log n)

    int n = e1 - s1 + 1;

    if (n <= 0) {
      return -1;
    }

    if (n == 1) {
      return (double) (a1[s1] + a2[s2]) / 2;
    }

    if (n == 2) {
      return (double) (max(a1[s1], a2[s2]) + min(a1[e1], a2[e2]))/2;
    }

    // get the median of the first and second arrays
    double median1 = MedianSortedArray.median(a1, s1, e1);
    double median2 = MedianSortedArray.median(a2, s2, e2);

    if (median1 == median2) {
      return median1;
    }

    // if median1 < median2 then median must
    // exist in a1[m1 ...] and a2[... m2]
    if (median1 < median2) {
      if (isEven(n)) {
        return median(a1, s1 + n / 2 - 1, e1, a2, s2, s2 + n / 2 - 1);
      }
      return median(a1, s1 + n / 2, e1, a2, s2, s2 + n / 2);
    }
    // if median1 > median2 than median must
    // exist in a1[... m1] and a2[m2 ...]
    else {
      if (isEven(n)) {
        return median(a2, s2 + n / 2 - 1, e2, a1, s1, s1 + n / 2 - 1);
      }
      return median(a2, s2 + n / 2, e2, a1, s1, s1 + n / 2);
    }
  }

  public static void main(String[] args) {
    int a1[] = { 1, 2, 3, 6 };
    int a2[] = { 4, 6, 8, 10 };
    System.out.println(median(a1, a2));
  }
}
