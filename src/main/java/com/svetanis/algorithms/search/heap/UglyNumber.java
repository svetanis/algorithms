package com.svetanis.algorithms.search.heap;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

// 264. Ugly Number II

// find the n-th ugly number
// ugly numbers are positive numbers
// whose prime factors only include
// 2, 3, 5

public final class UglyNumber {
  // Time Complexity: O(n log n)

  public static int nthUglyNum(int n) {
    int[] ugly = new int[n];
    ugly[0] = 1;
    int i2 = 0, i3 = 0, i5 = 0;
    for (int i = 1; i < n; i++) {
      int next2 = ugly[i2] * 2;
      int next3 = ugly[i3] * 3;
      int next5 = ugly[i5] * 5;
      int next = Math.min(next2, Math.min(next3, next5));
      ugly[i] = next;
      if (next == next2) {
        i2 += 1;
      }
      if (next == next3) {
        i3 += 1;
      }
      if (next == next5) {
        i5 += 1;
      }
    }
    return ugly[n - 1];
  }

  public static int nthUgly(int n) {
    Set<Long> set = new HashSet<>(1);
    List<Integer> factors = asList(2, 3, 5);
    Queue<Long> pq = new PriorityQueue<>();
    pq.add(1L);
    set.add(1L);
    long curr = 0;
    for (int i = 0; i < n; i++) {
      curr = pq.poll();
      for (int factor : factors) {
        long next = curr * factor;
        if (set.add(next)) {
          pq.add(next);
        }
      }
    }
    return (int) curr;
  }

  public static int ugly(int n) {
    Set<Long> set = new HashSet<>(1);
    List<Integer> factors = asList(2, 3, 5);
    Queue<Long> pq = new PriorityQueue<>();
    pq.add(1L);
    int count = 0;
    while (!pq.isEmpty()) {
      long curr = pq.poll();
      count += 1;
      if (count == n) {
        return (int) curr;
      }
      for (int factor : factors) {
        long next = curr * factor;
        if (set.add(next)) {
          pq.add(next);
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(nthUgly(10)); // 12
    System.out.println(nthUgly(1)); // 1

    System.out.println(ugly(10)); // 12
    System.out.println(ugly(1)); // 1
  }
}