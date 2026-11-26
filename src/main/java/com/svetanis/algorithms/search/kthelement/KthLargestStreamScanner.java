package com.svetanis.algorithms.search.kthelement;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// find kth largest element in a stream of numbers
// (no LeetCode number of its own -- LC 703 is KthLargestStream)

// Read integers one at a time from a Scanner and, once k have arrived, print
// the kth largest of everything read so far after each new one. Duplicates
// count separately. Stops when the input runs out of integers.

// Keep the k largest seen so far in a min-heap: its top is the smallest of
// them, which is the kth largest. Until the heap holds k, every number goes
// in. After that a number goes in only if it beats the top, and the top
// leaves to make room -- so the heap holds exactly k from then on.

// Time: O(log k) per number -- at most one poll and one add on a heap of k
// Space: O(k)

// Siblings:
//   KthLargestStream (703) -- the same heap behind an add() call, LeetCode's shape.
//   KthLargestMinHeapPQ (215) -- the same heap over a whole array at once.

public final class KthLargestStreamScanner {

  public static void kthLargest(Scanner input, int k) {
    Queue<Integer> pq = new PriorityQueue<Integer>(k, Integer::compare); // HEAP: min-heap, the top is the kth largest
    System.out.println("Enter a number: ");
    while (input.hasNextInt()) { // READ until the input runs out
      int x = input.nextInt();
      if (pq.size() < k) {
        pq.add(x); // SEED: fewer than k so far, every number is among the k largest
      } else if (x > pq.peek()) { // TEST: beats the smallest of the k largest?
        pq.poll(); // EVICT the top -- no longer among the k largest
        pq.add(x); // KEEP the newcomer
      }
      if (pq.size() == k) {
        // root of heap is k'th largest
        System.out.println("K-th largest: ");
        System.out.println(pq.peek()); // REPORT: the smallest of the k largest
      }
      System.out.println("Enter a number: ");
    }
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    kthLargest(input, 3); // input 1 2 5 3 9 0 7 prints 1 2 3 3 5
  }
}