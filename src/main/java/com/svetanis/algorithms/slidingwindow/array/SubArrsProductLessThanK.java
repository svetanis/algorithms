package com.svetanis.algorithms.slidingwindow.array;

import java.util.ArrayList;
import java.util.List;

// All subarrays with product less than k -- no LeetCode number; source unknown

// given array of positive integers and target number K
// find all subarrays whose product is less than K
// Takes a (every number at least 1) and k. Returns every such subarray
// as a list, grouped by where it ends; within a group the shortest comes
// first. Nothing qualifies when k <= 1. A product past 2^31 - 1 wraps.

// With every number at least 1, a longer block never has a smaller
// product. So for each right end, once the window is shrunk until its
// product is below k, every block that ends at right and starts at or
// after left is below k too, and no block starting before left is.

// Time: O(n^3) -- up to n^2 subarrays, and each is copied out, up to n
//   numbers apiece; the add at the front of the running list also
//   shifts up to n numbers
// Space: O(n^3) -- the output itself, up to n^2 lists of up to n numbers

// Sibling: CountSubArrsProductLessThanK (713) runs the same window but
// only COUNTS the subarrays, right - left + 1 per right end, in O(n).

public final class SubArrsProductLessThanK {

  public static List<List<Integer>> subarrays(int[] a, int k) {
    // Time Complexity: O(n^3)

    int n = a.length;
    int prod = 1;
    int left = 0;
    List<List<Integer>> lists = new ArrayList<>();
    for (int right = 0; right < n; right++) {
      prod *= a[right]; // ADD
      while (left <= right && prod >= k) { // SHRINK: while the product is too big
        prod /= a[left++]; // REMOVE
      }
      // since the product of all nums from 
      // left to right is less than target
      // therefore, all subarrays from left
      // to right will have a product less
      // than the target too
      // to avoid duplicates start from a[right]
      List<Integer> list = new ArrayList<>();
      for(int i = right; i >= left; i--) { // COUNT: the right - left + 1 subarrays ending at right
        list.add(0, a[i]);
        lists.add(List.copyOf(list)); // a copy: list keeps growing
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
