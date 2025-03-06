package com.svetanis.algorithms.search.median;

import java.util.PriorityQueue;
import java.util.Queue;

// 295. Find Median from Data Stream

public final class OnlineMedianSubmit {
  // Time Complexity: add - O(log n)
  // Time Complexity: findMedian - O(1)

  private Queue<Integer> min;
  private Queue<Integer> max;

  public OnlineMedianSubmit() {
    this.min = new PriorityQueue<>((a, b) -> a - b);
    this.max = new PriorityQueue<>((a, b) -> b - a);
  }

  public void add(int x) {
    if (!max.isEmpty() && x > max.peek()) {
      min.offer(x);
    } else {
      max.offer(x);
    }
    rebalance();
  }

  private void rebalance() {
    if (min.size() > max.size() + 1) {
      max.offer(min.poll());
    } else if (max.size() > min.size() + 1) {
      min.offer(max.poll());
    }
  }

  public double median() {
    if (max.isEmpty()) {
      return 0;
    }
    if (min.size() == max.size()) {
      return 0.5 * (min.peek() + max.peek());
    } else {
      return max.size() > min.size() ? max.peek() : min.peek();
    }
  }

  public static void main(String args[]) {
    OnlineMedianSubmit om = new OnlineMedianSubmit();
    om.add(1);
    om.add(2);
    System.out.println(om.median()); // 1.5
    om.add(3);
    System.out.println(om.median()); // 2.0
  }
}
