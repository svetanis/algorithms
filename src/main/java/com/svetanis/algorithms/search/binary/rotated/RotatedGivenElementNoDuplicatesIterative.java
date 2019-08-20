package com.svetanis.algorithms.search.binary.rotated;

public final class RotatedGivenElementNoDuplicatesIterative {

  public static int search(int[] a, int k) {
    // Time Complexity: O(log n)

    int n = a.length;
    int left = 0;
    int right = n - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (a[mid] == k) {
        return mid;
      }

      // a[left ... mid] sorted
      if (a[left] <= a[mid]) {
        if (k >= a[left] && k <= a[mid]) {
          right = mid - 1;
        } else { // k > a[mid]
          left = mid + 1;
        }
      } else {
        // a[mid ... right] sorted
        if (k >= a[mid] && k <= a[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] a1 = { 5, 6, 7, 8, 9, 10, 1, 2, 3 };
    System.out.println(search(a1, 3));

    int[] a2 = { 3, 4, 5, 1, 2 };
    System.out.println(search(a2, 3));
    System.out.println(search(a2, 4));

    int[] a3 = { 1, 2, 3, 4 };
    System.out.println(search(a3, 3));
  }
}