package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 1762. Buildings With an Ocean View
//
// heights[i] is the height of building i, at least 1; the buildings stand in a row, left to
// right, and the answer is an int array of positions.
// The ocean is to the right of the last building. A building has an ocean view when every
// building to its right is strictly shorter. Return those positions in increasing order.
//
// No stack is needed: scanning from the right, a building has the view exactly when it is
// taller than the tallest building seen so far.

public final class BuildingsWithOceanView1762 {
  // Time Complexity: O(n), one pass from the right and one reverse of the list
  // Space Complexity: O(n) for the list of positions

  public static int[] buildings(int[] heights) {
    int n = heights.length;
    int tallestToRight = 0; // START: heights are at least 1
    List<Integer> withView = new ArrayList<>();
    for (int i = n - 1; i >= 0; i--) { // WALK: right to left, building i arrives
      if (heights[i] > tallestToRight) { // VIEW: strictly: a building of equal height blocks the view
        withView.add(i); // RECORD: i sees the ocean
        tallestToRight = heights[i]; // RAISE: i is the tallest to the right of everyone further left
      }
    }
    Collections.reverse(withView); // ORDER: collected right to left
    return withView.stream().mapToInt(Integer::intValue).toArray(); // RETURN: positions in increasing order
  }

  public static void main(String[] args) {
    int[] a = { 4, 2, 3, 1 };
    System.out.println(Arrays.toString(buildings(a))); // [0, 2, 3]
  }
}
