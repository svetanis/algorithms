package com.svetanis.algorithms.prefixsum;

// 1732. Find the Highest Altitude
//
// Input: an array a of n gains. A biker starts at altitude 0 at point 0, and a[i] is the change in
// altitude from point i to point i + 1. Returns the highest altitude among the n + 1 points. On
// LeetCode: 1 <= n <= 100, -100 <= a[i] <= 100.
//
// The one idea: the altitude at point i + 1 is the sum of the gains a[0 .. i] -- a running sum.
// curr holds the altitude of the point just reached, and max the highest seen so far. max starts
// at 0 because point 0, altitude 0, is a point too: when every gain is negative the answer is 0.
//
// Time: O(n) -- one pass
// Space: O(1) -- two variables; no array of altitudes is built

public final class HighestAltitude1732 {

  public static int highestAltitude(int[] a) {
    int max = 0; // START: point 0 has altitude 0
    int curr = 0; // ALTITUDE of the point just reached
    for (int altitude : a) {
      curr += altitude; // CLIMB: one gain at a time
      max = Math.max(max, curr); // RECORD the highest
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