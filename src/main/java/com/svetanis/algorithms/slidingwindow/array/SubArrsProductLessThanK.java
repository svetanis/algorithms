package com.svetanis.algorithms.slidingwindow.array;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

// given array of positive integers and target number K
// find all subarrays whose product is less than K

public final class SubArrsProductLessThanK {

  public static List<List<Integer>> subarrays(int[] a, int k) {
    // Time Complexity: O(n^3)

	int n = a.length;
    int prod = 1;
    int left = 0;
    List<List<Integer>> lists = newArrayList();
    for (int right = 0; right < n; right++) {
      prod *= a[right];
      while (left <= right && prod >= k) {
        prod /= a[left++];
      }
      List<Integer> list = newArrayList();
      for(int i = right; i >= left; i--) {
    	  list.add(0, a[i]);
    	  lists.add(newList(list));
      }
    }
    return lists;
  }

  public static void main(String[] args) {
    
    int[] a1 = { 1, 2, 3, 4 };
    System.out.println(subarrays(a1, 10));

    int[] a2 = { 1, 9, 2, 8, 6, 4, 3 };
    System.out.println(subarrays(a2, 100));

    int[] a3 = { 5, 3, 2};
    System.out.println(subarrays(a3, 16));

    int[] a4 = { 100, 200};
    System.out.println(subarrays(a4, 100));

    int[] a5 = { 100, 200};
    System.out.println(subarrays(a5, 101));

  }
}
