package com.svetanis.algorithms.sorting.quicksort.impl;

import java.util.Arrays;
import java.util.Random;

// Quicksort with a random pivot and a three-block split -- not a LeetCode submission (LeetCode
// 912, Sort an Array, is the same task; see QuickSortSubmit).
//
// Input: a, an array of ints, and left and right, the first and last index to sort (inclusive).
// Sorts a[left..right] ascending, in place. On LeetCode 912: 1 <= a.length <= 5 * 10^4,
// -5 * 10^4 <= a[i] <= 5 * 10^4.
//
// The one idea: a random pivot splits the range into three blocks: smaller than the pivot on the
// left, equal to it in the middle, larger on the right. The equal block is already in its final
// place, because everything to its left is smaller and everything to its right is larger. So only
// the two outer blocks are sorted next, each by the same method. An array of equal numbers is one
// pass, since the whole range is the equal block. The pivot is random so that no fixed input,
// sorted for instance, keeps splitting off one number at a time.
//
// Time: O(n log n) on average -- a random pivot gives balanced blocks on average; the worst case
// is O(n^2), with vanishing probability.
// Space: O(log n) on average -- the recursion.
//
// Siblings: ThreeWayQuickSortNationalFlag is the same split with the pivot a[left] after one
// shuffle of the array. Partition holds the same split as a reference. QuickSortSubmit splits in
// two with a Hoare partition and is the 912 submission.

public final class QuickSortRandomized {

  private static final Random GENERATOR = new Random();

  public static void sort(int[] a, int left, int right) {
    if (left < right) {
      Block equal = partition(a, left, right, randomIndex(left, right));
      sort(a, left, equal.first() - 1); // LEFT: the smaller block
      sort(a, equal.last() + 1, right); // RIGHT: the larger block
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
        swap(a, smaller, current); // SMALLER: to the left block
        smaller++;
        current++;
      } else if (a[current] > pivot) {
        swap(a, current, larger); // LARGER: to the right block; the swapped-in value is unread
        larger--;
      } else {
        current++; // EQUAL: stays in the middle
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

    int[] b = { 5, 1, 1, 2, 0, 0 };
    sort(b, 0, b.length - 1);
    System.out.println(Arrays.toString(b)); // [0, 0, 1, 1, 2, 5]

    int[] equal = new int[100_000];
    Arrays.fill(equal, 7);
    sort(equal, 0, equal.length - 1);
    System.out.println(equal[0] + " " + equal[equal.length - 1]); // 7 7
  }
}
