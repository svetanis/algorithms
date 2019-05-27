package com.svetanis.algorithms.search.binary;

import java.util.Arrays;

public final class BinarySearchInsertPosition {

  public static int binary(int[] a, int x) {
    // O(log n)
    Arrays.sort(a);
    int start = 0;
    int end = a.length - 1;

    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (x > a[mid]) {
        start = mid + 1;
      } else if (x < a[mid]) {
        end = mid - 1;
      } else {
        return mid;
      }
    }
    return start;
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, 5, 6 };
    System.out.println(binary(a, 5)); // 2
    System.out.println(binary(a, 2)); // 1
    System.out.println(binary(a, 7)); // 4
    System.out.println(binary(a, 0)); // 0
  }
}