package com.svetanis.algorithms.sorting.mergesort;

import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Integer.MAX_VALUE;

// given two sorted arrays, A and B,
// where A has a large enough buffer
// at the end to hold B
// merge B into A in sorted order

public final class MergeFirstSortedArrayIntoSecondSortedArray {

  public static int[] merge(int[] larger, int[] smaller) {
    // Time complexity: O(n)
    // Auxiliary Space: O(1)
    // Space Complexity: O(n) due to input size

    int n = larger.length;
    int m = smaller.length;

    int i = m - 1; // current index of input part of larger array
    int j = m - 1; // current index of smaller array
    int k = n - 1; // current index of output part of larger array

    
    while (j >= 0) {
      if (i >= 0 && larger[i] > smaller[j]) {
    	larger[k] = larger[i--];  
      } else {
    	larger[k] = smaller[j--];
      }
      k--;
    }
    return larger;
  }

  public static void main(String[] args) {
    int[] smaller = {1, 3, 5};
    int[] larger = {2, 4, 6, MAX_VALUE, MAX_VALUE, MAX_VALUE};
    print(merge(larger, smaller));

    int[] small = {2};
    int[] large = {-4, -3, 0, MAX_VALUE};
    print(merge(large, small));
  }
}
