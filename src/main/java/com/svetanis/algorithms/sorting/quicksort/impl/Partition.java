package com.svetanis.algorithms.sorting.quicksort.impl;

import static com.svetanis.java.base.utils.Swap.swap;

import java.util.Random;

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

public final class Partition {
  // Time Complexity: O(right - left + 1) per call, every method: one pass over the range
  // Space Complexity: O(1)

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
        swap(a, i, j);
      }
    }
    // move pivot to its final place, one past the last smaller-or-equal value
    swap(a, i + 1, right);
    return i + 1;
  }

  // larger elements to the left, around the pivot a[right]
  // i is the NEXT slot for a value > pivot, so it starts at the range's first slot: left
  public static int reversePartition(int[] a, int left, int right) {
    int i = left;
    int pivot = a[right];
    for (int j = left; j < right; ++j) {
      if (a[j] > pivot) {
        swap(a, j, i); // fill first, then step
        i++;
      }
    }
    // move pivot to its final place, the slot i points at
    swap(a, right, i);
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
        swap(a, smaller, current);
        smaller++;
        current++;
      } else if (a[current] > pivot) {
        swap(a, current, larger); // current not advanced: the value swapped in is unread
        larger--;
      } else {
        current++;
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
        swap(a, larger, current);
        larger++;
        current++;
      } else if (a[current] < pivot) {
        swap(a, current, smaller); // current not advanced: the value swapped in is unread
        smaller--;
      } else {
        current++;
      }
    }
    return new Block(larger, smaller);
  }

  private static int randomIndex(int left, int right) {
    return GENERATOR.nextInt(right - left + 1) + left; // right included
  }

  // the block of values equal to the pivot, first and last index inclusive
  public record Block(int first, int last) {
  }

}
