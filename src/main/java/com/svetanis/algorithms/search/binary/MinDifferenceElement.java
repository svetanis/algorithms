package com.svetanis.algorithms.search.binary;

// Given an array of numbers sorted in ascending order, 
// find the element in the array that has the min diff with the given ‘key’.

public final class MinDifferenceElement {

  public static int minDiff(int[] a, int key) {
    int n = a.length;

    if (key < a[0]) {
      return a[0];
    }

    if (key > a[n - 1]) {
      return a[n - 1];
    }

    int start = 0;
    int end = n - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (key < a[mid]) {
        end = mid - 1;
      } else if (key > a[mid]) {
        start = mid + 1;
      } else {
        return a[mid];
      }
    }

    if (a[start] - key < key - a[end]) {
      return a[start];
    }
    return a[end];
  }

  public static void main(String[] args) {
    int[] a1 = { 4, 6, 10 };
    System.out.println(minDiff(a1, 7));
    System.out.println(minDiff(a1, 4));
    System.out.println(minDiff(a1, 17));

    int[] a2 = { 1, 3, 8, 10, 15 };
    System.out.println(minDiff(a2, 12));
  }
}