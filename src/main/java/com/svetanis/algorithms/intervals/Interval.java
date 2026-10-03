package com.svetanis.algorithms.intervals;

// A start and an end, held in two public int fields; the interval type shared by the problems in
// this package and its subpackages.
//
// It fixes no rule of its own. Whether an interval includes its end point, and whether two
// intervals that only touch count as overlapping, is decided by each problem that uses it. The
// fields can be written, so a method that assigns to them changes the caller's object.

public final class Interval {
  public int start;
  public int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return "[" + start + ", " + end + "]";
  }
}