package com.svetanis.algorithms.search.heap;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

// 264. Ugly Number II
//
// An ugly number is a positive integer whose only prime factors are 2, 3 and 5; 1 is the first.
// Given n (1 <= n <= 1690), returns the n-th ugly number. The 1690th, 2,123,366,400, still fits
// in an int.
//
// Every ugly number after 1 is a smaller ugly number times 2, 3 or 5. Three methods build them in
// increasing order:
//   nthUglyNum -- three pointers into the list built so far, one per factor. Each points at the
//     smallest ugly number whose product with its factor is not yet in the list; the next ugly
//     number is the smallest of the three products. Every pointer whose product equals it moves,
//     which is what keeps 6 = 2 * 3 = 3 * 2 from going in twice.
//   nthUgly -- a min-heap of candidates polled n times; each poll pushes its three products, and a
//     set of everything pushed drops the duplicates. Kept in long, so the products past the int
//     range do no harm.
//   ugly -- the same heap and set, counting the polls inside the loop instead of a fixed for.
//
// Time: nthUglyNum O(n); nthUgly and ugly O(n log n) -- n polls, three pushes each
// Space: O(n) for the list, or for the heap and the set
//
// Siblings, the ugly numbers:
//   IsUglyNumber (263)    -- is one number ugly
//   SuperUglyNumber (313) -- any list of primes; one heap with no set, each number pushed only from
//                            itself divided by its smallest prime factor

public final class UglyNumber {

  public static int nthUglyNum(int n) {
    int[] ugly = new int[n];
    ugly[0] = 1;                                    // START: 1 is the first ugly number
    int i2 = 0, i3 = 0, i5 = 0;                     // POINTERS: the next number to multiply by 2, 3, 5
    for (int i = 1; i < n; i++) {
      int next2 = ugly[i2] * 2;
      int next3 = ugly[i3] * 3;
      int next5 = ugly[i5] * 5;
      int next = Math.min(next2, Math.min(next3, next5)); // NEXT: the smallest product not yet in the list
      ugly[i] = next;                               // APPEND
      if (next == next2) {
        i2 += 1;                                    // MOVE: three ifs, not else-if -- every pointer that made it moves
      }
      if (next == next3) {
        i3 += 1;                                    // MOVE
      }
      if (next == next5) {
        i5 += 1;                                    // MOVE
      }
    }
    return ugly[n - 1];                             // REPORT
  }

  public static int nthUgly(int n) {
    Set<Long> set = new HashSet<>(1);               // SEEN: every number ever pushed
    List<Integer> factors = asList(2, 3, 5);
    Queue<Long> pq = new PriorityQueue<>();         // MIN-HEAP: the candidates, the smallest on top
    pq.add(1L);                                     // START: 1
    set.add(1L);
    long curr = 0;
    for (int i = 0; i < n; i++) {
      curr = pq.poll();                             // POLL: the next ugly number in order
      for (int factor : factors) {
        long next = curr * factor;
        if (set.add(next)) {
          pq.add(next);                             // PUSH: only a number not pushed before
        }
      }
    }
    return (int) curr;                              // REPORT: the n-th poll
  }

  public static int ugly(int n) {
    Set<Long> set = new HashSet<>(1);               // SEEN: every number ever pushed
    List<Integer> factors = asList(2, 3, 5);
    Queue<Long> pq = new PriorityQueue<>();         // MIN-HEAP: the candidates, the smallest on top
    pq.add(1L);                                     // START: 1
    int count = 0;
    while (!pq.isEmpty()) {
      long curr = pq.poll();                        // POLL: the next ugly number in order
      count += 1;
      if (count == n) {
        return (int) curr;                          // REPORT: the n-th poll
      }
      for (int factor : factors) {
        long next = curr * factor;
        if (set.add(next)) {
          pq.add(next);                             // PUSH: only a number not pushed before
        }
      }
    }
    return -1;                                      // UNREACHABLE: the heap never empties
  }

  public static void main(String[] args) {
    System.out.println(nthUglyNum(10)); // 12
    System.out.println(nthUglyNum(1)); // 1
    System.out.println(nthUgly(10)); // 12
    System.out.println(nthUgly(1)); // 1

    System.out.println(ugly(10)); // 12
    System.out.println(ugly(1)); // 1
  }
}