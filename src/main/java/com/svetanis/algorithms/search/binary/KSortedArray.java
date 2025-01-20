package com.svetanis.algorithms.search.binary;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.binary.BinarySearchRecursive.binarySearch;
import static com.svetanis.java.base.collect.Lists.sort;
import static java.lang.Math.abs;
import java.util.List;

// given an array of n distinct elements
// check whether the given array is k-sorted or not

// k-sorted array is an array where each element is
// at most k distance away from its target position
// in the sorted array

public final class KSortedArray {

  public static boolean kSorted(List<Integer> list, int k) {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)
	  
    List<Integer> sorted = sort(list);
    for (int i = 0; i < list.size(); i++) {
      int index = binarySearch(sorted, list.get(i));
      int diff = abs(i - index);
      if (diff > k) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    List<Integer> list = newArrayList( 3, 2, 1, 5, 6, 4 );
    System.out.println(kSorted(list, 2));
  }
}
