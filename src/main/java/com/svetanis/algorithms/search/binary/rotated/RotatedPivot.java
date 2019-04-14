package com.svetanis.algorithms.search.binary.rotated;

public final class RotatedPivot {

  public static int pivot(int[] a) {
    int n = a.length;
    return pivot(a, 0, n - 1);
  }

  public static int pivot(int[] a, int low, int high) {
    // time complexity: O(log n)
    
    if (high < low) {
      return -1;
    }

    if (high == low) {
      return low;
    }

    int mid = low + (high - low)/2;

    if (mid < high && a[mid] > a[mid + 1]) {
      return mid;
    }

    if (mid > low && a[mid] < a[mid - 1]) {
      return mid - 1;
    }

    if (a[low] >= a[mid]) {
      return pivot(a, low, mid - 1);
    } else {
      return pivot(a, mid + 1, high);
    }
  }

  public static void main(String[] args) {
    int[] a1 = { 5, 6, 7, 8, 9, 10, 1, 2, 3 };
    System.out.println(pivot(a1));

    int[] a2 = { 3, 4, 5, 1, 2 };
    System.out.println(pivot(a2));

    int[] a3 = { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 };
    System.out.println(pivot(a3));

    int[] a4 = { 2, 3, 0, 2, 2, 2, 2, 2, 2, 2 };
    System.out.println(pivot(a4));

    int[] a5 = { 1, 2, 3, 4 };
    System.out.println(pivot(a5));

    int[] a6 = { 2, 3, 2, 2, 2, 2, 2, 2, 2, 2 };
    System.out.println(pivot(a6));
    
    int[] a7 = { 15, 18, 2, 3, 6, 12 };
    System.out.println(pivot(a7));
  }
}