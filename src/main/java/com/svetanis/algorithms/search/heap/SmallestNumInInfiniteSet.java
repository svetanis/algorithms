package com.svetanis.algorithms.search.heap;

import java.util.HashSet;
import java.util.Set;

// 2336. Smallest Number in Infinite Set

public final class SmallestNumInInfiniteSet {
  // Time Complexity: O(n)
  // Space Complexity: O(m)

  private Set<Integer> set = new HashSet<>();

  public int popSmallest() {
    int smallest = 1;
    while (set.contains(smallest)) {
      smallest++;
    }
    set.add(smallest);
    return smallest;
  }

  public void addBack(int num) {
    set.remove(num);
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