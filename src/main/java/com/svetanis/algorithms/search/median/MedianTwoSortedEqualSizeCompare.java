package com.svetanis.algorithms.search.median;

import static java.lang.Math.max;
import static java.lang.Math.min;

// Median of two sorted arrays of the same size, by comparing their medians.

// Given two sorted arrays a1 and a2, each of length n, return the median
// of the 2n values together, as a double. Two empty arrays throw.
// Values whose pairwise sum fits in an int, as the code assumes.

// Compare the two arrays' own medians, median1 and median2. If they are
// equal, that value is the answer. If median1 < median2, the answer lies
// between them: drop the lower part of a1 and the upper part of a2. The
// same number of values goes from below the answer as from above it, so
// the answer stays the median of what is kept -- which is why the two
// kept ranges must have the same length. Recurse until a range holds 1 or
// 2 values, which are answered directly.

// Time: O(log n) -- each call keeps about half of each range.
// Space: O(log n) -- the recursion depth.

// Siblings:
//   MedianSortedArray -- median(a, start, end), the helper this calls for
//     each array's own median.
//   MedianTwoSortedEqualSizeBinary -- binary search for the index of the
//     upper middle; returns an int.
//   MedianTwoSortedEqualSizeMerge -- walks both arrays to the middle, O(n).
//   MedianTwoSortedArraysKthElement, MedianTwoSortedArraysPartition -- arrays of any
//     sizes (LC 4).

public final class MedianTwoSortedEqualSizeCompare {

  private static final String EMPTY = "median of two empty arrays is undefined";

  public static double median(int[] a1, int[] a2) {
    int n = a1.length;
    return median(a1, 0, n - 1, a2, 0, n - 1);
  }

  private static double median(int[] a1, int s1, int e1, int[] a2, int s2, int e2) {
    int n = e1 - s1 + 1;

    if (n <= 0) { // EMPTY: only two empty arrays get here
      throw new IllegalArgumentException(EMPTY);
    }

    if (n == 1) { // ONE: the average of the two values
      return (double) (a1[s1] + a2[s2]) / 2;
    }

    if (n == 2) { // TWO: the larger front and the smaller back are the middles
      return (double) (max(a1[s1], a2[s2]) + min(a1[e1], a2[e2]))/2;
    }

    // get the median of the first and second arrays
    double median1 = MedianSortedArray.median(a1, s1, e1);
    double median2 = MedianSortedArray.median(a2, s2, e2);

    if (median1 == median2) { // EQUAL: both medians are the answer
      return median1;
    }

    // if median1 < median2 then median must
    // exist in a1[m1 ...] and a2[... m2]

    // BOTH sub-ranges must keep the same length, or the next call's
    // n is wrong and every comparison below it is off. for even n
    // that length is n/2 + 1, so the kept tail of the second array
    // ends at s + n/2 -- not s + n/2 - 1, which is one short
    if (median1 < median2) { // KEEP: a1's upper part, a2's lower part
      if (n % 2 == 0) {
        return median(a1, s1 + n / 2 - 1, e1, a2, s2, s2 + n / 2);
      }
      return median(a1, s1 + n / 2, e1, a2, s2, s2 + n / 2);
    }
    // if median1 > median2 than median must
    // exist in a1[... m1] and a2[m2 ...]
    else { // KEEP: a2's upper part, a1's lower part
      if (n % 2 == 0) {
        return median(a2, s2 + n / 2 - 1, e2, a1, s1, s1 + n / 2);
      }
      return median(a2, s2 + n / 2, e2, a1, s1, s1 + n / 2);
    }
  }

  public static void main(String[] args) {
    int a1[] = { 1, 2, 3, 6 };
    int a2[] = { 4, 6, 8, 10 };
    System.out.println(median(a1, a2)); // 5.0
  }
}
