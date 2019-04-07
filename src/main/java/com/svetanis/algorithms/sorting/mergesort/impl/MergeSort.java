package com.svetanis.algorithms.sorting.mergesort.impl;

import static com.svetanis.java.base.utils.Print.print;
import static java.lang.System.arraycopy;

public final class MergeSort {

  public static void sort(int[] a) {
    // Time Complexity: O(n log n)
    
    int n = a.length;
    int[] aux = new int[n];
    sort(a, aux, 0, n - 1);
  }

  private static void sort(int[] a, int[] aux, int low, int high) {
    if (low < high) {
      int mid = low + (high - low) / 2;
      sort(a, aux, low, mid);
      sort(a, aux, mid + 1, high);
      merge(a, aux, low, mid, high);
    }
  }

  private static void merge(int[] a, int[] aux, int low, int mid, int high) {
    // copy to aux[]
    arraycopy(a, low, aux, low, high - low + 1);

    int left = low;
    int right = mid + 1;
    int current = low;

    while (left <= mid && right <= high) {
      if (aux[left] <= aux[right]) {
        a[current] = aux[left++];
      } else {
        a[current] = aux[right++];
      }
      current++;
    }

    // copy the rest of left part of temp into array
    int remaining = mid - left;
    for (int i = 0; i <= remaining; i++) {
      a[current + i] = aux[left + i];
    }
  }

  public static void main(String[] args) {
    int[] a = { 1000, 80, 10, 50, 70, 60, 90, 20, 30, 40, 0, -1000 };
    sort(a);
    print(a);
  }
}