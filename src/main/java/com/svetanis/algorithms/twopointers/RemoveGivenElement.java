package com.svetanis.algorithms.twopointers;

import static com.svetanis.java.base.utils.Print.print;

// 27. Remove Element

// Given an array and a value, remove all the instances of that value in the array. 
// Also return the number of elements left in the array after the operation.
// It does not matter what is left beyond the expected length.

public final class RemoveGivenElement {

  public static int remove(int[] a, int target) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    // slow = the WRITE cursor: everything in [0, slow) is finished
    // fast = the READ cursor: visits every element exactly once
    int slow = 0;
    for (int fast = 0; fast < a.length; fast++) {
      if (a[fast] != target) {
        a[slow] = a[fast];
        slow++;
      }
    }
    return slow;
  }

  public static void main(String[] args) {
    int[] a = {4, 1, 1, 2, 1, 3};
    System.out.println(remove(a, 1));
    print(a);
  }
}