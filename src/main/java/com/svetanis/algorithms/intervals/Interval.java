package com.svetanis.algorithms.intervals;

// Interval -- not a LeetCode problem: the shared type, with no algorithm of its own.
//
// A start and an end, held in two public int fields; the interval type shared by the problems in
// this package and its subpackages.
//
// It fixes no rule of its own. Whether an interval includes its end point, and whether two
// intervals that only touch count as overlapping, is decided by each problem that uses it. The
// fields can be written, so a method that assigns to them changes the caller's object.
//
// LeetCode passes most intervals as int[] { start, end }; it has an Interval class of its own only
// in a few problems, such as 759 (Employee Free Time), whose files here take this one.
//
// Time: O(1) -- per construction and per toString.
// Space: O(1) -- two ints.

public final class Interval {
  public int start;
  public int end;

  public Interval(int start, int end) {
    this.start = start; // STORE as given: start <= end is not checked
    this.end = end;
  }

  @Override
  public String toString() {
    return "[" + start + ", " + end + "]"; // PRINT: [start, end], the form the mains here print
  }
}