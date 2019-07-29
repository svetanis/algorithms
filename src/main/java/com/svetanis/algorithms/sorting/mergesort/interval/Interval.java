package com.svetanis.algorithms.sorting.mergesort.interval;

public final class Interval {
  protected int start;
  protected int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return "[" + start + ", " + end + "]";
  }
}