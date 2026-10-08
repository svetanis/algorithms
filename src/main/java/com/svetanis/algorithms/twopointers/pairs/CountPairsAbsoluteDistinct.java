package com.svetanis.algorithms.twopointers.pairs;

// Count Distinct Absolute Values in a Sorted Array
//
// Input: an array sorted in non-decreasing order; it may hold negatives, zeros and
// repeated values.
// Return: how many different absolute values it holds -- {-3, -2, 0, 3, 4, 5} holds
// 0, 2, 3, 4 and 5, so 5. The class name says pairs; the answer counts values.
//
// The one idea: start from n, one per number, and subtract one for every number whose
// absolute value is already counted. A repeated value sits next to its copy in a
// sorted array, so each end first skips its own copies. After that, x and -x are the
// only other way to share an absolute value, and that is the converging loop of the
// two-sum family with target 0. A sum of 0 subtracts one and drops both ends. A
// negative sum means |a[left]| is bigger than every number left on the right, so
// -a[left] is not there: a[left] keeps its count and is dropped. The mirror holds
// for a positive sum.
//
// Siblings: PairsPosNegHashing and PairsPosNegSet -- list the pairs {x, -x} of an
//   unsorted array instead of counting the values.
//
// Time: O(n) -- every step moves left or right inward.
// Space: O(1).

public final class CountPairsAbsoluteDistinct {

  public static int count(int[] a) {
    int n = a.length;
    int count = n;                  // START: every number counted once
    int left = 0;                   // START: the most negative number
    int right = n - 1;              // START: the largest number
    while (left < right) {          // STOP: at most one number left
      while (left != right && a[left] == a[left + 1]) {
        count--;                    // SKIP: a copy on the left repeats a counted value
        left++;
      }
      while (left != right && a[right] == a[right - 1]) {
        count--;                    // SKIP: a copy on the right repeats a counted value
        right--;
      }
      if (left == right) {
        return count;               // RETURN: one number left, nothing to pair it with
      }
      int sum = a[left] + a[right]; // COMPARE: x and -x add up to 0
      if (sum == 0) {
        count--;                    // FOUND: x and -x share one absolute value
        left++;
        right--;
      } else if (sum < 0) {
        left++;                     // DROP LEFT: no -a[left] is left on the right
      } else {
        right--;                    // DROP RIGHT: no -a[right] is left on the left
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a1 = { -3, -2, 0, 3, 4, 5 };
    System.out.println(count(a1)); // 5

    int[] a2 = { -1, -1, -1, -1, 0, 1, 1, 1, 1 };
    System.out.println(count(a2)); // 2

    int[] a3 = { -1, -1, -1, -1, 0 };
    System.out.println(count(a3)); // 2

    int[] a4 = { 0, 0, 0 };
    System.out.println(count(a4)); // 1

    int[] a5 = { -2, -1, 0, 1, 1 };
    System.out.println(count(a5)); // 3
  }
}
