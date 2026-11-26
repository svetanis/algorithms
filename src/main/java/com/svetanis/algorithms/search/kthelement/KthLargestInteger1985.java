package com.svetanis.algorithms.search.kthelement;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.naturalOrder;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 1985. Find the Kth Largest Integer in the Array

// Given nums, an array of strings that each hold a non-negative integer with
// no leading zeros, and k (1 <= k <= nums.length), return the string holding
// the kth largest integer. Duplicates count separately.

// A number can be 100 digits long, too long for any primitive, so the
// strings are compared as numbers directly: a shorter string is a smaller
// number, and two strings of the same length compare digit by digit, which is
// plain string order. With that comparator this is LC 215 again -- a
// min-heap keeps the k largest, and its top is the kth largest.

// Time: kthLargestNum O(n log k * m) -- an add and at most one poll on a heap
//   of k per number, each comparison reading up to m digits;
//   kthLargestNumSort O(n log n * m)
// Space: kthLargestNum O(k) for the heap; kthLargestNumSort sorts the
//   caller's array in place

// Siblings:
//   KthLargestMinHeapPQ (215) -- the same min-heap of k, on int.

public final class KthLargestInteger1985 {

  public static String kthLargestNum(String[] nums, int k) {
    Queue<String> pq =
        new PriorityQueue<String>(
            comparingInt((String s) -> s.length()).thenComparing(naturalOrder())); // HEAP: min-heap by value -- shorter is smaller, then digit by digit

    for (String num : nums) {
      pq.offer(num); // ADD the number
      if (pq.size() > k) {
        pq.poll(); // EVICT the smallest of k + 1 -- it is not among the k largest
      }
    }
    return pq.peek(); // REPORT: the smallest of the k largest
  }

  public static String kthLargestNumSort(String[] nums, int k) {
    int len = nums.length;
    Arrays.sort(nums, comparingInt((String s) -> s.length()).thenComparing(naturalOrder())); // SORT ascending by value, in the caller's array
    return nums[len - k]; // REPORT: the kth from the end
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