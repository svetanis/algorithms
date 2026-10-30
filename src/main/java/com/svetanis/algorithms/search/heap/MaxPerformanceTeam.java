package com.svetanis.algorithms.search.heap;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;
import java.util.PriorityQueue;

// 1383. Maximum Performance of a Team
//
// n engineers, engineer i with speed[i] and efficiency[i]. Choose at most k of them; a team's
// performance is (sum of their speeds) * (smallest efficiency among them). Returns the largest
// performance modulo 10^9 + 7.
//
// Fix who has the smallest efficiency. Walk the engineers by efficiency, highest first: the one
// just reached has the smallest efficiency of everyone seen so far, so the best team with him as
// the minimum is him plus the fastest of the others seen. A min-heap of speeds keeps the k - 1
// fastest to go with the next engineer, and the slowest is the one evicted. The maximum is taken
// on the exact long products, and the modulo only at the end -- taking it earlier would compare
// remainders, not performances.
//
// Time: O(n log n + n log k) -- the sort, then a heap of at most k speeds
// Space: O(n) for the sorted pairs
//
// Siblings, same move (sort by the factor that is a minimum, heap the summed part):
//   MaxSubSeqScore (2542) -- exactly k chosen, not at most k
//   MinCost (857)         -- the minimum cost, so the factor is a maximum and the heap evicts the largest

public final class MaxPerformanceTeam {

  private static final int MOD = (int) 1e9 + 7;

  public static int maxPerformance(int n, int[] speed,
      int[] efficiency, int k) {
    int[][] engineers = new int[n][2];
    for (int i = 0; i < n; i++) {
      engineers[i] = new int[] { speed[i], efficiency[i] };
    }
    Arrays.sort(engineers, comparingInt((int[] a) -> a[1]).reversed()); // ORDER: highest efficiency first

    long totalSpeed = 0;
    long maxPerformance = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>(); // MIN-HEAP: the slowest kept engineer on top
    for (int[] engineer : engineers) {
      int s = engineer[0], e = engineer[1];
      totalSpeed += s;                                              // ADD: he joins; e is the team's smallest efficiency
      maxPerformance = Math.max(totalSpeed * e, maxPerformance);    // RECORD: on the long product, before any modulo
      pq.offer(s);                                                  // PUSH: his speed
      if (pq.size() == k) {
        totalSpeed -= pq.poll();                                    // EVICT: the slowest, leaving k - 1 for the next engineer
      }
    }
    return (int) (maxPerformance % MOD);                            // REPORT: the modulo once, at the end
  }

  public static void main(String[] args) {
    int[] s1 = { 2, 10, 3, 1, 5, 8 };
    int[] e1 = { 5, 4, 3, 9, 7, 2 };
    System.out.println(maxPerformance(6, s1, e1, 2)); // 60

    int[] s2 = { 2, 10, 3, 1, 5, 8 };
    int[] e2 = { 5, 4, 3, 9, 7, 2 };
    System.out.println(maxPerformance(6, s2, e2, 3)); // 68

    int[] s3 = { 2, 10, 3, 1, 5, 8 };
    int[] e3 = { 5, 4, 3, 9, 7, 2 };
    System.out.println(maxPerformance(6, s3, e3, 4)); // 72
  }
}