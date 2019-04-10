package com.svetanis.algorithms.search.binary.math;

// if x is greater than array[mid], 
// then either array[mid + 1] is ceiling of x
// or ceiling lies in array[mid + 1 ... high]

// if x is smaller than array[mid], 
// then either array[mid] is ceiling of x
// or ceiling lies in array[low ... mid - 1]

public final class CeilingSortedArrayBinary {

  public static int ceil(int[] a, int x) {
    // Time Complexity: O(log n)

    int n = a.length;
    int low = 0;
    int high = n - 1;
    return ceil(a, low, high, x);
  }

  public static int ceil(int[] a, int low, int high, int x) {

    if (x <= a[low]) {
      return low;
    }

    if (x > a[high]) {
      return -1;
    }

    int mid = low + (high - low) / 2;
    
    if (a[mid] == x) {
      return mid;
    } else if (x > a[mid]) {
      if (mid + 1 <= high && x <= a[mid + 1]) {
        return mid + 1;
      } else {
        return ceil(a, mid + 1, high, x);
      }
    } else {
      if (mid - 1 >= low && x > a[mid - 1]) {
        return mid;
      } else {
        return ceil(a, low, mid - 1, x);
      }
    }
  }

  public static void main(String[] args) {
    int[] a = { 1, 2, 8, 10, 10, 12, 19 };
    System.out.println(ceil(a, 3));
  }
}