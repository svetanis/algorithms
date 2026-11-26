package com.svetanis.algorithms.search.kthelement;

import java.util.Arrays;
import java.util.Random;

// Kth smallest element in an unsorted array (no LeetCode number; the mirror of LC 215)

// Given an integer array a and k (1 <= k <= a.length), return the kth smallest
// element -- the one at position k if the array were sorted from smallest to
// biggest. Duplicates count separately. Rearranges a.

// A random pivot splits the stretch into three blocks: smaller than the pivot on the
// left, equal to it in the middle, larger on the right. k counts from the left end of
// the stretch, so the three block sizes say at once which block holds the answer. A k
// inside the equal block is found in one pass, so an array of equal numbers takes one
// pass.

// Time: O(n) average -- each call is linear in its stretch, and a random pivot
//   cuts the stretch by a constant fraction on average; O(n^2) worst, with
//   every pivot unlucky
// Space: O(log n) average, the recursion -- one call per stretch

// Siblings, the kth smallest three ways:
//   KthSmallestMaxHeapPQ             -- max-heap of k, O(n log k)
//   KthSmallestMinHeapPQ             -- min-heap of all n, polled k - 1 times
//   KthSmallestQuickSelectRandomized -- this file: quickselect, three groups
// KthLargestQuickSelectRandomized (215) is the mirror: < and > swapped in the partition.

public final class KthSmallestQuickSelectRandomized {

  private static final Random GENERATOR = new Random();

  public static int select(int[] a, int k) {
    int n = a.length;
    return select(a, 0, n - 1, k); // SEARCH the whole array for rank k
  }

  private static int select(int[] a, int left, int right, int k) {
    Block equal = partition(a, left, right, randomIndex(left, right)); // PARTITION around a random pivot
    int smaller = equal.first() - left; // COUNT: numbers smaller than the pivot
    int notLarger = equal.last() - left + 1; // COUNT: the smaller ones plus the pivot's copies
    if (k <= smaller) {
      return select(a, left, equal.first() - 1, k); // KEEP the smaller block: rank k is in it
    }
    if (k <= notLarger) {
      return a[equal.first()]; // REPORT: rank k lands in the equal block
    }
    return select(a, equal.last() + 1, right, k - notLarger); // KEEP the larger block: k is a rank, so drop the notLarger passed
  }

  // smaller elements to the left, equal ones in the middle, larger ones to the right
  private static Block partition(int[] a, int left, int right, int index) {
    int pivot = a[index];
    int smaller = left; // SLOT: where the next smaller number goes
    int current = left; // READ: the next number to place
    int larger = right; // SLOT: where the next larger number goes, from the right end
    while (current <= larger) { // WALK until the unread middle is empty
      if (a[current] < pivot) {
        swap(a, smaller, current); // MOVE it into the smaller block, on the left
        smaller++;
        current++;
      } else if (a[current] > pivot) {
        swap(a, current, larger); // MOVE it into the larger block, on the right
        larger--; // the number swapped in is unread, so current stays
      } else {
        current++; // KEEP an equal number where it is
      }
    }
    return new Block(smaller, larger); // REPORT the equal block
  }

  private static int randomIndex(int left, int right) {
    return GENERATOR.nextInt(right - left + 1) + left; // PICK: right included
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i]; // SWAP through a temporary
    a[i] = a[j];
    a[j] = temp;
  }

  // the equal block, first and last index inclusive
  private record Block(int first, int last) {
  }

  public static void main(String[] args) {
    int[] a = { 12, 3, 5, 7, 4, 19, 26 };
    System.out.println(select(a, 4)); // 7

    int[] a2 = { 8, 1, 6, 4, 0, 3, 9, 5 };
    System.out.println(select(a2, 5)); // 5

    int[] equal = new int[100_000];
    Arrays.fill(equal, 7);
    System.out.println(select(equal, 1)); // 7
  }
}
