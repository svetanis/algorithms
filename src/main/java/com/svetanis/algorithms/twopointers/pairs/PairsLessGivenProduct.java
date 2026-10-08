package com.svetanis.algorithms.twopointers.pairs;

// Count Pairs With Product Less Than k
//
// Input: an array of integers sorted in non-decreasing order, and k.
// Return: how many pairs of positions (i, j), i < j, have a[i] * a[j] < k.
// Convention the code follows: the values are not negative. A negative value breaks the
// order the loop relies on -- a larger partner can make the product smaller -- and the
// count comes out wrong: {-3, -1, 3, 3, 4} with k = -8 gives 4, and only 3 pairs
// qualify. The source statement would settle whether negatives can occur.
//
// The one idea: if a[left] * a[right] < k, then a[left] times any number between them
// is below k as well, because those numbers are at most a[right]: count all
// right - left pairs in one step and drop left. Otherwise a[right] is too big even with
// the smallest partner left: drop right.
//
// Sibling: PairsLessGivenSum -- the same loop for sums, which needs no sign condition
//
// Time: O(n) -- every step drops one number.
// Space: O(1).

public final class PairsLessGivenProduct {

  public static int count(int[] a, int k) {
    int left = 0;                   // START: the smallest number
    int right = a.length - 1;       // START: the largest number
    int count = 0;
    while (left < right) {          // STOP: a pair needs two different positions
      if (a[left] * a[right] < k) { // COMPARE
        count += right - left;      // COUNT a[left] with every number up to a[right]
        left++;                     // DROP LEFT: all its pairs are counted
      } else {
        right--;                    // DROP RIGHT: too big even with the smallest
      }
    }
    return count;
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 7, 9, 10, 11 };
    System.out.println(count(a1, 7)); // 1

    int[] a2 = { 1, 2, 3, 4, 5, 6, 7, 8 };
    System.out.println(count(a2, 7)); // 6
  }
}
