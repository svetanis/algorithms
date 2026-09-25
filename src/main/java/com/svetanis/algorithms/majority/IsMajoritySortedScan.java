package com.svetanis.algorithms.majority;

import static com.google.common.primitives.Ints.indexOf;

// does the SORTED array a[] hold more than n/2 copies of x?

public final class IsMajoritySortedScan {

  // PRECONDITION: a is sorted ascending. Nothing here checks it, and nothing
  // here sorts -- the caller has already paid for that. Handed an unsorted
  // array this returns a confident wrong answer: on {1, 3, 1, 3, 2} with x = 1
  // it says true, though 1 appears twice in five. The same values sorted,
  // {1, 1, 2, 3, 3}, correctly say false.

  public static boolean isMajority(int[] a, int x) {
    // Time complexity: O(n) -- O(log n) if the lookup is a binary search

    int n = a.length;

    // indexOf gives the FIRST position holding x. That is what the test below
    // needs, and it is the one thing MajorityElementMooreVoting's
    // findCandidate does not give -- it returns whichever position its counter
    // last reset at. On {1, 1, 2, 2, 2} that is index 3, not 2, and the same
    // test then reports no majority for a value holding three slots of five.
    int index = indexOf(a, x);

    // absent: indexOf gives -1, and a[-1 + n/2] is out of bounds whenever n/2
    // is 0. Same guard as IsMajoritySortedRecursive, which has always had it.
    if (index == -1) {
      return false;
    }

    // x owns a run of equal values starting at index. If the slot n/2 further
    // on still holds x, that run is longer than half the array.
    return index + n / 2 < n && a[index + n / 2] == x;
  }

  public static void main(String[] args) {
    int[] array = { 1, 2, 3, 3, 3, 3, 10 };
    System.out.println(isMajority(array, 3)); // true -- 4 of 7
    int[] array1 = { 1, 1, 2, 3, 3 };
    System.out.println(isMajority(array1, 1)); // false -- 2 of 5
  }
}
