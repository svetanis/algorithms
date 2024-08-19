package com.svetanis.algorithms.search.binary.rotated;

public final class RotatedGivenElementNoDuplicatesRecursive {

  public static int search(int[] a, int k) {
    int n = a.length;
    return search(a, 0, n - 1, k);
  }

  public static int search(int[] a, int left, int right, int k) {
    // Time Complexity: O(log n)

    if (left > right) {
      return -1;
    }

    int mid = left + (right - left) / 2;
    if (a[mid] == k) {
      return mid;
    }

    
    if (a[left] <= a[mid]) {
      // a[left ... mid] sorted	
      if (k >= a[left] && k < a[mid]) {
        return search(a, left, mid - 1, k);
      }
      return search(a, mid + 1, right, k);
    } else {
      // a[mid ... right] sorted
      if (k >= a[mid] && k <= a[right]) {
        return search(a, mid + 1, right, k);
      }
      return search(a, left, mid - 1, k);
    }
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