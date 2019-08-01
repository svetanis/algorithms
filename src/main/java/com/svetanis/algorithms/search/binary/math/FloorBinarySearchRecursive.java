package com.svetanis.algorithms.search.binary.math;

public final class FloorBinarySearchRecursive {

  public static int floor(int[] a, int x) {
    if (x < a[0]) {
      return -1;
    }
    return floor(a, 0, a.length - 1, x);
  }

  private static int floor(int[] a, int low, int high, int x) {
    // O(log n)

    if (low > high) {
      return -1;
    }
    
    if (x >= a[high]) {
      return high;
    }
    
    int mid = low + (high - low) / 2;

    if (a[mid] == x) {
      return mid;
    } 
    
    if (mid > 0 && a[mid - 1] <= x && x < a[mid]) {
      return mid - 1;
    }
    
    if (x < a[mid]) {
      return floor(a, low, mid - 1, x);
    } else {
      return floor(a, mid + 1, high, x);
    }
  }
  
  public static void main(String[] args) {
    int[] a = { 1, 3, 8, 10, 10, 12, 19 };
    System.out.println(floor(a, 12));
  }
}
