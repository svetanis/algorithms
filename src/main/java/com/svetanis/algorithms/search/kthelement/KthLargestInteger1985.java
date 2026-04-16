package com.svetanis.algorithms.search.kthelement;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 1985. Find the Kth Largest Integer in the Array

public final class KthLargestInteger1985 {
  // Time Complexity: O(n log k)

  public static String kthLargestNum(String[] nums, int k) {
    Queue<String> pq = new PriorityQueue<String>((x, y) -> 
    x.length() == y.length() ? x.compareTo(y) : x.length() - y.length());

    for (String num : nums) {
      pq.offer(num);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    return pq.peek();
  }

  public static String kthLargestNumSort(String[] nums, int k) {
    int len = nums.length;
    Arrays.sort(nums, (x, y) -> 
    x.length() == y.length() ? x.compareTo(y) : x.length() - y.length());
    return nums[len - k];
  }

  public static void main(String[] args) {
    String[] a1 = { "3", "6", "7", "10" };
    System.out.println(kthLargestNum(a1, 4)); // 3
    String[] a2 = { "2", "21", "12", "1" };
    System.out.println(kthLargestNum(a2, 3)); // 2
    String[] a3 = { "0", "0" };
    System.out.println(kthLargestNum(a3, 2)); // 0
  }
}