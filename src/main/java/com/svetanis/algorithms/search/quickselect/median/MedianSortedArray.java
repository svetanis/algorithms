package com.svetanis.algorithms.search.quickselect.median;

import static com.svetanis.java.base.utils.Nums.isEven;

public final class MedianSortedArray {

  public static double median(int[] a) {
    int n = a.length;
    return median(a, 0, n - 1);
  }

  public static double median(int[] a, int start, int end) {
    int n = end - start + 1;
    int index = n / 2;
    if (isEven(n)) {
      return (a[index] + a[index - 1]) / 2;
    }
    return a[index];
  }

  public static void main(String[] args) {
    int a1[] = { 1, 12, 15, 26, 38 };
    System.out.println(median(a1));

    int a2[] = { 2, 13, 17, 30, 45 };
    System.out.println(median(a2));
  }
}
