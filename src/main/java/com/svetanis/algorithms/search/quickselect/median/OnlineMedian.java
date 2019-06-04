package com.svetanis.algorithms.search.quickselect.median;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// Design an algorithm for computing the running median of a sequence. 

// We use two heaps, max heap, and min heap. 
// The invariant here is that for every incoming element from the stream, 
// we want to let Max Heap store the smaller half of the stream data so far, 
// and let Min Heap store the bigger half. By keeping this invariant, 
// we can output the median easily according to the number of elements 
// we have seen so far. 

public final class OnlineMedian {

  private static Queue<Integer> MIN = new PriorityQueue<Integer>();
  private static Queue<Integer> MAX = new PriorityQueue<Integer>((a, b) -> b.compareTo(a));

  public static void add(int x) {
    if (!MAX.isEmpty() && x > MAX.peek()) {
      MIN.offer(x);
    } else {
      MAX.offer(x);
    }

    if (MIN.size() > MAX.size() + 1) {
      MAX.offer(MIN.poll());
    } else if (MAX.size() > MIN.size() + 1) {
      MIN.offer(MAX.poll());
    }
  }

  public static double getMedian() {
    if (MIN.size() == MAX.size()) {
      return 0.5 * (MIN.peek() + MAX.peek());
    } else {
      if (MAX.size() > MIN.size()) {
        return MAX.peek();
      } else {
        return MIN.peek();
      }
    }
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      int num = in.nextInt();
      add(num);
      System.out.println(getMedian());
    }
    in.close();
  }
}
