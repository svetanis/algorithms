package com.svetanis.algorithms.search.twoheaps;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;

import java.util.PriorityQueue;
import java.util.Queue;

// 502. IPO
//
// Project i needs capital[i] to start and adds profit[i] to the capital when it finishes. Start
// with capital w and take at most k distinct projects, one after another. Returns the largest
// final capital. n and k up to 10^5, w and capital[i] up to 10^9, profit[i] between 0 and 10^4;
// LeetCode guarantees the answer fits in an int.
//
// Profits are never negative, so the capital never falls: a project affordable now stays
// affordable, and taking the most profitable affordable project is always safe. Two heaps on two
// different keys: minHeap holds the projects not yet affordable, smallest capital on top; maxHeap
// holds the profits of the affordable ones, largest on top. Each round UNLOCKs everything the
// current capital covers, then TAKEs the top of maxHeap.
//
// Siblings, same problem:
//   MaximizeCapital  the same two heaps over project INDEXES, with comparators that read c[i]
//                    and p[i], and parameters in the order (capital, profit, k, w). Every
//                    peek() there is an index, so the unlock test needs the array lookup

public final class IPO {
  // Time complexity: O(n * log(n)), every project is pushed into minHeap once, moved to maxHeap
  // at most once and taken at most once; a round that takes nothing ends the loop, so there are
  // at most min(k, n) rounds
  // Auxiliary Space: O(n), the two heaps hold each project at most once between them

  public static int maxCapital(int k, int w, int[] profit, int[] capital) {
    // WAITING: every project, smallest capital first
    Queue<Project> minHeap = minHeap(profit, capital);
    // READY: profits of the affordable projects, largest first
    Queue<Integer> maxHeap = new PriorityQueue<>(reverseOrder());
    while (k > 0) {
      // UNLOCK: a while, not an if -- one project can lift w past several capitals
      while (!minHeap.isEmpty() && minHeap.peek().capital <= w) {
        maxHeap.offer(minHeap.poll().profit);
      }
      // STOP: nothing affordable now, and nothing ever will be, because w did not grow
      if (maxHeap.isEmpty()) {
        break;
      }
      // TAKE: the most profitable affordable project
      w += maxHeap.poll();
      k--;
    }
    return w;
  }

  private static Queue<Project> minHeap(int[] profit, int[] capital) {
    Queue<Project> pq = new PriorityQueue<>(comparingInt(p -> p.capital));
    for (int i = 0; i < profit.length; i++) {
      pq.offer(new Project(profit[i], capital[i]));
    }
    return pq;
  }

  public static void main(String[] args) {
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
