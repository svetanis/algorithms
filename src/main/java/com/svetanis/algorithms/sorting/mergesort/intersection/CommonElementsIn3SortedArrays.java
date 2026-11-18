package com.svetanis.algorithms.sorting.mergesort.intersection;

import java.util.ArrayList;
import java.util.List;

// Common elements of three sorted arrays (GeeksforGeeks; LeetCode 1213, Intersection of Three
// Sorted Arrays, is the same task for strictly increasing arrays)
//
// Input: three arrays a1, a2 and a3, each sorted in non-decreasing order. Returns the values that
// are in all three, in sorted order. A value repeated in all three comes out as many times as it
// appears in the array that has the fewest copies of it.
//
// The one idea: i, j and k point at the smallest number of each array not yet looked at. If the
// three are equal, that value is in all three: take it and move all three on. If not, the
// smallest front is smaller than the largest front. The array holding the largest front has
// nothing left below it, so the smallest front is not in that array and cannot be common: skip
// it. The code skips a1[i] if it is below a2[j], else a2[j] if it is below a3[k], else a3[k].
//
// Time: O(n1 + n2 + n3) -- every step moves a pointer right, and none moves back.
// Space: O(1) -- three indices; the returned list is the output.

public final class CommonElementsIn3SortedArrays {

  public static List<Integer> common(int[] a1, int[] a2, int[] a3) {
    int n1 = a1.length;
    int n2 = a2.length;
    int n3 = a3.length;
    int i = 0, j = 0, k = 0;
    List<Integer> list = new ArrayList<>();
    while (i < n1 && j < n2 && k < n3) { // STOP: one array is used up
      if (a1[i] == a2[j] && a2[j] == a3[k]) { // TAKE: the value is in all three
        list.add(a1[i]);
        i++;
        j++;
        k++;
      } else if (a1[i] < a2[j]) { // SKIP a1[i]: below a2[j]
        i++;
      } else if (a2[j] < a3[k]) { // SKIP a2[j]: below a3[k]
        j++;
      } else { // SKIP a3[k]: here a1[i] >= a2[j] >= a3[k], not all equal, so a3[k] < a1[i]
        k++;
      }
    }
    return list; // RETURN
  }

  public static void main(String[] args) {
    int[] ar1 = { 1, 5, 10, 20, 40, 80 };
    int[] ar2 = { 6, 7, 20, 80, 100 };
    int[] ar3 = { 3, 4, 15, 20, 30, 70, 80, 120 };
    System.out.println(common(ar1, ar2, ar3)); // [20, 80]

    int[] a1 = { 1, 5, 5 };
    int[] a2 = { 3, 4, 5, 5, 10 };
    int[] a3 = { 5, 5, 10, 20 };
    System.out.println(common(a1, a2, a3)); // [5, 5]
  }
}
