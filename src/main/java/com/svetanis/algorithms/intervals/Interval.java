package com.svetanis.algorithms.intervals;

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