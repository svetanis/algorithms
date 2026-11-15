package com.svetanis.algorithms.sorting.quicksort.impl;

import java.util.Arrays;
import java.util.Random;

// Quicksort with a three-way partition, the Dutch national flag -- not a LeetCode submission
// (LeetCode 912, Sort an Array, is the same task; LeetCode 75, Sort Colors, is the partition alone
// with three values).
//
// Input: a, an array of ints. Sorts a ascending, in place. On LeetCode 912: 1 <= a.length <=
// 5 * 10^4, -5 * 10^4 <= a[i] <= 5 * 10^4.
//
// The one idea: take the pivot a[left] and split the range into three blocks: smaller than the
// pivot on the left, equal to it in the middle, larger on the right. The equal block is already in
// its final place, because everything to its left is smaller and everything to its right is
// larger. So only the two outer blocks are sorted next, each by the same method, and an array of
// equal numbers is one pass. Taking a[left] as the pivot is bad on sorted input, so the array is
// shuffled once first, which makes every order equally likely and leaves no fixed input that keeps
// putting the pivot at one end.
//
// Time: O(n log n) on average -- after the shuffle the pivot is random, so the blocks are balanced
// on average; the worst case is O(n^2), with vanishing probability.
// Space: O(log n) on average -- the recursion.
//
// Siblings: QuickSortRandomized is the same split with a random pivot on every call instead of one
// shuffle. Partition holds the same split as a reference.

public final class ThreeWayQuickSortNationalFlag {

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
    sort(a, left, equal.first() - 1); // LEFT: the smaller block
    sort(a, equal.last() + 1, right); // RIGHT: the larger block
  }

  // smaller to the left, equal in the middle, larger to the right, around the pivot a[left]
  private static Block partition(int[] a, int left, int right) {
    int pivot = a[left];
    int smaller = left; // where the next smaller number goes
    int current = left;
    int larger = right; // where the next larger number goes, from the right end
    while (current <= larger) {
      if (a[current] < pivot) {
        swap(a, smaller, current); // SMALLER: to the left block
        smaller++;
        current++;
      } else if (a[current] > pivot) {
        swap(a, current, larger); // LARGER: to the right block; current not advanced: the swapped-in value is unread
        larger--;
      } else {
        current++; // EQUAL: stays in the middle
      }
    }
    return new Block(smaller, larger);
  }

  // Fisher-Yates: every order equally likely
  private static void shuffle(int[] a) {
    for (int i = a.length - 1; i > 0; i--) {
      int random = GENERATOR.nextInt(i + 1);
      swap(a, i, random); // SHUFFLE: a random one of a[0..i] goes to index i
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
