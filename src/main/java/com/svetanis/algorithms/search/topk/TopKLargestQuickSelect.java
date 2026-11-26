package com.svetanis.algorithms.search.topk;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

// The k Largest Numbers, by Quickselect
//
// Takes an array of n numbers and k, 1 <= k <= n. Returns the k largest, each copy of a
// repeated number counted on its own, in partition order. The input array is reordered.
//
// The k largest, in partition order. A random pivot splits the stretch into three blocks:
// larger than the pivot on the left, equal to it in the middle, smaller on the right. Once
// the kth largest is placed, everything before it is larger or equal, so the first k
// positions of the array are the answer. A k inside the equal block ends the search in one
// pass, so an array of equal numbers takes one pass.
//
// Siblings -- four ways to take the k largest or smallest in this package:
//   TopKLargestMinHeap      a min-heap capped at k; O(n log k), heap order
//   TopKLargestTreeSet      the same cap with a TreeSet of (value, index); O(n log k), ascending
//   TopKLargestQuickSelect  partition until the kth largest is in place           (this file)
//   TopKSmallestHeapAll     the k SMALLEST: every number into a min-heap, then k polls;
//                           O(n log n), ascending

public final class TopKLargestQuickSelect {
  // Time Complexity: O(n) average -- each partition is linear in its stretch, and a random
  // pivot shrinks the stretch by a constant fraction on average; O(n^2) worst case
  // Space Complexity: O(log n) average, the recursion, plus the k returned

  private static final Random GENERATOR = new Random();

  public static List<Integer> select(int[] a, int k) {
    int n = a.length;
    int last = select(a, 0, n - 1, k); // SELECT: index of the kth largest, once placed
    return Arrays.stream(a, 0, last + 1).boxed().toList(); // REPORT the prefix, unordered
  }

  private static int select(int[] a, int left, int right, int k) {
    Block equal = partition(a, left, right, randomIndex(left, right)); // SPLIT around a random pivot
    int larger = equal.first() - left; // COUNT: numbers larger than the pivot
    int notSmaller = equal.last() - left + 1; // COUNT: the larger ones plus the pivot's copies
    if (k <= larger) {
      return select(a, left, equal.first() - 1, k); // LEFT: the kth largest is larger than the pivot
    }
    if (k <= notSmaller) {
      return left + k - 1; // FOUND: the kth largest sits inside the equal block
    }
    return select(a, equal.last() + 1, right, k - notSmaller); // RIGHT: skip the notSmaller already placed
  }

  // larger elements to the left, equal ones in the middle, smaller ones to the right
  private static Block partition(int[] a, int left, int right, int index) {
    int pivot = a[index];
    int larger = left; // WRITE: where the next larger number goes
    int current = left; // READ: the number being placed
    int smaller = right; // WRITE: where the next smaller number goes, from the right end
    while (current <= smaller) {
      if (a[current] > pivot) {
        swap(a, larger, current); // LARGER to the left block
        larger++;
        current++;
      } else if (a[current] < pivot) {
        swap(a, current, smaller); // SMALLER to the right block; the swapped-in number is unread
        smaller--;
      } else {
        current++; // EQUAL stays in the middle
      }
    }
    return new Block(larger, smaller); // REPORT the equal block's bounds
  }

  private static int randomIndex(int left, int right) {
    return GENERATOR.nextInt(right - left + 1) + left; // PICK: right included
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
    int[] a = { 6, 7, 3, 1, 5, 9, 11 };
    System.out.println(select(a, 4)); // 11, 9, 7, 6 in some order

    int[] equal = new int[100_000];
    Arrays.fill(equal, 7);
    System.out.println(select(equal, 100_000).size()); // 100000
  }
}
