package com.svetanis.algorithms.sorting.mergesort.interval;

public final class Job {
  protected int start;
  protected int end;
  protected int load;

  public Job(int start, int end, int load) {
    this.start = start;
    this.end = end;
    this.load = load;
  }

  @Override
  public String toString() {
    return "[" + start + ", " + end + ", " + load + "]";
  }
}