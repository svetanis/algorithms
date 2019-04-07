package com.svetanis.algorithms.sorting.mergesort.inversions;

public final class InversionsCountPseudoInPlace {

  public static int count(int[] a) {
    // Time Complexity: O(n log n)
    
    int n = a.length;
    int[] aux = new int[n];
    return count(a, aux, 0, n - 1);
  }

  private static int count(int[] a, int[] aux, int low, int high) {
    int count = 0;

    if (high <= low) {
      return count;
    }
    int mid = low + (high - low) / 2;
    count += count(a, aux, low, mid);
    count += count(a, aux, mid + 1, high);
    count += merge(a, aux, low, mid, high);
    return count;
  }

  private static int merge(int[] a, int[] aux, int low, int mid, int high) {

    int left = low;
    int right = mid + 1;
    int current = low;
    int count = 0;

    while (left <= mid && right <= high) {
      if (a[left] <= a[right]) {
        aux[current] = a[left++];
      } else {
        aux[current] = a[right++];
        count = count + (mid + 1 - left);
      }
      current++;
    }

    while (left <= mid) {
      aux[current++] = a[left++];
    }
    while (right <= high) {
      aux[current++] = a[right++];
    }

    for (int i = 0; i <= high; i++) {
      a[i] = aux[i];
    }

    return count;
  }

  public static void main(String[] args) {
    int[] a = { 4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69,
        62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20,
        25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73,
        41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54 }; // 2372

    System.out.println(count(a));
  }
}