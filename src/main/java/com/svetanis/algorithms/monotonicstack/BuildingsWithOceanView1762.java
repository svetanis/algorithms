package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 1762. Buildings With an Ocean View
//
// The ocean is to the right of the last building. A building has an ocean view when every
// building to its right is strictly shorter. Return those positions in increasing order.
//
// No stack is needed: scanning from the right, a building has the view exactly when it is
// taller than the tallest building seen so far.

public final class BuildingsWithOceanView1762 {
  // Time Complexity: O(n)
  // Space Complexity: O(n) for the list of positions

  public static int[] buildings(int[] heights) {
    int n = heights.length;
    int tallestToRight = 0; // heights are at least 1
    List<Integer> withView = new ArrayList<>();
    for (int i = n - 1; i >= 0; i--) {
      if (heights[i] > tallestToRight) { // strictly: a building of equal height blocks the view
        withView.add(i);
        tallestToRight = heights[i];
      }
    }
    Collections.reverse(withView); // collected right to left
    return withView.stream().mapToInt(Integer::intValue).toArray();
  }

  public static void main(String[] args) {
    int[] a = { 4, 2, 3, 1 };
    Print.print(buildings(a)); // 0 2 3
  }
}
