package com.svetanis.algorithms.search.binary.unbounded;

import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.binarySearch;

import com.svetanis.java.base.Pair;

public final class BinarySearchInfiniteArray {

  public static int binary(int[] a, int x) {
    // O(log p), p is position of element to be search
    Pair<Integer, Integer> pair = findBounds(a, x);
    return binarySearch(a, pair.getLeft(), pair.getRight(), x);
  }

  private static Pair<Integer, Integer> findBounds(int[] a, int x) {
    int low = 0;
    int high = 1;
    int val = a[0];

    // find the high bounds
    while (val < x) {
      low = high; // store previous high
      high = 2 * high; // double high index
      val = a[high]; // update new val
    }
    return Pair.build(low, high);
  }

  public static void main(String[] args) {
    int[] a = { 3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170 };
    System.out.println(binary(a, 10));
  }
}