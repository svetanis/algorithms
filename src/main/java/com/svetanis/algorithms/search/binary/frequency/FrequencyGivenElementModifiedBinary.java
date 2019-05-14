package com.svetanis.algorithms.search.binary.frequency;

public final class FrequencyGivenElementModifiedBinary {

  public static int search(int[] a, int x) {
    int n = a.length;
    return search(a, 0, n - 1, x);
  }

  private static int search(int[] a, int start, int end, int x) {
    int count = 0;

    if (start <= end) {
      int mid = start + (end - start) / 2;

      if (a[mid] == x) {
        int left = search(a, start, mid - 1, x);
        int right = search(a, mid + 1, end, x);
        count = 1 + left + right;
      } else if (x <= a[mid]) {
        count = search(a, start, mid - 1, x);
      } else {
        count = search(a, mid + 1, end, x);
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, 3, 5, 5, 6, 7, 7, 9, 9 };
    System.out.println(search(a, 9));
  }
}