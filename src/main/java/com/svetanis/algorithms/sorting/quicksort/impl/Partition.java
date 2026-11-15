package com.svetanis.algorithms.sorting.quicksort.impl;

import java.util.Arrays;
import java.util.Random;

// Partition -- not a LeetCode submission: a reference class, called by no other file, that holds
// the partition methods quicksort and quickselect are built from. It stays as the one place where
// the two-group and three-group versions sit side by side.
//
// Input: a, an array of ints, and left and right, the first and last index of the range
// (inclusive). Each method rearranges a[left..right] in place around a pivot. LeetCode problems
// that use these splits: 912 (Sort an Array), 215 (Kth Largest Element in an Array), 75 (Sort
// Colors).
//
// Partitions around a pivot, in two groups or three. Each puts the pivot where it belongs
// in sorted order and returns where that is.
//
// Two groups: the pivot's index. Every copy of the pivot value stays on one side, so an
// array of equal numbers moves the pivot to one end and a caller that recurses on the rest
// removes one number per call.
//
// Three groups: the block of values equal to the pivot. A caller skips the whole block, so
// an array of equal numbers is one call.
//
// Which one to use:
//   direction  smaller to the left: the rank counts from the bottom -- kth smallest,
//              k closest, ascending sort. Larger to the left (the reverse ones): the rank
//              counts from the top -- kth largest, top k.
//   groups     three when values can repeat, which is any input you do not control:
//              duplicates cost nothing extra. Two only when values are known distinct; it
//              returns a single index, which is simpler to recurse on.
//   pivot      call a randomized one, or pass a random index to a three-group one, so
//              that sorted input does not put the pivot at one end every time. The plain
//              two-group ones split around a[right], whatever the caller put there.
//
// The one idea: one pass with a boundary index. Everything before the boundary is already on the
// correct side of the pivot; the loop looks at one number at a time and, if it belongs on the
// near side, swaps it to the boundary and moves the boundary on. Three groups keep a second
// boundary from the right end, for the numbers that belong on the far side, and leave the equal
// ones where they are in between.
//
// Time: O(right - left + 1) per call, every method -- one pass over the range
// Space: O(1) -- a few indexes, the swap done in place.
//
// Siblings: QuickSortRandomized and ThreeWayQuickSortNationalFlag hold private copies of the
// three-group split inside their sorts; QuickSortSubmit uses a Hoare split of its own.

public final class Partition {

  private static final Random GENERATOR = new Random();

  public static int randomizedPartition(int[] a, int left, int right) {
    int random = randomIndex(left, right);
    swap(a, random, right); // the random pivot goes where partition reads it
    return partition(a, left, right);
  }

  public static int randomizedReversePartition(int[] a, int left, int right) {
    int random = randomIndex(left, right);
    swap(a, random, right); // the random pivot goes where reversePartition reads it
    return reversePartition(a, left, right);
  }

  // smaller elements to the left, around the pivot a[right]
  // i is the LAST slot holding a value <= pivot, so it starts one before the range: left - 1
  public static int partition(int[] a, int left, int right) {
    int i = left - 1;
    int pivot = a[right];
    for (int j = left; j < right; ++j) {
      if (a[j] <= pivot) {
        i++; // step first, then fill
        swap(a, i, j); // NEAR SIDE: a number <= pivot joins the left block
      }
    }
    // move pivot to its final place, one past the last smaller-or-equal value
    swap(a, i + 1, right); // PLACE: the pivot
    return i + 1;
  }

  // larger elements to the left, around the pivot a[right]
  // i is the NEXT slot for a value > pivot, so it starts at the range's first slot: left
  public static int reversePartition(int[] a, int left, int right) {
    int i = left;
    int pivot = a[right];
    for (int j = left; j < right; ++j) {
      if (a[j] > pivot) {
        swap(a, j, i); // fill first, then step; NEAR SIDE: a number > pivot joins the left block
        i++;
      }
    }
    // move pivot to its final place, the slot i points at
    swap(a, right, i); // PLACE: the pivot
    return i;
  }

  // smaller elements to the left, equal ones in the middle, larger ones to the right
  public static Block threeWayPartition(int[] a, int left, int right, int index) {
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
        swap(a, current, larger); // LARGER: to the right block; current not advanced: the value swapped in is unread
        larger--;
      } else {
        current++; // EQUAL: stays in the middle
      }
    }
    return new Block(smaller, larger);
  }

  // larger elements to the left, equal ones in the middle, smaller ones to the right
  public static Block reverseThreeWayPartition(int[] a, int left, int right, int index) {
    int pivot = a[index];
    int larger = left; // where the next larger number goes
    int current = left;
    int smaller = right; // where the next smaller number goes, from the right end
    while (current <= smaller) {
      if (a[current] > pivot) {
        swap(a, larger, current); // LARGER: to the left block
        larger++;
        current++;
      } else if (a[current] < pivot) {
        swap(a, current, smaller); // SMALLER: to the right block; current not advanced: the value swapped in is unread
        smaller--;
      } else {
        current++; // EQUAL: stays in the middle
      }
    }
    return new Block(larger, smaller);
  }

  private static int randomIndex(int left, int right) {
    return GENERATOR.nextInt(right - left + 1) + left; // right included
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  // the block of values equal to the pivot, first and last index inclusive
  public record Block(int first, int last) {
  }

  public static void main(String[] args) {
    int[] a = { 3, 1, 3, 2, 3 };
    int p = partition(a, 0, a.length - 1);
    System.out.println(Arrays.toString(a) + " " + p); // [3, 1, 3, 2, 3] 4

    int[] b = { 2, 3, 1, 2 };
    Block block = threeWayPartition(b, 0, b.length - 1, 0);
    System.out.println(Arrays.toString(b) + " " + block); // [1, 2, 2, 3] Block[first=1, last=2]
  }

}
