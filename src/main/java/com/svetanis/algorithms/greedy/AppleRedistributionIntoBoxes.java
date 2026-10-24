package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 3074. Apple Redistribution into Boxes
//
// Input: apple[i] -- the apples in pack i; capacity[j] -- how many apples box j holds. The apples
// of one pack may be split across several boxes. Returns the fewest boxes that hold every apple.
// On LeetCode: 1 <= apple.length, capacity.length <= 50, 1 <= apple[i], capacity[j] <= 50, and
// the boxes together always have room for all the apples.
//
// The one idea: packs can be split, so only the total number of apples matters, not which pack
// an apple came from. To hold that total in as few boxes as possible, take the biggest box, then
// the next biggest, and stop as soon as the boxes taken hold the total. Any k boxes hold at most
// what the k biggest boxes hold, so when the k biggest are not enough, no k boxes are. The loop
// therefore never has to give back a box it took and try another one.
//
// Time: O(n + m log m) -- n = apple.length, m = capacity.length: one pass adds up the packs, the
// sort orders the boxes, and the take loop runs at most m times.
// Space: O(1) -- beyond the sort, which works in place: capacity comes back sorted.
//
// When the boxes cannot hold all the apples, which LeetCode rules out, the take loop walks off
// the front of capacity and throws.

public final class AppleRedistributionIntoBoxes {

  public static int minBoxes(int[] apple, int[] capacity) {
    Arrays.sort(capacity); // SORT: smallest first, so the biggest box sits at the end
    int total = 0;
    for (int pack : apple) {
      total += pack; // COUNT: only the total matters -- packs can be split
    }
    int count = 0;
    int n = capacity.length;

    while (total > 0) { // TAKE boxes while some apples still have no room
      total -= capacity[n - 1 - count]; // the biggest box not yet taken
      count += 1;
    }
    return count; // RETURN: boxes taken
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 3, 2 };
    int[] c1 = { 4, 3, 1, 5, 2 };
    System.out.println(minBoxes(a1, c1)); // 2

    int[] a2 = { 5, 5, 5 };
    int[] c2 = { 2, 4, 2, 7 };
    System.out.println(minBoxes(a2, c2)); // 4
  }
}
