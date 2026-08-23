package com.svetanis.algorithms.search.binary.bitonic;

// Find the minimum value in a given V-shaped (valley) array.
// An array is V-shaped if it is strictly decreasing and then
// strictly increasing -- for any index i, arr[i] != arr[i+1].

// NOTE the shape: this is the MIRROR of BitonicMaxElement, which
// rises then falls. The minimum of a rises-then-falls array is not
// an interior point at all -- it sits at one of the two ENDS, and
// comparing them is one operation, so there is nothing to search.
// A valley has an interior turning point, and that is what makes
// the binary search below both possible and necessary.

public final class BitonicMinElement {

  public static int min(int[] a) {
    // Time Complexity: O(log n)

    int start = 0;
    int end = a.length - 1;
    while (start < end) {
      int mid = start + (end - start) / 2;
      // a[mid] > a[mid + 1] -- still descending, the valley is to the right
      if (a[mid] > a[mid + 1]) {
        start = mid + 1;
      } else {
        // ascending, so mid may itself be the valley -- keep it
        end = mid;
      }
    }
    return start;
  }

  public static void main(String[] args) {
    int[] a1 = { 9, 7, 5, 2, 4, 6, 10 };
    System.out.println(min(a1)); // 3

    int[] a2 = { 10, 8, 6, 5, 2, 12, 14 };
    System.out.println(min(a2)); // 4

    int[] a3 = { 12, 8, 3, 1 };
    System.out.println(min(a3)); // 3 -- no ascending half

    int[] a4 = { 1, 3, 8, 12 };
    System.out.println(min(a4)); // 0 -- no descending half

    int[] a5 = { 2, 1 };
    System.out.println(min(a5)); // 1

  }
}
