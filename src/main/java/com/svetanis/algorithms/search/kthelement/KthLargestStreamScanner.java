package com.svetanis.algorithms.search.kthelement;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// find kth largest element in a stream of numbers

public final class KthLargestStreamScanner {
  // Time complexity: O(log k)
  
  public static void kthLargest(Scanner input, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>(k, (x, y) -> x - y);

    int count = 0;
    while (true) {
      System.out.println("Enter a number: ");
      int x = input.nextInt();

      if (count < k - 1) {
        pq.add(x);
      } else {
        if (count == k - 1) {
          pq.add(x);
        }
        
        // if next element is greater than
        // k-th largest, then replace the root
        if (x > pq.peek()) {
          pq.poll();
          pq.offer(x);
        }

        // root of heap is k'th largest
        System.out.println("K-th largest: ");
        System.out.println(pq.peek());
      }
      count++;
    }
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    kthLargest(input, 3);
  }
}