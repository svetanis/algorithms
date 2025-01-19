package com.svetanis.algorithms.prefixsum;

// 1732. Find the Highest Altitude

public final class HighestAltitude1732 {
  // Time Complexity: O(n)
  // Space Complexity: O(1)

  public static int highestAltitude(int[] a) {
    int max = 0;
    int curr = 0;
    for (int altitude : a) {
      curr += altitude;
      max = Math.max(max, curr);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] a = { -5, 1, 5, 0, -7 };
    System.out.println(highestAltitude(a)); // 1

    int[] a1 = { -4, -3, -2, -1, 4, 3, 2 };
    System.out.println(highestAltitude(a1)); // 0
  }
}