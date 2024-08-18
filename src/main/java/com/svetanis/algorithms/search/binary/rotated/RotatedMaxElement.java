package com.svetanis.algorithms.search.binary.rotated;

import static java.util.Arrays.sort;

// if we reach to the point where a[mid] 
// is greater than both of its adjacent 
// elements a[mid - 1] and a[mid + 1],
// then a[mid] is the maximum element

// if a[mid] is greater than the next element 
// and smaller than the previous element then 
// max lies on left side of middle
// we are in decreasing second half of the array

// when a[mid] is greater than previous element 
// and smaller than next than the max lies on 
// right side of middle 
// we are in increasing first half of the array

public final class RotatedMaxElement {

  public static int max(int[] a) {
    int n = a.length;
    return max(a, 0, n - 1);
  }

  private static int max(int[] a, int left, int right) {
    // Time Complexity: O(log n)

    if (right < left) {
      return -1;
    }

    // base case 1: only one element
    if (left == right) {
      return a[left];
    }

    // base case 2: only two elements
    if (right == left + 1) {
      return (a[left] > a[right]) ? a[left] : a[right];
    }

    int mid = left + (right - left) / 2;

    // base case 3: a[mid] is greater than both of
    // its adjacent elements a[mid - 1] & a[mid + 1]
    if (a[mid] > a[mid + 1] && a[mid] > a[mid - 1]) {
      return a[mid];
    }

    if (a[mid] > a[mid + 1] && a[mid] < a[mid - 1]) {
      return max(a, left, mid - 1);
    } else {
      return max(a, mid + 1, right);
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 30, 40, 50, 60, 70, 23, 20 };
    sort(a);
    System.out.println(max(a));
  }
}