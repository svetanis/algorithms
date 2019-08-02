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

  private Queue<Integer> min;
  private Queue<Integer> max;

  public OnlineMedian() {
    this.min = new PriorityQueue<Integer>();
    this.max = new PriorityQueue<Integer>((a, b) -> b.compareTo(a));
  }

  public void add(int x) {
    if (!max.isEmpty() && x > max.peek()) {
      min.offer(x);
    } else {
      max.offer(x);
    }

    if (min.size() > max.size() + 1) {
      max.offer(min.poll());
    } else if (max.size() > min.size() + 1) {
      min.offer(max.poll());
    }
  }

  public double getMedian() {
    if (min.size() == max.size()) {
      return 0.5 * (min.peek() + max.peek());
    } else {
      if (max.size() > min.size()) {
        return max.peek();
      } else {
        return min.peek();
      }
    }
  }

  public static void main(String args[]) {
    OnlineMedian om = new OnlineMedian();
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      int num = in.nextInt();
      om.add(num);
      System.out.println(om.getMedian());
    }
    in.close();
  }
}
