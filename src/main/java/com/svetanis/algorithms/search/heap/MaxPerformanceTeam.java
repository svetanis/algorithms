package com.svetanis.algorithms.search.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

// 1383. Maximum Performance of a Team

public final class MaxPerformanceTeam {
  // Time Complexity: O(n log n + n log k)
  // Space Complexity: O(n)

  private static final int MOD = (int) 1e9 + 7;

  public static int maxPerformance(int n, int[] speed, 
      int[] efficiency, int k) {
    int[][] engineers = new int[n][2];
    for (int i = 0; i < n; i++) {
      engineers[i] = new int[] { speed[i], efficiency[i] };
    }
    Arrays.sort(engineers, (a, b) -> b[1] - a[1]);

    long totalSpeed = 0;
    long maxPerformance = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int[] engineer : engineers) {
      int s = engineer[0], e = engineer[1];
      totalSpeed += s;
      maxPerformance = Math.max(totalSpeed * e, maxPerformance);
      pq.offer(s);
      if (pq.size() == k) {
        totalSpeed -= pq.poll();
      }
    }
    return (int) (maxPerformance % MOD);
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