package com.svetanis.algorithms.sorting.quicksort.impl;

import java.util.Arrays;
import java.util.Random;

// Quicksort with a three-way partition, the Dutch national flag: smaller than the pivot on
// the left, equal to it in the middle, larger on the right. The equal block is already in
// its final place, so only the two outer blocks are sorted next, and an array of equal
// numbers is one pass. The array is shuffled once first, so that no fixed input, sorted
// for instance, keeps putting the first element's pivot at one end.

public final class ThreeWayQuickSortNationalFlag {
  // Time Complexity: O(n log n) average
  // Space Complexity: O(log n) average, the recursion

  private static final Random GENERATOR = new Random();

  public static void sort(int[] a) {
    shuffle(a); // once, instead of a random pivot on every call
    int n = a.length;
    sort(a, 0, n - 1);
  }

  private static void sort(int[] a, int left, int right) {
    if (right <= left) {
      return;
    }
    Block equal = partition(a, left, right);
    sort(a, left, equal.first() - 1);
    sort(a, equal.last() + 1, right);
  }

  // smaller to the left, equal in the middle, larger to the right, around the pivot a[left]
  private static Block partition(int[] a, int left, int right) {
    int pivot = a[left];
    int smaller = left; // where the next smaller number goes
    int current = left;
    int larger = right; // where the next larger number goes, from the right end
    while (current <= larger) {
      if (a[current] < pivot) {
        swap(a, smaller, current);
        smaller++;
        current++;
      } else if (a[current] > pivot) {
        swap(a, current, larger); // current not advanced: the value swapped in is unread
        larger--;
      } else {
        current++;
      }
    }
    return new Block(smaller, larger);
  }

  // Fisher-Yates: every order equally likely
  private static void shuffle(int[] a) {
    for (int i = a.length - 1; i > 0; i--) {
      int random = GENERATOR.nextInt(i + 1);
      swap(a, i, random);
    }
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
    int[] a = { 4, 9, 2, 4, 1, 9, 4, 2, 9, 4, 2, 1, 2 };
    sort(a);
    System.out.println(Arrays.toString(a)); // [1, 1, 2, 2, 2, 2, 4, 4, 4, 4, 9, 9, 9]

    int[] equal = new int[100_000];
    Arrays.fill(equal, 7);
    sort(equal);
    System.out.println(equal[0] + " " + equal[equal.length - 1]); // 7 7
  }
}
