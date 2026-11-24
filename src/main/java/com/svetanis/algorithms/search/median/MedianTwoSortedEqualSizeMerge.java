package com.svetanis.algorithms.search.median;

// Median of two sorted arrays of the same size, by merging.

// Given two sorted arrays a1 and a2, each of length n >= 1, return the
// median of the 2n values together: the average of the n-th and the
// (n+1)-th smallest. The return type is int, so the average is truncated
// toward zero. Values whose pairwise sum fits in an int, as the code assumes.

// There are 2n values, so the median is the average of the values at
// indexes n - 1 and n of the array that merging a1 and a2 would give.
// That array is never built: walk both arrays taking the smaller front,
// n + 1 times, remembering the last two values taken. If one array
// runs out first, all of it was below the other's first value, so that
// first value is the next one taken.

// Time: O(n) -- n + 1 steps of the merge.
// Space: O(1)

// Siblings:
//   MedianTwoSortedEqualSizeBinary -- binary search for the index of the
//     upper middle, O(log n); falls back to this file when ties hide it.
//   MedianTwoSortedEqualSizeCompare -- compares the two arrays' own
//     medians and halves both ranges; returns a double, O(log n).
//   MedianTwoSortedArraysKthElement, MedianTwoSortedArraysPartition -- arrays of any
//     sizes (LC 4).

public final class MedianTwoSortedEqualSizeMerge {

  public static int median(int[] a1, int[] a2) {
    int n = a1.length;

    int i = 0; // current index of a1
    int j = 0; // current index of a2
    int prev = -1;
    int next = -1;

    for (int count = 0; count <= n; ++count) { // WALK: n + 1 steps, to merged index n
      // all elements of a1 are smaller
      // than first element of a2
      if (i == n) { // EXHAUSTED: a1 is used up
        prev = next;
        next = a2[0];
        break;
      }
      // all elements of a2 are smaller
      // than first element of a1
      if (j == n) { // EXHAUSTED: a2 is used up
        prev = next;
        next = a1[0];
        break;
      }

      if (a1[i] < a2[j]) { // TAKE: the smaller front
        prev = next; // store the prev median
        next = a1[i];
        i++;
      } else {
        prev = next; // store the prev median
        next = a2[j];
        j++;
      }
    }
    return (prev + next) / 2; // AVERAGE: merged indexes n - 1 and n, truncated
  }

  public static void main(String[] args) {
    int a1[] = { 1, 12, 15, 26, 38 };
    int a2[] = { 2, 13, 17, 30, 45 };
    System.out.println(median(a1, a2)); // 16
  }
}
