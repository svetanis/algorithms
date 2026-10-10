package com.svetanis.algorithms.twopointers.triplet;

// Maximize x * a[i] + y * a[j] + z * a[k] with i <= j <= k
//
// Input: a non-empty array of integers and three weights x, y, z of any sign.
// Return: the largest value of x * a[i] + y * a[j] + z * a[k] over positions
// i <= j <= k; a position may be used more than once.
//
// The one idea: FIX the middle position j. The best first term is then the largest
// x * a[i] with i <= j -- a running maximum from the left -- and the best last term is the
// largest z * a[k] with k >= j -- a running maximum from the right. Precompute both, and one
// pass over j adds the three. The three choices do not interfere, because i <= j <= k is
// the only link between them.
//
// Time: O(n) -- three passes.
// Space: O(n) for the two arrays of best terms.

public final class MaximizeProduct {

  public static int maximize(int[] a, int x, int y, int z) {
    int n = a.length;
    int[] left = left(a, x);                      // left[j]: the best x * a[i], i <= j
    int[] right = right(a, z);                    // right[j]: the best z * a[k], k >= j
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {                 // FIX the middle position
      int value = left[i] + y * a[i] + right[i];  // COMPARE the best value through it
      max = Math.max(max, value);
    }
    return max;
  }

  private static int[] left(int[] a, int x) {
    int n = a.length;
    int[] left = new int[n];
    left[0] = x * a[0];
    for (int i = 1; i < n; i++) {
      left[i] = Math.max(left[i - 1], x * a[i]);  // RECORD the running maximum from the left
    }
    return left;
  }

  private static int[] right(int[] a, int z) {
    int n = a.length;
    int[] right = new int[n];
    right[n - 1] = z * a[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], z * a[i]); // RECORD the running maximum from the right
    }
    return right;
  }

  public static void main(String[] args) {
    int x = 1;
    int y = 2;
    int z = -3;
    int[] a = { -1, -2, -3, -4, -5 };
    System.out.println(maximize(a, x, y, z)); // 12
  }
}
