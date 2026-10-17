package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 27. Remove Element
//
// Input: an array and a value.
// Return: k, the count of numbers NOT equal to the value, after moving those numbers,
// in place, into the first k slots. Whatever sits past slot k does not matter.
//
// The one idea: a WRITE cursor and a READ cursor walk the same array. The reader visits
// every number once; a number worth keeping is copied down to the writer, which then
// steps on. The writer never passes the reader, so a copy never lands on a number that
// has not been read yet.
//
// Time: O(n) -- the reader visits every number once.
// Space: O(1) -- the array is rewritten in place.

public final class RemoveGivenElement {

  public static int remove(int[] a, int target) {
    // slow = the WRITE cursor: everything in [0, slow) is finished
    // fast = the READ cursor: visits every element exactly once
    int slow = 0;
    for (int fast = 0; fast < a.length; fast++) {
      if (a[fast] != target) {     // KEEP: the test reads only the number under the reader
        a[slow] = a[fast];         // WRITE it down to the writer
        slow++;                    // MOVE the writer past it
      }
    }
    return slow;                   // RETURN the length of the kept prefix
  }

  public static void main(String[] args) {
    int[] a = { 4, 1, 1, 2, 1, 3 };
    System.out.println(remove(a, 1));          // 3
    System.out.println(Arrays.toString(a));    // [4, 2, 3, 2, 1, 3]

    int[] a1 = { 3, 2, 2, 3 };
    System.out.println(remove(a1, 3));         // 2

    int[] a2 = { 0, 1, 2, 2, 3, 0, 4, 2 };
    System.out.println(remove(a2, 2));         // 5
  }
}