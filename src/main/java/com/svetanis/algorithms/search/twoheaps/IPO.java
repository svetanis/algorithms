package com.svetanis.algorithms.search.twoheaps;

import java.util.PriorityQueue;
import java.util.Queue;

// 502. IPO

public final class IPO {
  // Time Complexity: O(n * log n + k * log k)
  // Space Complexity: O(n + k)

  public static int maxCapital(int k, int w, int[] profit, int[] capital) {
    Queue<Project> minHeap = minHeap(profit, capital);
    Queue<Integer> maxHeap = new PriorityQueue<>((p1, p2) -> p2 - p1);
    while (k > 0) {
      while (!minHeap.isEmpty() && minHeap.peek().capital <= w) {
        maxHeap.offer(minHeap.poll().profit);
      }
      if (maxHeap.isEmpty()) {
        break;
      }
      w += maxHeap.poll();
      k--;
    }
    return w;
  }

  private static Queue<Project> minHeap(int[] profit, int[] capital) {
    Queue<Project> pq = new PriorityQueue<>((p1, p2) -> p1.capital - p2.capital);
    for (int i = 0; i < profit.length; i++) {
      pq.offer(new Project(profit[i], capital[i]));
    }
    return pq;
  }

  public static void main(String args[]) {
    int[] p1 = { 1, 2, 3 };
    int[] c1 = { 0, 1, 1 };
    System.out.println(maxCapital(2, 0, p1, c1)); // 4

    int[] p2 = { 1, 2, 3 };
    int[] c2 = { 0, 1, 2 };
    System.out.println(maxCapital(3, 0, p2, c2)); // 6
  }

  private static class Project {
    private int profit;
    private int capital;

    public Project(int profit, int capital) {
      this.profit = profit;
      this.capital = capital;
    }
  }
}
