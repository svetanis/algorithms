package com.svetanis.algorithms.search.binary.rotated;

public final class RotatedMinElementDuplicates {

  public static int min(int[] a) {
    int n = a.length;
    return min(a, 0, n - 1);
  }

  private static int min(int[] a, int left, int right) {
    // Time complexity: O(n)

    if (right < left) {
      return a[0];
    }

    if (left == right) {
      return a[left];
    }

    int mid = left + (right - left) / 2;

    // check if element (mid + 1) is min element.
    // consider the cases like {1, 1, 0, 1}
    if (mid < right && a[mid + 1] < a[mid]) {
      return a[mid + 1];
    }

    // this case causes O(n) time
    if (a[left] == a[mid] && a[right] == a[mid]) {
      int min1 = min(a, left, mid - 1);
      int min2 = min(a, mid + 1, right);
      return Math.min(min1, min2);
    }

    // check if mid itself is min element
    if (mid > left && a[mid] < a[mid - 1]) {
      return a[mid];
    }

    if (a[right] > a[mid]) {
      return min(a, left, mid - 1);
    }
    return min(a, mid + 1, right);
  }

  public static void main(String[] args) {
    int a1[] = { 5, 6, 1, 2, 3, 4 };
    System.out.println(min(a1));

    int a2[] = { 1, 1, 0, 1 };
    System.out.println(min(a2));

    int a3[] = { 1, 1, 2, 2, 3 };
    System.out.println(min(a3));

    int a4[] = { 3, 3, 3, 4, 4, 4, 4, 5, 3, 3 };
    System.out.println(min(a4));

    int a5[] = { 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 2 };
    System.out.println(min(a5));

    int a6[] = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1 };
    System.out.println(min(a6));

    int a7[] = { 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2 };
    System.out.println(min(a7));
  }
}