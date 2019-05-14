package com.svetanis.algorithms.search.binary.frequency;

public final class CountZeros {

  public static int countZeros(int[] a) {
    int n = a.length;
    int first = firstZero(a, 0, n - 1);
    if (first == -1) {
      return 0;
    }
    return n - first;
  }

  private static int firstZero(int[] a, int left, int right) {
    // O(log n)

    if (right < left) {
      return -1;
    }
    int mid = left + (right - left) / 2;
    if ((mid == 0 || a[mid - 1] == 1) && a[mid] == 0) {
      return mid;
    }
    // if mid element is not 0
    if (a[mid] == 1) {
      return firstZero(a, mid + 1, right);
    } else { // if mid element is 0, but not first 0
      return firstZero(a, left, mid - 1);
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 1, 1, 0, 0, 0, 0, 0 };
    System.out.println(countZeros(a));
  }
}