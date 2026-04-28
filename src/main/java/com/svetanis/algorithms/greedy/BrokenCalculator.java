package com.svetanis.algorithms.greedy;

// 991. Broken Calculator

public final class BrokenCalculator {
  // Time Complexity: O(log target)
  // Space Complexity: O(1)

  public static int brokenCalc(int start, int target) {
    int steps = 0;
    while (start < target) {
      if (target % 2 == 0) {
        target /= 2;
      } else {
        target += 1;
      }
      steps += 1;
    }
    steps += (start - target);
    return steps;
  }

  public static void main(String[] args) {
    System.out.println(brokenCalc(2, 3)); // 2
    System.out.println(brokenCalc(5, 8)); // 2
    System.out.println(brokenCalc(3, 10)); // 3
  }
}
