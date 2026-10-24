package com.svetanis.algorithms.greedy;

// 991. Broken Calculator
//
// Input: start -- the number on the display; target -- the number wanted. The calculator has two
// buttons: double the number shown, or subtract 1 from it. Returns the fewest presses that turn
// start into target. On LeetCode: 1 <= start, target <= 10^9.
//
// The one idea: work backwards, from target down to start. Backwards, the buttons become "halve"
// (allowed only on an even number) and "add 1". Going forwards it is unclear whether to double now
// or subtract first; going backwards there is no real choice. While target is above start, an odd
// target cannot be halved, so add 1. An even target is halved at once: adding 1 twice and then
// halving reaches the same number as halving and then adding 1, with one press more. Once target
// is at or below start, halving only moves further away, so the rest is start - target presses of
// "add 1", each one undoing a "subtract 1".
//
// Time: O(log target) -- every two steps at least halve target: an odd target becomes even, then
// is halved.
// Space: O(1) -- one counter.

public final class BrokenCalculator {

  public static int brokenCalc(int start, int target) {
    int steps = 0;
    while (start < target) { // WALK BACKWARDS from target until it is not above start
      if (target % 2 == 0) {
        target /= 2; // UNDO a double
      } else {
        target += 1; // UNDO a subtract; at most 10^9 + 1, fits an int
      }
      steps += 1; // COUNT the press
    }
    steps += (start - target); // FINISH: the rest is "subtract 1", one press per unit
    return steps; // RETURN
  }

  public static void main(String[] args) {
    System.out.println(brokenCalc(2, 3)); // 2
    System.out.println(brokenCalc(5, 8)); // 2
    System.out.println(brokenCalc(3, 10)); // 3
  }
}
