package com.svetanis.algorithms.sorting.heapsort;

import static com.svetanis.java.base.utils.Print.print;
import static com.svetanis.java.base.utils.Swap.swap;

public final class HeapSort {

  public static void sort(int[] a) {
    // Time Complexity: O(n log n)

    int n = a.length;
    asMaxHeap(a);
    for (int i = n - 1; i >= 0; i--) {
      swap(a, 0, i);
      heapify(a, i, 0);
    }
  }

  private static void asMaxHeap(int[] a) {
    int n = a.length;
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(a, n, i);
    }
  }

  private static void heapify(int[] a, int n, int i) {
    // Time Complexity: O(log n)

    int root = i;
    int left = left(i);
    int right = right(i);

    // if left child is greater than root
    if (left < n && a[left] > a[root]) {
      root = left;
    }

    // if right child is greater than root
    if (right < n && a[right] > a[root]) {
      root = right;
    }

    // change root, if needed
    if (root != i) {
      swap(a, root, i);
      heapify(a, n, root);
    }
  }

  private static int left(int i) {
    return 2 * i + 1;
  }

  private static int right(int i) {
    return 2 * i + 2;
  }

  public static void main(String[] args) {
    int[] a = { 80, 10, 50, 1000, 70, 60, 90, 20, 30, 40, 0, -1000 };
    sort(a);
    print(a);
  }

}