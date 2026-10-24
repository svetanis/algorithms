package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1054. Distant Barcodes
//
// Input: barcodes -- a row of barcodes. Returns the same barcodes rearranged so that no two
// neighbours are equal; any such order is accepted. On LeetCode: 1 <= barcodes.length <= 10^4,
// 1 <= barcodes[i] <= 10^4, and an answer always exists.
//
// The one idea: sort the barcodes so that the most common value comes first and every value's
// copies sit together, then deal them out to positions 0, 2, 4, ... and, once the even positions
// run out, to 1, 3, 5, ... Copies dealt to the same kind of position are two apart, so they never
// touch. An answer exists, so the most common value has at most (n + 1) / 2 copies, which is the
// number of even positions: it fits on even positions alone. A later value may be split, its
// first copies at the last even positions and the rest at the first odd positions, and those two
// groups could only meet if the value filled about half the row. Then the most common value,
// dealt before it and at least as frequent, would have taken every even position, and the later
// value would not be split at all. So no two equal barcodes end up side by side.
//
// Time: O(n log n) -- sorting the n boxed values with a comparator; counting is one pass.
// Space: O(n + max) -- the boxed copy and the answer, n each, and a count for every value up to
// the largest one, max <= 10^4.

public final class DistantBarcodes {

  public static int[] rearrangeBarcodes(int[] barcodes) {
    int n = barcodes.length;
    int max = Integer.MIN_VALUE;
    Integer[] clone = new Integer[n]; // boxed, so it can be sorted with a comparator
    for (int i = 0; i < n; i++) {
      clone[i] = barcodes[i];
      max = Math.max(max, barcodes[i]); // the largest value sizes the count array
    }

    int[] frequency = new int[max + 1];
    for (int barcode : barcodes) {
      frequency[barcode] += 1; // COUNT the copies of each value
    }
    Arrays.sort(clone, (a, b) -> 
      frequency[a] == frequency[b] ? a - b : frequency[b] - frequency[a]); // SORT: most common first; a tie by value keeps each value's copies together
    
    int index = 0;
    int[] sorted = new int[n];
    for (int i = 0; i < n; i += 2) {
      sorted[i] = clone[index++]; // DEAL to the even positions first
    }
    for (int i = 1; i < n; i += 2) {
      sorted[i] = clone[index++]; // DEAL the rest to the odd positions
    }
    return sorted; // RETURN: no two neighbours equal
  }

  public static void main(String[] args) {
    int[] a1 = { 1, 1, 1, 2, 2, 2 };
    System.out.println(Arrays.toString(rearrangeBarcodes(a1))); // [1, 2, 1, 2, 1, 2]

    int[] a2 = { 1, 1, 1, 1, 2, 2, 3, 3 };
    System.out.println(Arrays.toString(rearrangeBarcodes(a2))); // [1, 2, 1, 2, 1, 3, 1, 3]
  }
}
