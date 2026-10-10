package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;
import java.util.List;

// Segregate even and odd numbers, in place
//
// Input: an array (or a List) of integers; negatives are allowed, since x % 2 == 0
// tests evenness for both signs.
// Return: nothing for the array, the same List for the List -- rearranged in place so
// that every even number comes before every odd number. The order inside each group is
// not kept.
//
// The one idea: two cursors converging from the ends. [0, left) holds evens and
// (right, n-1] holds odds. left skips the evens already in place, right skips the odds
// already in place; when both stop, a[left] is odd and a[right] is even, and one swap
// puts both where they belong.
//
// Two methods, the same loop:
//   segregate(int[])         -- on an array
//   segregate(List<Integer>) -- on a List; it reads into int locals before swapping
// Siblings -- the same question:
//   twopointers.segregate.SegregateOddAndEvenExtraSpace -- fills a new array from both
//     ends instead, O(n) space
//   twopointers.SortByParity (LC 905) -- sortByParity2 is this loop; sortByParity is the
//     read/write version, which keeps the evens' order
//   twopointers.segregate.SegregateZerosAndOnesSingleTraversal -- this loop with the test
//     "is 0" in place of "is even"
//
// Time: O(n) -- each step moves left or right inward, so at most n steps.
// Space: O(1) -- swaps in place.

public final class SegregateOddAndEvenInPlace {

  public static void segregate(int[] a) {
    int left = 0;                                        // START: [0, left) holds evens
    int right = a.length - 1;                            // START: (right, n-1] holds odds

    while (left < right) {                               // STOP: the cursors meet

      while (a[left] % 2 == 0 && left < right) {
        left++;                                          // SKIP evens already in place
      }

      while (a[right] % 2 != 0 && left < right) {
        right--;                                         // SKIP odds already in place
      }

      if (left < right) {
        swap(a, left, right);                            // SWAP an odd on the left with an even on the right
        left++;
        right--;
      }
    }
  }

  public static List<Integer> segregate(List<Integer> list) {
    int left = 0;                                        // START: [0, left) holds evens
    int right = list.size() - 1;                         // START: (right, n-1] holds odds

    while (left < right) {                               // STOP: the cursors meet

      while (list.get(left) % 2 == 0 && left < right) {
        left++;                                          // SKIP evens already in place
      }

      while (list.get(right) % 2 != 0 && left < right) {
        right--;                                         // SKIP odds already in place
      }

      if (left < right) {
        swap(left, right, list);                         // SWAP an odd on the left with an even on the right
        left++;
        right--;
      }
    }
    return list;
  }

  private static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  private static void swap(int i, int j, List<Integer> list) {
    int temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }

  public static void main(String[] args) {
    int[] a = { 3, 4 };
    segregate(a);
    System.out.println(Arrays.toString(a)); // [4, 3]

    int[] a1 = { 4, 9, 5, 2, 9, 5, 7, 10 };
    segregate(a1);
    System.out.println(Arrays.toString(a1)); // [4, 10, 2, 5, 9, 5, 7, 9]

    int[] a2 = { 12, 34, 45, 9, 8, 90, 3 };
    segregate(a2);
    System.out.println(Arrays.toString(a2)); // [12, 34, 90, 8, 9, 45, 3]

    System.out.println(segregate(Arrays.asList(4, 9, 5, 2, 9, 5, 7, 10))); // [4, 10, 2, 5, 9, 5, 7, 9]
  }
}
