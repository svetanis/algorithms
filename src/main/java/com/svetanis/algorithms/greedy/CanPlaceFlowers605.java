package com.svetanis.algorithms.greedy;

// 605. Can Place Flowers
//
// Input: a -- a row of plots, 1 for a flower and 0 for an empty plot, with no two flowers next to
// each other; n -- the number of new flowers to plant. A new flower may not go next to any other
// flower. Returns whether all n fit. On LeetCode: 1 <= a.length <= 2 * 10^4, a[i] is 0 or 1, no
// two neighbouring plots both hold 1, 0 <= n <= a.length.
//
// The one idea: walk left to right and plant in every empty plot whose left and right neighbours
// are empty too (a plot past either end counts as empty). Planting at the first plot that allows
// it never costs a flower: that flower blocks only the plot to its right, and any plan that skips
// this plot and plants the next one instead can move that flower one plot left and break nothing.
// So planting as early as possible plants the most flowers there can be, and the answer is
// whether that most reaches n.
//
// Time: O(len) -- one pass over the row; len = a.length, not the flower count n.
// Space: O(1) -- nothing is allocated. The flowers are planted
// straight into the caller's array, which comes back modified.

public final class CanPlaceFlowers605 {

  public static boolean placeFlowers(int[] a, int n) {
    int len = a.length;
    for (int i = 0; i < len; i++) { // WALK: left to right
      int left = i == 0 ? 0 : a[i - 1]; // may be a flower planted one step ago
      int right = i == len - 1 ? 0 : a[i + 1]; // past either end counts as empty
      if (left + a[i] + right == 0) { // CHECK: this plot and both neighbours are empty
        a[i] = 1; // PLANT: the caller's array changes
        n--; // COUNT: one fewer still to plant
      }
    }
    return n <= 0; // RETURN: n goes below 0 when more fit than were asked for
  }

  public static void main(String[] args) {
    int[] a = { 1, 0, 0, 0, 1 };
    System.out.println(placeFlowers(a, 1)); // true
    int[] a1 = { 1, 0, 0, 0, 1 };
    System.out.println(placeFlowers(a1, 2)); // false
  }
}
