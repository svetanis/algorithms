package com.svetanis.algorithms.search.heap;

import java.util.HashSet;
import java.util.Set;

// 2336. Smallest Number in Infinite Set
//
// The set starts with every positive integer. popSmallest() removes and returns the smallest
// number in it; addBack(num) puts num back if it was removed. At most 1000 calls, num <= 1000.
//
// Store what the set is MISSING instead of what it holds: the removed numbers, in a hash set. The
// smallest number held is the first number from 1 up that is not missing. No heap is needed at
// these limits: a pop walks past at most as many numbers as have been removed.
//
// Time: popSmallest O(p), p the numbers currently removed -- the walk from 1; addBack O(1)
// Space: O(p) for the removed numbers
//
// Siblings, same move (store only what differs from the infinite start):
//   SeatReservationManagerEfficient (1845) -- a counter for the never-touched numbers, a heap for
//     the ones given back; it would be the faster choice here too

public final class SmallestNumInInfiniteSet {

  private Set<Integer> set = new HashSet<>(); // MISSING: the numbers popped and not added back

  public int popSmallest() {
    int smallest = 1;
    while (set.contains(smallest)) {
      smallest++;                // WALK: past every missing number
    }
    set.add(smallest);           // POP: it is missing from now on
    return smallest;             // REPORT
  }

  public void addBack(int num) {
    set.remove(num);             // RESTORE: no longer missing; a number never popped is a no-op
  }

  public static void main(String[] args) {
    SmallestNumInInfiniteSet sns = new SmallestNumInInfiniteSet();
    sns.addBack(2);
    System.out.println(sns.popSmallest()); // 1
    System.out.println(sns.popSmallest()); // 2
    System.out.println(sns.popSmallest()); // 3
    sns.addBack(1);
    System.out.println(sns.popSmallest()); // 1
    System.out.println(sns.popSmallest()); // 4
    System.out.println(sns.popSmallest()); // 5
  }
}