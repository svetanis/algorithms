package com.svetanis.algorithms.search.binary.rotated;

public final class RotatedGivenElementDuplicatesRecursive {

  public static int search(int[] a, int k) {
    int n = a.length;
    return search(a, 0, n - 1, k);
  }

  private static int search(int[] a, int left, int right, int k) {
    if (right < left) {
      return -1;
    }

    int mid = left + (right - left) / 2;

    if (k == a[mid]) {
      return mid;
    }

    // left is sorted
    if (a[left] < a[mid]) {
      if (k >= a[left] && k <= a[mid]) {
        return search(a, left, mid - 1, k);
      } else {
        return search(a, mid + 1, right, k);
      }
    } else if (a[mid] < a[left]) {
      // right is sorted
      if (k >= a[mid] && k <= a[right]) {
        return search(a, mid + 1, right, k);
      } else {
        return search(a, left, mid - 1, k);
      }
    }
    // Left is either all repeats OR loops around
    // (with the right half being all dups)
    else if (a[left] == a[mid]) {
      // if right half is different, search there
      if (a[mid] != a[right]) {
        return search(a, mid + 1, right, k);
      } else {
        // we have to search both halves
        int result = search(a, left, mid - 1, k);
        if (result == -1) {
          return search(a, mid + 1, right, k);
        } else {
          return result;
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

    int[] a3 = { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 };
    System.out.println(search(a3, 0));

    int[] a4 = { 2, 3, 0, 2, 2, 2, 2, 2, 2, 2 };
    System.out.println(search(a4, 3));
    System.out.println(search(a4, 2));

    int[] a5 = { 1, 2, 3, 4 };
    System.out.println(search(a5, 3));

    int[] a6 = { 2, 3, 2, 2, 2, 2, 2, 2, 2, 2 };
    System.out.println(search(a6, 2));
    System.out.println(search(a6, 3));
    System.out.println(search(a6, 4));
    System.out.println(search(a6, 1));
    System.out.println(search(a6, 8));
  }
}