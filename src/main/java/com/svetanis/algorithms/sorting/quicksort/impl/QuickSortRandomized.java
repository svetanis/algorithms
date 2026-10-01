package com.svetanis.algorithms.sorting.quicksort.impl;

import java.util.Arrays;
import java.util.Random;

// A random pivot splits the range into three blocks: smaller than the pivot on the left,
// equal to it in the middle, larger on the right. The equal block is already in its final
// place, so only the two outer blocks are sorted next. An array of equal numbers is one
// pass.

public final class QuickSortRandomized {
  // Time Complexity: O(n log n) average
  // Space Complexity: O(log n) average, the recursion

  private static final Random GENERATOR = new Random();

  public static void sort(int[] a, int left, int right) {
    if (left < right) {
      Block equal = partition(a, left, right, randomIndex(left, right));
      sort(a, left, equal.first() - 1);
      sort(a, equal.last() + 1, right);
    }
  }

  // smaller elements to the left, equal ones in the middle, larger ones to the right
  private static Block partition(int[] a, int left, int right, int index) {
    int pivot = a[index];
    int smaller = left; // where the next smaller number goes
    int current = left;
    int larger = right; // where the next larger number goes, from the right end
    while (current <= larger) {
      if (a[current] < pivot) {
        swap(a, smaller, current);
        smaller++;
        current++;
      } else if (a[current] > pivot) {
        swap(a, current, larger);
        larger--;
      } else {
        current++;
      }
    }
    return new Block(smaller, larger);
  }

  private static int randomIndex(int left, int right) {
    return GENERATOR.nextInt(right - left + 1) + left; // right included
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  // the equal block, first and last index inclusive
  private record Block(int first, int last) {
  }

  public static void main(String[] args) {
    int[] a = { 1000, 80, 10, 50, 70, 60, 90, 20, 30, 40, 0, -1000 };
    sort(a, 0, a.length - 1);
    System.out.println(Arrays.toString(a)); // [-1000, 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 1000]

    int[] equal = new int[100_000];
    Arrays.fill(equal, 7);
    sort(equal, 0, equal.length - 1);
    System.out.println(equal[0] + " " + equal[equal.length - 1]); // 7 7
  }
}
